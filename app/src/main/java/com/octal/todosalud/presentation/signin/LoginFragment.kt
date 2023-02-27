package com.octal.todosalud.presentation.signin

import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octal.todosalud.R
import com.octal.todosalud.databinding.FragmentLoginFragmentBinding
import com.octal.todosalud.domain.entities.LoginRequest
import com.octal.todosalud.utility.*
import com.octal.todosalud.utility.extensions.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginFragmentBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
        observeLoginFlow()
    }

    private fun setClickListener() {
        binding.apply {
            textForgetPassword.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_forgotPassword)
            }
            textRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
            }
            textEnter.setOnClickListener {

                binding.apply {
                    if (validateEntries(
                            email = editEmail.text.toString(),
                            pass = editPassword.text.toString()
                        )
                    ) {
                        viewModel.callLoginApi(
                            LoginRequest(
                                email = editEmail.toString(),
                                password = editPassword.toString(),
                            )
                        )
                    }
                }

            }
            textSkip.setOnClickListener {

            }
            gSignIn.setOnClickListener {

            }
            linearFacebookGoogle.setOnClickListener {

            }
        }
    }

    private fun validateEntries(email: String, pass: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            toast(resources.getString(R.string.email_field_empty))
            false
        } else if (!Utils.validate(email)) {
            toast(resources.getString(R.string.enter_valid_email_id))
            false
        } else if (TextUtils.isEmpty(email)) {
            toast(resources.getString(R.string.password_field_empty))
            false
        } else {
            true
        }
    }
    private fun observeLoginFlow() {
        viewModel.loginFlow.onEach {
            when (it) {
                is ApiCallingState.Loading -> {
                 showProgress()
                }
                is ApiCallingState.Success -> {

                    /* toast(it.value.message)
                     val bundle = Bundle().apply {
                         putString("country_code", it.value.countryCode)
                         putString("phone", it.value.phone)
                         putString("otp", it.value.phoneOtpNumber.toString())
                     }*/
                    when {
                        it.value.success && it.value.data?.isEmailVerified ==true -> {
                            //Navigate
                        }
                        else -> {
                            hideProgress()
                            toast(message = it.value.message)
                        }
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
    }
}
