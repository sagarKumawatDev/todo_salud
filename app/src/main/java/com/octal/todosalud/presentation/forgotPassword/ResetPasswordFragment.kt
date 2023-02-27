package com.octal.todosalud.presentation.forgotPassword

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.octal.todosalud.R
import com.octal.todosalud.databinding.FragmentResetPasswordBinding
import com.octal.todosalud.domain.entities.ResetPasswordRequest
import com.octal.todosalud.utility.*
import com.octal.todosalud.utility.extensions.showValidationDialog
import com.octal.todosalud.utility.extensions.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentResetPasswordBinding
    private val viewModel by viewModels<ResetPasswordViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeResetPasswordApiFlow()
        setClickListener()
    }

    private fun setClickListener() {
        binding.updateButton.setOnClickListener {
            if (isValid()) {
                viewModel.callResetPasswordApi(
                    ResetPasswordRequest(
                        email = "",//get from nav args
                        newPassword = binding.editPassword.text.toString(),
                        confirmPassword = binding.editResetPassword.text.toString()
                    )
                )
            }
        }
    }

    private fun observeResetPasswordApiFlow() = viewModel.resetPasswordApiFlow.onEach {
        when (it) {
            is ApiCallingState.Loading -> showProgress()
            is ApiCallingState.Success -> {
                hideProgress()
                toast(
                    it.value.message.onEmptyOrNull(
                        getString(
                            R.string.Unexpected_error
                        )
                    )
                )
                viewModel.updateToIdleState()
            }
            is ApiCallingState.Failure -> {
                hideProgress()
                it.throwable.message?.let { errorMsg -> toast(errorMsg) }
            }
            else -> Unit
        }
    }.launchIn(fragmentScope)

    fun isValid(): Boolean = when {
        binding.editPassword.text.isNullOrEmpty() || binding.editResetPassword.text.isNullOrEmpty() -> {
            showValidationDialog()
            false
        }
        binding.editPassword.text != binding.editResetPassword.text -> {
            showErrorDialog()
            false
        }
        else -> true
    }


    private fun showErrorDialog() {
        val dialogBuilder = AlertDialog.Builder(requireActivity()).create()
        val inflater = layoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_layout, null)
        val textViewLeft: TextView = dialogView.findViewById(R.id.text_left)
        val textViewRight: TextView = dialogView.findViewById(R.id.text_right)
        val textViewTitle: TextView = dialogView.findViewById(R.id.text_title)
        val textViewContent: TextView = dialogView.findViewById(R.id.content_text_view)
        textViewTitle.setText(resources.getString(R.string.register_password_problem_popup_heading))
        textViewContent.setText(resources.getString(R.string.register_password_problem_popup_content))
        textViewLeft.setVisibility(View.GONE)
        textViewRight.setText(resources.getString(R.string.register_password_problem_popup_accept))
        textViewRight.setOnClickListener { view -> dialogBuilder.dismiss() }
        dialogBuilder.setView(dialogView)
        dialogBuilder.show()
    }

}