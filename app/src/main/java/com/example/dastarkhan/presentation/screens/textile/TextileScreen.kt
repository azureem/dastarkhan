package com.example.dastarkhan.presentation.screens.textile

import android.graphics.Color
import android.os.Bundle
import android.util.Log
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
import com.example.dastarkhan.R
import com.example.dastarkhan.databinding.ScreenCategoryTxtBinding
import com.example.dastarkhan.presentation.adapter.TextileAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TextileScreen @Inject constructor(): Fragment(R.layout.screen_category_txt) {

    private var binding: ScreenCategoryTxtBinding? = null
    private val viewModel: TxtVM by viewModels<TxtVMImpl> ()
    private val adapter = TextileAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScreenCategoryTxtBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding!!.myRV.adapter = adapter
        binding!!.myRV.layoutManager = GridLayoutManager(requireContext(),2)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = Color.parseColor("#641B0D")

        viewModel.getTxt()
        viewModel.flowTxt.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)


//        viewModel.progressBar.onEach {
//            Log.d("UUU", "onViewCreated:${it} ")
//            binding!!.progressBar.isVisible = it
//        }

        lifecycleScope.launch {
            viewModel.progressBar.collect { isVisible ->
                Log.d("UUU", "onViewCreated: $isVisible ")
                binding?.progressBar?.isVisible = isVisible
            }
        }


//        viewModel.progressBar.launchLifecycle(lifecycle, lifecycleScope) {
//            Log.d("UUU", "onViewCreated:${it} ")
//            binding!!.progressBar.isVisible = it
//        }


        adapter.itemTouchedFun {
            val myBundle = bundleOf(
                Pair("name", it.name),
                Pair("img", it.img),
                Pair("description", it.description),
                Pair("history",it.history))
            Log.d("BBB", "lallaal:${it.description} ")
            Log.d("BBB", "lallaal:${it.name} ")
            findNavController().navigate(R.id.action_textileScreen_to_txtInfo, myBundle)
        }
        binding!!.goBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}