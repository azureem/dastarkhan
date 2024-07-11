package com.example.dastarkhan.presentation.screens.txtInfo

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
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
import com.example.dastarkhan.databinding.ScreenTxtinfoBinding
import javax.sql.DataSource
import kotlin.math.log

class TxtInfo : Fragment(R.layout.screen_txtinfo) {
    private var binding: ScreenTxtinfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScreenTxtinfoBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val comingImg = arguments?.getString("img")
        val comingDescription = arguments?.getString("description")
        Log.d("BBB", "onViewCreated: ${comingDescription}")
        val comingHistory = arguments?.getString("history")
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = Color.parseColor("#641B0D")

        val comingName = arguments?.getString("name")


        Glide.with(binding!!.root)
            .load(comingImg)
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


        binding!!.description.text = comingDescription.toString()
        binding!!.history.text = comingHistory.toString()
        binding!!.foodName.text = comingName.toString()
        binding!!.historyTitle.text = "Evolution of " + comingName.toString()


        binding!!.goBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}