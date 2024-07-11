package com.example.dastarkhan.presentation.screens.info

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.dastarkhan.R
import com.example.dastarkhan.databinding.ScreenInfoBinding

class InfoScreen: Fragment(R.layout.screen_info) {

    private var binding: ScreenInfoBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = ScreenInfoBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = Color.parseColor("#641B0D")


        val comingName = arguments?.getString("name")
            val comingImg = arguments?.getString("img")
            val comingImg2 = arguments?.getString("img2")
            val comingHistory = arguments?.getString("history")
            val comingIngredients = arguments?.getString("ingredients")
            val comingExtra = arguments?.getString("extra")
            val comingPreparation = arguments?.getString("preparation")

        Glide.with(binding!!.root)
            .load(comingImg2)
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    binding!!.progressBar.visibility = View.GONE
                    return false
                }

                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                    binding!!.progressBar.visibility = View.GONE
                    return false
                }

//                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstAttempt: Boolean): Boolean {
//                    // No error handling needed
//                    binding!!.progressBar.visibility = View.GONE  // Hide progress bar even on failure
//                    return false
//                }
            })
            .into(binding!!.imageHolder)

        binding!!.history.text = comingHistory.toString()
        binding!!.ingredients.text = comingIngredients.toString()
        binding!!.extra.text = comingExtra.toString()
        binding!!.foodName.text = comingName.toString()
        binding!!.historyName.text = "Evolution of " + comingName.toString()
        binding!!.preparation.text = comingPreparation.toString()


        binding!!.goBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}