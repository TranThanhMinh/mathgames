package com.example.myapplication

import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.app.myapplication.R
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.startUpdateFlowForResult
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import javax.crypto.Cipher


class MenuFragment:Fragment() {
    private val MY_REQUEST_CODE = 100

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        checkForUpdate()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tv_plus_minus = view.findViewById<TextView>(R.id.tv_plus_minus)
        val tv_multiply_divide = view.findViewById<TextView>(R.id.tv_multiply_divide)
        val tv_count_number = view.findViewById<TextView>(R.id.tv_count_number)

        tv_plus_minus.setOnClickListener {
            val action = MenuFragmentDirections.actionLoginToHome("1")
            navigate(action)
        }


        tv_multiply_divide.setOnClickListener {
            val action = MenuFragmentDirections.actionLoginToHome("2")
            navigate(action)
        }

        tv_count_number.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuToCountNumber()
            navigate(action)
        }

    }

    private fun navigate(action: NavDirections) {
        findNavController()?.navigate(action)
    }

   internal fun onBackPressed(){
          Toast.makeText(requireContext(),"Login",Toast.LENGTH_SHORT).show()
    }

    fun checkForUpdate() {
        val appUpdateManager = AppUpdateManagerFactory.create(requireContext())

// Returns an intent object that you use to check for an update.
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

// Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                // This example applies an immediate update. To apply a flexible update
                // instead, pass in AppUpdateType.FLEXIBLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                // Request the update.
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.IMMEDIATE,
                    this,
                    MY_REQUEST_CODE
                )
            } else {
                commonLog("update01:Update not available")
            }
        }
    }

    private fun commonLog(message: String) {
        Log.d("tag001", message)
    }

}