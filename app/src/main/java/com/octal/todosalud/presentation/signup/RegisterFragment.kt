package com.octal.todosalud.presentation.signup

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.octal.todosalud.R
import com.octal.todosalud.databinding.FragmentSignUpBinding
import com.octal.todosalud.utility.Utils
import com.octal.todosalud.utility.extensions.toast
import dagger.hilt.android.AndroidEntryPoint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Boolean
import kotlin.Int
import kotlin.apply
import kotlin.toString

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private var dob = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListener()

    }

    private fun setClickListener() {
        binding.apply {
            textContinue.setOnClickListener {
                if (validateEntries()) {

                }
            }
            alreadyHaveAccountText.setOnClickListener {

            }
            imageArrowBack.setOnClickListener {

            }
            dobEdit.setOnClickListener {

            }
            relativeFacebookRegister.setOnClickListener {

            }
        }

    }


    private fun validateEntries(): Boolean {
        binding.apply {
            return when {
                firstNameEdit.text.isNullOrEmpty() -> {
                    toast(resources.getString(R.string.input_field_required_validation))
                    return false
                }
                lastNameEdit.text.isNullOrEmpty() -> {
                    toast(resources.getString(R.string.input_field_required_validation))
                    return false
                }

                editEmail.text.isNullOrEmpty() -> {
                    toast(resources.getString(R.string.input_field_required_validation))
                    return false
                }
                Utils.validate(
                    editEmail.text.toString()
                ).not() -> {
                    toast(resources.getString(R.string.enter_valid_email_id))
                    return false
                }
                maleRadio.isChecked.not() && femaleRadio.isChecked.not() -> {
                    toast(resources.getString(R.string.input_field_required_validation))
                    return false
                }

                editPassword.text.isNullOrEmpty() -> {
                    toast(resources.getString(R.string.input_field_required_validation))
                    return false
                }
                editResetPassword.text.isNullOrEmpty() -> {
                    toast(resources.getString(R.string.input_field_required_validation))
                    return false
                }

                editPassword.text.toString() != editResetPassword.text.toString() -> {
                    toast(resources.getString(R.string.register_password_problem_popup_content))
                    return false
                }
                appCompatCheckBox.isChecked.not() -> {
                    toast(resources.getString(R.string.input_field_required_validation))
                    return false
                }
                else -> true
            }
        }
    }

    private fun showDatePicker() {
        val now = Calendar.getInstance()
        val dpd = DatePickerDialog(
            requireContext(),
            OnDateSetListener { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                var months = ""
                var day = ""
                months = if (year < 10) {
                    "0" + (dayOfMonth + 1)
                } else {
                    (month + 1).toString()
                }
                day = if (dayOfMonth < 10) {
                    "0$dayOfMonth"
                } else {
                    dayOfMonth.toString()
                }
                val deliveryDate = "$year-$months-$day"
                val dateFormatPrev = SimpleDateFormat("yyyy-MM-dd")
                var d: Date? = null
                try {
                    d = dateFormatPrev.parse(deliveryDate)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
                val dateFormat = SimpleDateFormat("dd MMM yyyy")
                val changedDate = dateFormat.format(d)
                dob = dateFormatPrev.format(d)
                binding.dobEdit.setText(changedDate)
            }, now[Calendar.YEAR],
            now[Calendar.MONTH],
            now[Calendar.DAY_OF_MONTH]
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 568025136000L
        dpd.show()
        dpd.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
        // dpd(Calendar.getInstance());
    }

}
