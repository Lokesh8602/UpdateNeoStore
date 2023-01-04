package com.neosoft.updateneostore.ui.activites.register


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.neosoft.updateneostore.MainActivity
import com.neosoft.updateneostore.R
import com.neosoft.updateneostore.base.BaseActivity
import com.neosoft.updateneostore.databinding.ActivityRegisterBinding
import com.neosoft.updateneostore.di.DaggerApplication
import javax.inject.Inject

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {
    @Inject
    lateinit var viewModel: RegisterViewModel
    var selectGender: String? = null
    lateinit var radioBtnId: RadioButton

    @Inject
    lateinit var registerFactory: RegisterFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as DaggerApplication).appComponent.inject(this)

        binding.btnRegister.setOnClickListener {
            createUser()
        }

        viewModel = ViewModelProvider(this, registerFactory).get(RegisterViewModel::class.java)

        viewModel.registerLiveData.observe(this, Observer {
            if (it?.status == 200) {
                showToast(it.message)
                Toast.makeText(this@RegisterActivity, "Registered Successful", Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        })
    }

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityRegisterBinding =
        ActivityRegisterBinding.inflate(layoutInflater)

    fun createUser() {
        val firstname = binding.txtInputFirstName.editText?.text.toString()
        val lastname = binding.txtInputLastName.editText?.text.toString()
        val emailcheck = binding.txtInputEmail.editText?.text.toString()
        val regPassword = binding.txtInputRegPassword.editText?.text.toString()
        val confrmPassword = binding.txtInputConfirmedPassword.editText?.text.toString()
        val phoneNumber = binding.txtInputPhoneNumber.editText?.text.toString()
        val radioButton = binding.radioGroupButton.checkedRadioButtonId
        when (radioButton) {
            R.id.radioBtnMale -> selectGender = "M"
            R.id.radioBtnFemale -> selectGender = "F"
        }
        selectGender?.let {
            Log.d("Test", selectGender!!)
            if (viewModel.validateForm(
                    firstname,
                    lastname,
                    emailcheck,
                    regPassword,
                    confrmPassword,
                    binding.radioGroupButton,
                    binding.checkBox,
                    phoneNumber
                )
            ) {

                viewModel.register(
                    firstname,
                    lastname,
                    emailcheck,
                    regPassword,
                    confrmPassword,
                    selectGender!!,
                    phoneNumber.toLong()
                )
            }

        }
    }
}