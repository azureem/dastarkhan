package com.example.dastarkhan.presentation.screens.category

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dastarkhan.R
import com.example.dastarkhan.databinding.ScreenCategoryBinding
import com.example.dastarkhan.presentation.adapter.FoodAdapter
import com.example.dastarkhan.presentation.adapter.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryScreen : Fragment(R.layout.screen_category) {
    private var binding: ScreenCategoryBinding? = null
    private val viewModel: CategoryVM by viewModels<CategoryVMImpl>()
    private val adapter = FoodAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScreenCategoryBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding!!.myRV.adapter = adapter
        binding!!.myRV.layoutManager = LinearLayoutManager(requireContext())

        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = Color.parseColor("#641B0D")


        lifecycleScope.launch {
            viewModel.progressBar.collect{
                binding!!.progressBar.isVisible = it
            }
        }


        viewModel.getFoodData()
        viewModel.dataFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)


        adapter.itemTouchedFun {
            val myBundle = bundleOf(Pair("name", it.name),
                Pair("img", it.img), Pair("history", it.history),
                Pair("ingredients", it.ingredients),
                Pair("extra", it.extra),
                Pair("preparation", it.preparation),
                Pair("img2", it.img2)
            )
            findNavController().navigate(R.id.action_categoryScreen_to_infoScreen, myBundle)
        }
        binding!!.goBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}