package com.acemirr.autovalidatetextfield

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MainActivityViewModel(application: Application):AndroidViewModel(application) {
    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var errorEmail: MutableLiveData<String> = MutableLiveData()
    var errorPassword: MutableLiveData<String> = MutableLiveData()

    private var isEmailValid: Boolean = false
    private var isPasswordValid: Boolean = false
    var isValid: MutableLiveData<Boolean> = MutableLiveData()

    fun setup(lifecycleOwner: LifecycleOwner,context: Context){
        email.observe(lifecycleOwner, Observer { email ->
            val validationModel = email.validateEmail(context)
            isEmailValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid)
            errorEmail.postValue(validationModel.message)
        })
        password.observe(lifecycleOwner, Observer { password ->
            val validationModel = password.validatePassword(context)
            isPasswordValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid)
            errorPassword.postValue(validationModel.message)
        })
    }
    private fun validateInput(email: Boolean, password: Boolean) {
        isValid.postValue(email && password)
    }

    fun onClickVerify(){
//        do your stuff
    }
}