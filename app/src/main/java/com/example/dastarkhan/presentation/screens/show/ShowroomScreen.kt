package com.example.dastarkhan.presentation.screens.show

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dastarkhan.R
import com.example.dastarkhan.data.ImageData
import com.example.dastarkhan.databinding.ScreenShowroomBinding
import com.example.dastarkhan.presentation.adapter.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ShowroomScreen @Inject constructor(): Fragment(R.layout.screen_showroom) {
    private var binding: ScreenShowroomBinding? = null
    private val imgAdapter = ImageAdapter()
    private val viewModel: ShowVM by viewModels<ShowVMImpl>()
    private val list = ArrayList<ImageData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScreenShowroomBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.imgRV.adapter = imgAdapter
        binding!!.imgRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
      requireActivity().window.statusBarColor = Color.parseColor("#641B0D")

        binding!!.imgRV.adapter = imgAdapter
        binding!!.imgRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        list.add(ImageData(R.drawable.rasm))
        list.add(ImageData(R.drawable.rasm2))
        list.add(ImageData(R.drawable.rasm3))
        list.add(ImageData(R.drawable.rasm4))
        list.add(ImageData(R.drawable.rasm5))
        list.add(ImageData(R.drawable.rasm6))
        list.add(ImageData(R.drawable.rasm7))
        list.add(ImageData(R.drawable.rasm8))
        list.add(ImageData(R.drawable.rasm9))
        imgAdapter.submitList(list)
//        viewModel.getImageData()
//        viewModel.imgFlow.onEach {
//            imgAdapter.submitList(it)
//        }.launchIn(lifecycleScope)


        binding!!.food.setOnClickListener {
            findNavController().navigate(R.id.action_showroomScreen_to_categoryScreen)
        }

        binding!!.textile.setOnClickListener {
            findNavController().navigate(R.id.action_showroomScreen_to_textileScreen)
        }
    }


}