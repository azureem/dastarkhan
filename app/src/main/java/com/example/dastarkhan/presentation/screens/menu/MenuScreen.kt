package com.example.dastarkhan.presentation.screens.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dastarkhan.R
import com.example.dastarkhan.databinding.ScreenMenuBinding

class MenuScreen : Fragment(R.layout.screen_menu) {

    private var binding: ScreenMenuBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScreenMenuBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        binding!!.about.setOnClickListener {
//            findNavController().navigate(R.id.action_menuScreen_to_aboutScreen)
//        }
//        binding!!.open.setOnClickListener {
//            findNavController().navigate(R.id.action_menuScreen_to_showroomScreen)
//        }
//    }
}