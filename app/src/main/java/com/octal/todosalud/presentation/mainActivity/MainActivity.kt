package com.octal.todosalud.presentation.mainActivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.octal.todosalud.R
import com.octal.todosalud.data.network.connectionManager.ConnectivityObserver
import com.octal.todosalud.databinding.ActivityMainBinding
import com.octal.todosalud.databinding.LayoutProgressBinding
import com.octal.todosalud.domain.appEvents.AppEvents
import com.octal.todosalud.domain.appEvents.AppEventsHandler
import com.octal.todosalud.presentation.noNetwork.NoInternetDialogFragment
import com.octal.todosalud.utility.ApiCallingState
import com.octal.todosalud.utility.LocaleHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainActivityViewModel>()
    private lateinit var progressBinding: LayoutProgressBinding
    @Inject
    lateinit var appEventsHandler: AppEventsHandler

    @Inject
    lateinit var connectivityObserver: ConnectivityObserver

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.callApi()
        navigationController =
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
        observeAppEvents()
        addProgressToViewGroup()
        internetConnectionHandle()
    }



    private fun observeAppEvents() {
        appEventsHandler.appEventsFlow.onEach {
            when (it) {
                AppEvents.Logout -> performLogout()
                AppEvents.Login -> performLogin()
            }
        }.launchIn(lifecycleScope)
    }

    private fun performLogout() {
        viewModel.logoutClearData()
       // navigationController.navigate(R.id.moveToLoginScreen)
    }

    private fun performLogin() {
        viewModel.userLoggedIn()
    }

    private fun internetConnectionHandle(){
        val noInternetDialog = NoInternetDialogFragment()
        connectivityObserver.observe().onEach {
            if (it == ConnectivityObserver.Status.Lost || it == ConnectivityObserver.Status.Unavailable) {
                noInternetDialog.show(supportFragmentManager, "No Internet")
            } else if (it == ConnectivityObserver.Status.Available && noInternetDialog.isVisible) {
                noInternetDialog.dismiss()
            }
        }.launchIn(lifecycleScope)
        if(connectivityObserver.isNetworkAvailable.not()){
            noInternetDialog.show(supportFragmentManager, "No Internet")
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase))
    }

    private fun addProgressToViewGroup() {
        progressBinding = LayoutProgressBinding.inflate(layoutInflater)
        val v = this.findViewById<View>(android.R.id.content).rootView
        val viewGroup = v as ViewGroup
        viewGroup.addView(progressBinding.root)
    }

    fun showProgress() {
        runOnUiThread {
            progressBinding.root.visibility = View.VISIBLE
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        }
    }

    fun hideProgress() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        runOnUiThread {
            progressBinding.root.visibility = View.GONE
        }
    }
}
