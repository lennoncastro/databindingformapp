package com.example.databindingformapp

import android.util.Patterns

internal fun String.isEmailValid(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()
