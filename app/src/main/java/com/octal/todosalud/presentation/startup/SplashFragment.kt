package com.octal.todosalud.presentation.startup

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octal.todosalud.R

import com.octal.todosalud.databinding.FragmentStartUpBinding
import com.octal.todosalud.utility.fragmentScope

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentStartUpBinding
    private val viewModel by viewModels<SplashFragmentViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            openNextScreen()
        }, 2000)
    }

    private fun openNextScreen() {
        fragmentScope.launch {
           /* if (viewModel.isUserLoggedIn()) {
                findNavController().navigate(R.id.action_startUpFragment_to_dashboardFragment)
            } else {
                findNavController().navigate(R.id.action_startUpFragment_to_loginFragment)
            }*/
            findNavController().navigate(R.id.action_startUpFragment_to_loginFragment)
        }
    }
}
