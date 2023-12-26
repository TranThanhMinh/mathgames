package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.app.myapplication.R
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.startUpdateFlowForResult

class MainActivity: AppCompatActivity() {
    var navHostFragment:NavHostFragment?=null
    private val MY_REQUEST_CODE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  val host = NavHostFragment.create(R.navigation.navigation_main)
      //  supportFragmentManager.beginTransaction().replace(R.id.fragment_container, host).setPrimaryNavigationFragment(host).commit()
         navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val inflater = navHostFragment?.navController?.navInflater
        val graph = inflater?.inflate(R.navigation.navigation_main)
        if (true){
            graph?.setStartDestination(R.id.menuFragment)
        }else {
            graph?.setStartDestination(R.id.homeFragment)
        }

        val navController = navHostFragment?.navController
        navController?.setGraph(graph!!,intent.extras)
        checkForUpdate()
    }
    fun checkForUpdate() {
        val appUpdateManager = AppUpdateManagerFactory.create(this)

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

    override fun onBackPressed() {
//        val currentFragment = navHostFragment?.childFragmentManager?.primaryNavigationFragment
//        when(currentFragment){
//            is LoginFragment->{
//                // currentFragment.onBackPressed()
//                supportFragmentManager.popBackStack()
//            }
//
//            is HomeFragment->{
//              //  currentFragment.onBackPressed()
//                supportFragmentManager.popBackStack()
//            }
//            else -> {
//               onBackPressedDispatcher.onBackPressed()
//            }
//        }
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }



    private fun commonLog(message: String) {
        Log.d("tag001", message)
    }

}