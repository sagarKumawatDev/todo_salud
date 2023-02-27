package com.octal.todosalud.presentation.forgotPassword

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton
import com.octal.todosalud.R
import com.octal.todosalud.databinding.FragmentForgotPasswordBinding
import com.octal.todosalud.domain.entities.ForgotPasswordRequest
import com.octal.todosalud.utility.*
import com.octal.todosalud.utility.extensions.hideKeyboard
import com.octal.todosalud.utility.extensions.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.String
import kotlin.Boolean
import kotlin.Unit
import kotlin.also
import kotlin.getValue
import kotlin.let
import kotlin.toString

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding
    private val viewModel: ForgetPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentForgotPasswordBinding.inflate(layoutInflater, container, false).also {
        binding = it
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeForgotPasswordApiFlow()
        setClickListener()
    }

    private fun setClickListener() {
        binding.textSendEmail.setOnClickListener {
            if (isValid()) {
                viewModel.callForgotPasswordApi(
                    ForgotPasswordRequest(
                        email = binding.editEmail.text.toString()
                    )
                )
            }
        }
    }

    private fun observeForgotPasswordApiFlow() = viewModel.forgotPasswordApiFlow.onEach {
        when (it) {
            is ApiCallingState.Loading -> showProgress()
            is ApiCallingState.Success -> {
                hideProgress()
                when {
                    it.value.status.not() -> toast(
                        it.value.message.onEmptyOrNull(
                            getString(
                                R.string.Unexpected_error
                            )
                        )
                    )
                    else -> showResultPopup()
                }
                viewModel.updateToIdleState()
            }
            is ApiCallingState.Failure -> {
                hideProgress()
                it.throwable.message?.let { errorMsg -> toast(errorMsg) }
            }
            else -> Unit
        }
    }.launchIn(fragmentScope)

    private fun isValid(): Boolean = when {
        binding.editEmail.text.isNullOrEmpty() -> {
            toast(getString(R.string.enter_email_id))
            false
        }
        Utils.validate(binding.editEmail.text.toString()).not() -> {
            toast(getString(R.string.enter_valid_email_id))
            false
        }
        else -> true
    }


    private fun showResultPopup() {
        val dialogBuilder = AlertDialog.Builder(activity).create()
        dialogBuilder.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val inflater = layoutInflater
        val dialogView: View = inflater.inflate(R.layout.common_dialog, null)
        val okButton = dialogView.findViewById<MaterialButton>(R.id.okButton)
        val textMessage = dialogView.findViewById<AppCompatTextView>(R.id.messageText)
        textMessage.text =
            resources.getString(R.string.login_forgot_password_message_sent_popup_content)
        okButton.setOnClickListener {
            dialogBuilder.dismiss()
//            val intent: Intent = VerifyOtpScreen.newIntent(activity)
//            intent.putExtra("From", "forgot-password")
//            intent.putExtra("email", String.valueOf(dataBinding.editEmail.getText()))
//            startActivity(intent)
//            requireActivity().overridePendingTransition(0, 0)
//            requireActivity().finish()
            hideKeyboard()
        }
        dialogBuilder.setView(dialogView)
        dialogBuilder.show()

    }

}