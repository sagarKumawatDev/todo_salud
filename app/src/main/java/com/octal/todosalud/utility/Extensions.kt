package com.octal.todosalud.utility

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.octal.todosalud.presentation.mainActivity.MainActivity


val Fragment.fragmentScope: LifecycleCoroutineScope
get() = this.viewLifecycleOwner.lifecycleScope

val Fragment.safeFragmentScope: LifecycleCoroutineScope?
get() = Result.runCatching { this@safeFragmentScope.viewLifecycleOwner.lifecycleScope }.getOrNull()

fun Fragment.showProgress() {
    (requireActivity() as MainActivity).showProgress()
}

/*fun Fragment.showMessageDialog(message:String) {
    (requireActivity() as MainActivity).showMessageDialog(message)
}*/

fun Fragment.hideProgress() {
    (requireActivity() as MainActivity).hideProgress()
}


fun String?.defaultOnNullValue(): String = this ?: ""
fun String?.onEmptyOrNull(replaceString: String): String = if (this.isNullOrEmpty()) {
    replaceString
} else {
    this
}

fun Int?.defaultOnNullValue(): Int = this ?: 0
fun Double?.defaultOnNullValue(): Double = this ?: 0.0
fun Float?.defaultOnNullValue(): Float = this ?: 0.0f
fun Boolean?.defaultOnNullValue(): Boolean = this ?: false
fun <T> List<T>?.defaultOnNullValue(): List<T> = this ?: emptyList()


