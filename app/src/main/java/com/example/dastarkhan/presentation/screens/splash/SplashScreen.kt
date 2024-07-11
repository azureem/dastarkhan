package com.example.dastarkhan.presentation.screens.splash

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dastarkhan.R

class SplashScreen: Fragment(R.layout.screen_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        android.os.Handler().postDelayed({
            findNavController().navigate(R.id.action_splashScreen_to_showroomScreen)
        }, 1500)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

    }
}