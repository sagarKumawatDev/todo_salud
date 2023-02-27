package com.octal.todosalud.presentation.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octal.todosalud.R
import com.octal.todosalud.databinding.FragmentDashboardBinding
import com.octal.todosalud.presentation.mainActivity.MainActivity
import com.octal.todosalud.utility.LocaleHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    private val viewModel by viewModels<DashboardFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonOne.setOnClickListener {
            viewModel.logout()
        }
        binding.buttonTwo.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_composeDemoFragment)
        }
        binding.buttonThree.setOnClickListener {
            LocaleHelper.setLocale(requireContext(), "hi")
            val intent = Intent(requireActivity(), MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            requireActivity().finishAffinity()
            startActivity(intent)
        }
        binding.buttonFour.setOnClickListener {
            LocaleHelper.setLocale(requireContext(), "en")
            val intent = Intent(requireActivity(), MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            requireActivity().finishAffinity()
            startActivity(intent)
        }
    }
}
