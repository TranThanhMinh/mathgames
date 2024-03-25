package com.app.mathgames

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.app.myapplication.R


import com.dn.vdp.coloring_book.utils.Utils
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


class MenuFragment:Fragment() {
    lateinit var adRequest: AdRequest
    private final var TAG = "MainActivity"
    private var mInterstitialAd: InterstitialAd? = null
    private  var idInter = 3
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

        val tv_plus_minus = view.findViewById<TextView>(R.id.tv_plus_minus)
        val tv_multiply_divide = view.findViewById<TextView>(R.id.tv_multiply_divide)
        val tv_count_number = view.findViewById<TextView>(R.id.tv_count_number)
        val imgShare = view.findViewById<ImageView>(R.id.img_share)
        val tvVersion = view.findViewById<TextView>(R.id.tv_version)
        val adView = view.findViewById<AdView>(R.id.adView)

        adView.loadAd(adRequest)
       tvVersion.setText("Version: 1.0.7")

        tv_plus_minus.setOnClickListener {
            val action = MenuFragmentDirections.actionLoginToHome("1")
            navigate(action)
        }

        imgShare.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
                var shareMessage = "\nLet me recommend you this application\n\n"
                shareMessage =
                    """
                                   ${shareMessage + "https://play.google.com/store/apps/details?id=com.app.minhdev.mathgames"}


                                   """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {

            }
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

    override fun onResume() {
        super.onResume()
        if (idInter == 3){
            adUnitIdInter()
            idInter = 0
        }
    }



    private fun adUnitIdInter(){
        InterstitialAd.load(requireContext(),Utils.adUnitIdInter, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError?.toString()?.let { Log.d(TAG, it) }
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
                if (mInterstitialAd != null) {
                    mInterstitialAd?.show(requireActivity())
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.")
                }
            }
        })
    }



    private fun navigate(action: NavDirections) {
        idInter++
        findNavController()?.navigate(action)
    }

   internal fun onBackPressed(){
          Toast.makeText(requireContext(),"Login",Toast.LENGTH_SHORT).show()
    }

}