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
import com.dn.vdp.coloring_book.utils.Utils
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
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
    lateinit var interstitialAd: InterstitialAd
    lateinit var adRequest: AdRequest
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // initializing our mobile ads.
        MobileAds.initialize(requireContext())

        // on below line we are
        // initializing our ad request.
        adRequest = AdRequest.Builder().build()


        adUnitIdInter()


        val tv_plus_minus = view.findViewById<TextView>(R.id.tv_plus_minus)
        val tv_multiply_divide = view.findViewById<TextView>(R.id.tv_multiply_divide)
        val tv_count_number = view.findViewById<TextView>(R.id.tv_count_number)
        val adView = view.findViewById<AdView>(R.id.adView)

        adView.loadAd(adRequest)

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

    fun adUnitIdInter(){
        // on below line we are
        // initializing our interstitial ad.
        interstitialAd = InterstitialAd(requireContext())

        // on below line we are setting ad
        // unit id for our interstitial ad.
        interstitialAd.adUnitId = Utils.adUnitIdInter

        // on below line we are loading
        // our ad with ad request
        interstitialAd.loadAd(adRequest)

        // on below line we are setting ad
        // listener for our interstitial ad.
        interstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // on below line we are calling display
                // ad function to display interstitial ad.
                displayInterstitialAd(interstitialAd)
            }
        }
    }

    // on below line we are creating a
// function for displaying interstitial ad.
    private fun displayInterstitialAd(interstitialAd: InterstitialAd) {
        // on below line we are
        // checking if the ad is loaded
        if (interstitialAd.isLoaded) {
            // if the ad is loaded we are displaying
            // interstitial ad by calling show method.
            interstitialAd.show()
        }
    }

    private fun navigate(action: NavDirections) {
        findNavController()?.navigate(action)
    }

   internal fun onBackPressed(){
          Toast.makeText(requireContext(),"Login",Toast.LENGTH_SHORT).show()
    }

}