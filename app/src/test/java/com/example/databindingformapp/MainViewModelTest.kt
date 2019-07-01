package com.example.databindingformapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<String>

    @Mock
    private lateinit var observerButton: Observer<Boolean>

    private var target: MainViewModel = MainViewModel()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun whenUpdateEmail_verifyIfEmailFieldWasChanged() {
        target.password.observeForever(observer)

        target.updatePassword("anyemail")

        verify(observer).onChanged(any())
    }

    @Test
    fun whenUpdatePassword_verifyIfPasswordFieldWasChanged() {
        target.password.observeForever(observer)

        target.updatePassword("anypassword")

        verify(observer).onChanged(any())
    }

    @Test
    fun whenUpdatePasswordAndEmail_verifyIfButtonEnableFieldWasChanged() {
        target.buttonEnable.observeForever(observerButton)

        target.updatePassword("anypassword")
        target.updateEmail("anyemail@email.com")

        verify(observerButton, times(2)).onChanged(any())
    }

    @Test
    fun whenUpdatePasswordAndEmailAreCorrect_verifyIfButtonEnableAreTrue() {
        target.updatePassword("anypassword")
        target.updateEmail("anyemail@email.com")

        target.buttonEnable.value?.apply {
            Assert.assertTrue(this)
        }
    }

    @Test
    fun whenUpdatePasswordAndEmailAreIncorrect_verifyIfButtonEnableAreFalse() {
        target.updatePassword("a")
        target.updateEmail("anyemai")

        target.buttonEnable.value?.apply {
            Assert.assertFalse(this)
        }
    }
}