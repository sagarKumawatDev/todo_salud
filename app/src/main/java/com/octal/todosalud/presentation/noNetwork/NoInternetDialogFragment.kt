package com.octal.todosalud.presentation.noNetwork

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.octal.todosalud.databinding.FragmentNoInternetDialogBinding

class NoInternetDialogFragment : DialogFragment() {

    lateinit var binding: FragmentNoInternetDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_DeviceDefault_Light)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentNoInternetDialogBinding.inflate(inflater, container, false)
        .also { binding = it }.root

}