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



