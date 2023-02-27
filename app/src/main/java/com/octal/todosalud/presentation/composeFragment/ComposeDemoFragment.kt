package com.octal.todosalud.presentation.composeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.octal.todosalud.presentation.composeFragment.stateAndEvents.ComposeScreenEvents
import com.octal.todosalud.utility.fragmentScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ComposeDemoFragment : Fragment() {

    val viewModel: ComposeDemoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val coroutinesScope = rememberCoroutineScope()
            val screenState = viewModel.composeScreenState.collectAsState()
            ComposeScreen(screenState){ composeScreenEvents ->
                coroutinesScope.launch {
                   viewModel.emitComposeScreenEvents(composeScreenEvents)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeComposeScreenEvents()
    }

    private fun observeComposeScreenEvents() {
        viewModel.composeScreenEvents.onEach {
            when (it) {
                is ComposeScreenEvents.ShowToast -> Toast.makeText(
                    requireContext(),
                    "Button Clicked",
                    Toast.LENGTH_SHORT
                ).show()
                else -> Unit
            }
        }.launchIn(fragmentScope)
    }

}