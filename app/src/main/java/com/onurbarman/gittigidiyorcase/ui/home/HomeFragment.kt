package com.onurbarman.gittigidiyorcase.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onurbarman.gittigidiyorcase.databinding.FragmentHomeBinding
import com.onurbarman.gittigidiyorcase.domain.model.Output
import com.onurbarman.gittigidiyorcase.ui.MainViewModel
import com.onurbarman.gittigidiyorcase.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {
    private val viewModel: HomeViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var slidersAdapter: SlidersAdapter
    private lateinit var bannersAdapter: BannersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObservers()
    }

    private fun initUi() {
        slidersAdapter = SlidersAdapter(mutableListOf()) { id -> onItemClick(id) }
        bannersAdapter = BannersAdapter(mutableListOf()) { id -> onItemClick(id) }
        binding.run {
            recyclerViewSliders.adapter = slidersAdapter
            recyclerViewSliders.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

            recyclerViewBanners.adapter = bannersAdapter
            recyclerViewBanners.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.run {
                    launch {
                        tokenState.collect {
                            when (it.status) {
                                Output.Status.LOADING -> {
                                    setViewVisibility(Output.Status.LOADING)
                                }
                                Output.Status.SUCCESS -> {
                                    it.data?.let { data ->
                                        mainViewModel.accessToken = data.token
                                        fetchHomePage(data.token)
                                    }
                                }
                                Output.Status.ERROR -> {
                                    setViewVisibility(Output.Status.ERROR, it.error?.statusMessage)
                                }
                            }
                        }
                    }

                    launch {
                        homePageState.collect {
                            when (it.status) {
                                Output.Status.LOADING -> {
                                    setViewVisibility(Output.Status.LOADING)
                                }
                                Output.Status.SUCCESS -> {
                                    it.data?.let { data ->
                                        slidersAdapter.update(data.sliders)
                                        bannersAdapter.update(data.banners)
                                    }
                                    setViewVisibility(Output.Status.SUCCESS)
                                }
                                Output.Status.ERROR -> {
                                    setViewVisibility(Output.Status.ERROR, it.error?.statusMessage)
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    private fun setViewVisibility(status: Output.Status, error: String? = null) {
        binding.run {
            progressDialog.isVisible = status == Output.Status.LOADING
            layoutViews.isVisible = status == Output.Status.SUCCESS
            errorLayout.root.isVisible = status == Output.Status.ERROR
            error?.let {
                errorLayout.labelErrorDescription.text = it
            }
        }
    }

    private fun onItemClick(itemId: Int) {
        findNavController().navigate(
            HomeFragmentDirections
                .actionHomeFragmentToProductDetailFragment(itemId)
        )
    }
}