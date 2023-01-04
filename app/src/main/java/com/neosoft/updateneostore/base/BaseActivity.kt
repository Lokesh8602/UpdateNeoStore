package com.neosoft.updateneostore.base


import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.neosoft.updateneostore.R

 abstract class BaseActivity <VB : ViewBinding> : AppCompatActivity() {

 protected lateinit var binding : VB

 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  binding = inflateLayout(layoutInflater)
  setContentView(binding.root)

 }

 abstract fun inflateLayout(layoutInflater: LayoutInflater) : VB

 open fun showToast(message:String){
  Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
 }
}






