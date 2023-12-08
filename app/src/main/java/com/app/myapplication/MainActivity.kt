package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.app.myapplication.R

class MainActivity: AppCompatActivity() {
    var navHostFragment:NavHostFragment?=null
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
}