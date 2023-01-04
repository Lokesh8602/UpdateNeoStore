package com.neosoft.updateneostore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neosoft.updateneostore.base.BaseActivity
import com.neosoft.updateneostore.databinding.ActivityMainBinding
import com.neosoft.updateneostore.di.DaggerApplication
import com.neosoft.updateneostore.di.Module.GetSharedData
import com.neosoft.updateneostore.repository.MainRepository
import com.neosoft.updateneostore.ui.activites.dashboard.DashboardActivity
import com.neosoft.updateneostore.ui.activites.login.LoginFactory
import com.neosoft.updateneostore.ui.activites.login.LoginViewModel
import com.neosoft.updateneostore.ui.activites.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {


    @Inject
    lateinit var viewModel: LoginViewModel
    @Inject
    lateinit var loginFactory: LoginFactory
    @Inject
    lateinit var sharePref: GetSharedData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as DaggerApplication).appComponent.inject(this)

        viewModel=ViewModelProvider(this,loginFactory).get(LoginViewModel::class.java)

        viewModel.loginLiveData.observe(this, Observer {
            if (it?.status==200){
                showToast("Successful")
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                showToast("ERROR")
            }
            sharePref.saveToken(it.data.access_token)
        })

        binding.btnLoginButton.setOnClickListener {
            userLogin()
        }
        binding.imgplus.setOnClickListener {
            val intent=Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

       }
    fun userLogin() {
        viewModel.getlogin(
            txtInputUserName.editText?.text.toString(),
            txtInputPassword.editText?.text.toString()
        )
    }

    override fun inflateLayout(layoutInflater: LayoutInflater)= ActivityMainBinding.inflate(layoutInflater)
    }


