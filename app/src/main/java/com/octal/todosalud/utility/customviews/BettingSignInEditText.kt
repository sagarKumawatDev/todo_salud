package com.octal.todosalud.utility.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.octal.todosalud.R
import com.octal.todosalud.databinding.SigninEditTextLayoutBinding

class BettingSignInEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val binding = SigninEditTextLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setHint(context, attrs)
        setStartImage(context, attrs)
    }

    private fun setHint(context: Context, attrs: AttributeSet?){
        var hint = ""
        if (attrs != null) {
            val array = context.obtainStyledAttributes(attrs, R.styleable.BettingSignInEditText)
            array.getString(R.styleable.BettingSignInEditText_bettingSignInEditTextHint)?.let {
                hint = it
            }
            array.recycle()
        }
        if (hint.isNotEmpty()) {
            binding.et.hint = hint
        }
    }

    private fun setStartImage(context: Context, attrs: AttributeSet?){
        var startImageDrawable: Drawable? = null
        if (attrs != null) {
            val array = context.obtainStyledAttributes(attrs, R.styleable.BettingSignInEditText)
            startImageDrawable = array.getDrawable(R.styleable.BettingSignInEditText_bettingSignInEditTextStartImage)
            array.recycle()
        }
        if (startImageDrawable != null) {
            binding.iv.setImageDrawable(startImageDrawable)
        }
    }
}
