package com.ferbotz.calculatortest.helper

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Any?.method(): String{

    if(this == null) return "null"

    return toString()
 }
  fun String?.elvis():String{
      return this ?: "name"
  }
  fun String.log(tag: String){
      Log.e(tag,this)
  }
  fun Int.validation():Boolean{
      return this%2 ==0
  }
  fun Int.add(b:Int):Int{
      return this+b
  }

fun String.validateEmail() = this.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
fun String.validateNumber() = this.isNotBlank() && this.matches(Regex("^(\\+91[\\s-]?)?\\d{10}$"))
fun String.validatePassword() = this.isNotBlank() && this.length >= 6


fun String.toast(context: Context, message :String){
    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()

}