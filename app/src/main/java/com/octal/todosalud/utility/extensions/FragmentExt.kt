package com.octal.todosalud.utility.extensions

import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.octal.todosalud.R

fun androidx.fragment.app.Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = activity?.toast(message, duration)

inline fun androidx.fragment.app.Fragment.alertDialog(body: AlertDialog.Builder.() -> AlertDialog.Builder) = activity?.alertDialog(body)

fun androidx.fragment.app.Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

// find all nav controllers from closest to farest
fun Fragment.findAllNavControllers(): List<NavController> {
    val navControllers = mutableListOf<NavController>()
    var parent = parentFragment
    while (parent != null) {
        if (parent is NavHostFragment) {
            navControllers.add(parent.navController)
        }
        parent = parent.parentFragment
    }
    return navControllers
}

// find one nav controller by fragment id
fun Fragment.findNavControllerById(@IdRes id: Int): NavController {
    var parent = parentFragment
    while (parent != null) {
        if (parent is NavHostFragment && parent.id == id) {
            return parent.navController
        }
        parent = parent.parentFragment
    }
    throw RuntimeException("NavController with specified id not found")
}

fun Fragment.getTodoAppNavController(): NavController? =
    (this.activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment?)?.navController

