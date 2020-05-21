package com.acemirr.autovalidatetextfield

import android.content.Context
import android.util.Patterns

class ValidationModel(val isValid: Boolean, val message: String)

fun String.validateEmail(context: Context): ValidationModel {
    return if (this.trim().isEmpty()) {
        ValidationModel(false, "Email should not Empty")
    } else if (!Patterns.EMAIL_ADDRESS.matcher(this).matches()) {
        ValidationModel(false, "Email format is wrong")
    } else {
        ValidationModel(true, "")
    }
}

//  context for harcoded string
fun String.validatePassword(context: Context): ValidationModel {
    // require combination number and text
    val pattern = "[0-9]".toRegex()
    val pattern2 = "[a-z]|[A-Z]".toRegex()

    return if (this.isEmpty()) {
        ValidationModel(false, "Password should not empty")
    } else if (this.length < 5) {
        ValidationModel(false, "Minimal 5 character")
    } else if (!pattern.containsMatchIn(this) || !pattern2.containsMatchIn(this)) {
        ValidationModel(false, "Require combination number and text")
    } else {
        ValidationModel(true, "")
    }
}