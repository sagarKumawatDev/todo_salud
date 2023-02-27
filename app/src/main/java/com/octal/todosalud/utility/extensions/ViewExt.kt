package com.octal.todosalud.utility.extensions


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

fun ViewGroup.inflate(@LayoutRes resourceId: Int) = LayoutInflater.from(context).inflate(resourceId, this, false)

inline fun ViewGroup.forEach(action: (view: View) -> Unit) {
    for (index in 0 until childCount) {
        action(getChildAt(index))
    }
}

fun TextInputLayout.disableHintAnimation() {
    isHintAnimationEnabled = false
}

fun EditText.clearBackground() {
    val paddingBottom = paddingBottom
    val paddingTop = paddingTop
    val paddingLeft = paddingLeft
    val paddingRight = paddingRight
    background = null
    setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
}

fun View.visible(animate: Boolean = true) {
    if (animate) {
        animate().alpha(1f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                visibility = View.VISIBLE
            }
        })
    } else {
        visibility = View.VISIBLE
    }
}

fun View.invisible(animate: Boolean = true) {
    hide(View.INVISIBLE, animate)
}

fun View.gone(animate: Boolean = true) {
    hide(View.GONE, animate)
}

fun RecyclerView?.getCurrentPosition() : Int {
    return (this?.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
}
fun View.visibleOrInvisible(show: Boolean, animate: Boolean = true) {
    if (show) visible(animate) else invisible(animate)
}

fun View.visibleOrGone(show: Boolean, animate: Boolean = true) {
    if (show) visible(animate) else gone(animate)
}

private fun View.hide(hidingStrategy: Int, animate: Boolean = true) {
    if (animate) {
        animate().alpha(0f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                visibility = hidingStrategy
            }
        })
    } else {
        visibility = hidingStrategy
    }
}


inline fun View.snack(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit
) {
    snackx(resources.getString(messageRes), length, f)
}

inline fun View.snackx(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(ContextCompat.getColor(context, color)) }
}

/**
 * for check string
 */

fun checkStringValue(text: String?): Boolean {
    return !(text == null || text.trim { it <= ' ' } == "null" || text.trim { it <= ' ' }.isEmpty())
}

fun View.slideUp(duration: Int = 500) {
    visibility = View.VISIBLE
    val animate = TranslateAnimation(0f, 0f, this.height.toFloat(), 0f)
    animate.duration = duration.toLong()
    animate.fillAfter = true
    this.startAnimation(animate)
}

fun View.slideDown(duration: Int = 500) {
    visibility = View.VISIBLE
    val animate = TranslateAnimation(0f, 0f, 0f, this.height.toFloat())
    animate.duration = duration.toLong()
    animate.fillAfter = true
    this.startAnimation(animate)
}


inline fun <reified T> tryCast(instance: Any?, block: T.() -> Unit) {
    if (instance is T) {
        block(instance)
    }
}

fun datePicker(context: Context?, tv: View?, minDate: String?, isMinDate: Boolean, isDOB: Boolean): String? {
    val dateReturn = ""
    val myCalendar = Calendar.getInstance()
    val date =
        OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar[Calendar.YEAR] = year
            myCalendar[Calendar.MONTH] = monthOfYear
            myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            val myFormat = "yyyy-MM-dd HH:mm:ss"
            val myFormatTwo = "yyyy-MM-dd"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            val sdfTwo = SimpleDateFormat(myFormatTwo, Locale.US)
            if (tv is TextView) {
                val textView = tv
                textView.text = sdfTwo.format(myCalendar.time)
                textView.setTextColor(Color.GRAY)
            }
        }
    var d: Date? = null
    try {
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        d = if (minDate != null) {
            sdf.parse(minDate)
        } else {
            val cal = Calendar.getInstance()
            if (isDOB) {
            } else cal.add(Calendar.DAY_OF_MONTH, 1)

       cal.time
        }
    } catch (e: Exception) {
        e.fillInStackTrace()
    }
    val datePicker = DatePickerDialog(context!!, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH], myCalendar[Calendar.DAY_OF_MONTH])
    if (isDOB) {
        datePicker.datePicker.maxDate = d!!.time
    } else {
        if (isMinDate) datePicker.datePicker.minDate = d!!.time
    }
    datePicker.show()
    return dateReturn
}

fun String.isValidEmail() = !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun isValidPassword(password: String?): Boolean {
    val pattern: Pattern
    val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$"
    pattern = Pattern.compile(PASSWORD_PATTERN)
    val matcher: Matcher = pattern.matcher(password)
    Log.e("mate" , matcher.matches().toString())
    return !(matcher.matches())
}

fun isValidEmail(target: CharSequence): Boolean {
    return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
}


fun Group.setAllOnClickListener(listener: View.OnClickListener?) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}