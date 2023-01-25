package com.mecofarid.trending.ui.trending.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mecofarid.trending.android.R
import com.mecofarid.trending.android.databinding.FragmentTrendingBinding
import com.mecofarid.trending.app.appComponent
import com.mecofarid.trending.domain.features.trending.domain.model.Trending
import com.mecofarid.trending.domain.features.trending.ui.UiState
import com.mecofarid.trending.ui.trending.TrendingViewModel

class TrendingFragment : Fragment(){

//    private lateinit var binding: FragmentTrendingBinding
//    private val viewModel by viewModels<TrendingViewModel> {
//        TrendingViewModel.factory(requireActivity().application.appComponent().repoComponent().getRepoInteractor())
//    }
//    private val trendingAdapter by lazy { TrendingAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding.viewModel = viewModel
//        binding.lifecycleOwner = this
//        initSuccessAdapter()
//        observeViewModel()
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_trending, container, false)
//        return binding.root
//    }
//
//    private fun observeViewModel() {
//        viewModel.liveUiState.observe(this) {
//            when (it) {
//                is UiState.Success -> showSuccess(it.repos)
//                else -> {}
//            }
//        }
//    }
//
//    private fun initSuccessAdapter(){
//        binding.successHolder.dataHolder.apply {
//            adapter = trendingAdapter
//        }
//    }
//
//    private fun showSuccess(repos: List<Trending>){
//        val trendingView = repos.map { TrendingView(it) }
//        trendingAdapter.submitList(trendingView)
//    }
}
