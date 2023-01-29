package com.mecofarid.trending.ui.trending.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mecofarid.trending.android.R
import com.mecofarid.trending.android.databinding.FragmentTrendingBinding
import com.mecofarid.trending.app.appComponent
import com.mecofarid.trending.features.trending.domain.model.Trending
import com.mecofarid.trending.features.trending.ui.UiState
import com.mecofarid.trending.ui.trending.TrendingViewModel

class TrendingFragment : Fragment(){

    private lateinit var binding: FragmentTrendingBinding
    private val viewModel by viewModels<TrendingViewModel> {
        TrendingViewModel.factory(requireActivity().application.appComponent().trendingComponent().getTrendingInteractor())
    }
    private val trendingAdapter by lazy { TrendingAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_trending, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initSuccessAdapter()
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> showSuccess(it.trendingList)
                else -> {}
            }
        }
    }

    private fun initSuccessAdapter(){
        binding.successHolder.dataHolder.apply {
            adapter = trendingAdapter
        }
    }

    private fun showSuccess(trendingList: List<Trending>){
        val trendingView = trendingList.map { TrendingView(it) }
        trendingAdapter.submitList(trendingView)
    }
}
