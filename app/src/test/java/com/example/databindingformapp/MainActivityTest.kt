package com.example.databindingformapp

import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    private var activity: MainActivity = Robolectric.setupActivity(MainActivity::class.java)

    private val editTextEmail by lazy {
        activity.findViewById<AppCompatEditText>(R.id.email)
    }

    private val editTextPassword by lazy {
        activity.findViewById<AppCompatEditText>(R.id.password)
    }

    private val buttonSubmit by lazy {
        activity.findViewById<AppCompatButton>(R.id.submit_button)
    }

    companion object {
        private const val CORRECT_PASSWORD = "mypassword"
        private const val INCORRECT_PASSWORD = "error"
        private const val VALID_EMAIL = "validemail@myemail.com"
        private const val INVALID_EMAIL = "wrongemail@ss"
        private const val EMPTY_VALUE = ""
    }

    @Test
    fun whenEmailAndPasswordIsEmpty_submitButtonShouldBeDisabled() {
        assertEquals(activity.submit_button.isEnabled, false)
    }

    @Test
    fun whenEmailIsNotNull_andPasswordIsEmpty_submitButtonShouldBeDisabled() {

        editTextEmail.setText(VALID_EMAIL)
        editTextPassword.setText(EMPTY_VALUE)

        assertEquals(buttonSubmit.isEnabled, false)
    }

    @Test
    fun whenEmailIsNotNull_andPasswordIsIncorrect_submitButtonShouldBeDisabled() {

        editTextEmail.setText(VALID_EMAIL)
        editTextPassword.setText(INCORRECT_PASSWORD)

        assertEquals(buttonSubmit.isEnabled, false)
    }

    @Test
    fun whenPasswordIsNotNull_andEmailIsEmpty_submitButtonShouldBeDisabled() {

        editTextPassword.setText(CORRECT_PASSWORD)
        editTextEmail.setText(EMPTY_VALUE)

        assertEquals(buttonSubmit.isEnabled, false)
    }

    @Test
    fun whenEmailAndPasswordIsNotEmpty_andPasswordIsWrong_submitButtonShouldBeDisabled() {

        editTextPassword.setText(INVALID_EMAIL)
        editTextEmail.setText(CORRECT_PASSWORD)

        assertEquals(buttonSubmit.isEnabled, false)
    }

    @Test
    fun whenEmailAndPasswordIsNotEmpty_submitButtonShouldBeEnabled() {
        editTextEmail.setText(VALID_EMAIL)
        editTextPassword.setText(CORRECT_PASSWORD)

        assertEquals(buttonSubmit.isEnabled, true)
    }
}
