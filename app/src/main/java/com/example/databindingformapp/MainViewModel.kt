package com.example.databindingformapp

import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    //them receive the inputed infor
    private var _email: MutableLiveData<String> = MutableLiveData()
    private var _password: MutableLiveData<String> = MutableLiveData()
    private var _buttonEnable: MutableLiveData<Boolean> = MutableLiveData()

    //them observer the inputed data and update the ui throught data binding
    var email: LiveData<String> = _email
        set(value) {
            field = value
            validateUserInfo()
        }

    var password: LiveData<String> = _password
        set(value) {
            field = value
            validateUserInfo()
        }

    var buttonEnable: LiveData<Boolean> = _buttonEnable

    fun updateEmail(typedEmail: String) {
        with(typedEmail) {
            _email.value = this
            email = email
        }
    }

    fun updatePassword(typedPassword: String) {
        with(typedPassword) {
            _password.value = this
            password = _password
        }
    }

    private fun validateUserInfo() {
        _buttonEnable.value = validateEmail().and(validatePassword()) == true
    }

    private fun validateEmail(): Boolean {
        return this.email.value?.isNotBlank() == true && this.email.value?.isEmailValid() == true
    }

    private fun validatePassword(): Boolean {
        return this.password.value?.length in 6..12
    }
}

internal fun String.isEmailValid(): Boolean = EMAIL_ADDRESS.matcher(this).matches()