package com.neosoft.updateneostore.ui.activites.register

import android.util.Patterns
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neosoft.updateneostore.data.pojo.RegisterResponse
import com.neosoft.updateneostore.repository.MainRepository
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject


class RegisterViewModel@Inject constructor(private val repository: MainRepository): ViewModel() {
    val registerLiveData = MutableLiveData<RegisterResponse>()

    fun register(first_name: String,last_name: String, email: String,password: String,confirm_password: String,gender:String,
                 phone_no: Long) {
        viewModelScope.launch {

            val response = repository.register(first_name,last_name,email, password, confirm_password, gender, phone_no)

            if (response != null) {
                registerLiveData.postValue(response.body())
            }

        }

    }
    fun validateForm(firstname: String, lastname: String, email: String, password: String, cnfrmpass: String, radioGroup: RadioGroup, checkBox: CheckBox, phone_no: String):Boolean
    {
        return  (!firstNameValidate(firstname) or !lastname(lastname) or emailCheck(email) or cnfrmpassword(cnfrmpass)   or !checkGender(radioGroup) or !checkCheckBox(checkBox) or !phonenocheck(phone_no) )
    }

    fun firstNameValidate(firstname : String): Boolean {
        if(firstname.isEmpty()){
            firstname.apply {


            }
            return false
        }
        return true
    }


    fun lastname(lastname : String): Boolean {
        if(lastname.toString().isEmpty()){
            lastname.apply {

            }
            return false
        }
        return true
    }


    fun emailCheck(email: String):Boolean{
        if (email.isEmpty()) {

            return false

        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return false
        }
        return true
    }
    fun phonenocheck(phone_no: String): Boolean {
        if (phone_no == null) {
            return false
            error("Phone Number is empty")
        } else if (phone_no.length != 10) {
            return false
            error("invalid number")
        }
        return true
    }

 fun cnfrmpassword(password: String): Boolean {
     if (password.length == 0) {
        /* Toast.makeText(context, "confirm password is needed ", Toast.LENGTH_LONG).show()*/
         return false
     } else if (password != password) {/*
         Toast.makeText(
             context,
             "confirmpassword should be same as password ",
             Toast.LENGTH_LONG
         )
             .show()*/

     }
     return false

 }

    fun checkGender( radioGroup: RadioGroup): Boolean {
        if(radioGroup.checkedRadioButtonId == -1){
          /*  Toast.makeText( "Please Select Gender", Toast.LENGTH_SHORT).show()*/
            return false
        }
        return true
    }
    fun checkCheckBox( checkBox: CheckBox): Boolean {
        if(checkBox.isChecked){
            return true
        }
        else{
            /*Toast.makeText(context, "Please Select Terms & Conditions", Toast.LENGTH_SHORT).show()*/
        }
        return false
    }


}