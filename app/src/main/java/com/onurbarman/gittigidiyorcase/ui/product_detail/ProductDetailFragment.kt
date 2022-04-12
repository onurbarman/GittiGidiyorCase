package com.onurbarman.gittigidiyorcase.ui.product_detail

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onurbarman.gittigidiyorcase.R
import com.onurbarman.gittigidiyorcase.databinding.FragmentProductDetailBinding
import com.onurbarman.gittigidiyorcase.domain.model.Output
import com.onurbarman.gittigidiyorcase.domain.model.ProductDetailResponse
import com.onurbarman.gittigidiyorcase.ui.MainViewModel
import com.onurbarman.gittigidiyorcase.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>(
    FragmentProductDetailBinding::inflate
) {
    private val viewModel: ProductDetailViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var productImagesAdapter: ProductImagesAdapter

    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObservers()
        fetchProduct()
    }

    private fun fetchProduct() {
        viewModel.fetchProductDetail(mainViewModel.accessToken,args.itemId)
    }

    private fun initUi() {
        productImagesAdapter = ProductImagesAdapter(mutableListOf())
        binding.run {
            recyclerViewProductImages.adapter = productImagesAdapter
            recyclerViewProductImages.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.run {
                    launch {
                        productState.collect {
                            when (it.status) {
                                Output.Status.LOADING -> {
                                    setViewVisibility(Output.Status.LOADING)
                                }
                                Output.Status.SUCCESS -> {
                                    it.data?.let { data ->
                                        updateUi(data)
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

    private fun updateUi(data: ProductDetailResponse) {
        productImagesAdapter.update(data.images)
        binding.run {
            data.run {
                textTitle.text = title
                textSubtitle.text = subTitle
                textDescription.text = description
                textSeller.text = seller.nick
                textDiscountedPrice.text = getString(R.string.price_text, discountedPrice.toString())
                textPrice.text = getString(R.string.price_text, price.toString())
                textPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                textDiscountRate.text = getString(R.string.discount_text, discountRate)
                textRemainingCount.text = getString(R.string.remaining_count_text, remainingCount.toString())
                textStatus.setTextColor(ContextCompat.getColor(requireContext(),
                    if (active) R.color.teal_200 else R.color.colorPassive))
                textStatus.text = if (active) getString(R.string.product_active) else getString(R.string.product_passive)
            }
        }
    }

    private fun setViewVisibility(status: Output.Status, error: String? = null) {
        binding.run {
            progressDialog.isVisible = status == Output.Status.LOADING
            layoutView.isVisible = status == Output.Status.SUCCESS
            errorLayout.root.isVisible = status == Output.Status.ERROR
            error?.let {
                errorLayout.labelErrorDescription.text = it
            }
        }
    }

}