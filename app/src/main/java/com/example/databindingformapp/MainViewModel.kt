package com.example.databindingformapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var _email: MutableLiveData<String> = MutableLiveData()
    private var _password: MutableLiveData<String> = MutableLiveData()
    private var _buttonEnable: MutableLiveData<Boolean> = MutableLiveData()

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
        _buttonEnable.value = isValidEmail().and(isValidPassword()) == true
    }

    private fun isValidEmail(): Boolean {
        return email.value?.isNotBlank() == true && email.value?.isEmailValid() == true
    }

    private fun isValidPassword(): Boolean {
        return password.value?.length in 6..12
    }
}
