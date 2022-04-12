package com.onurbarman.gittigidiyorcase.ui.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onurbarman.gittigidiyorcase.domain.model.Output
import com.onurbarman.gittigidiyorcase.domain.model.ProductDetailResponse
import com.onurbarman.gittigidiyorcase.domain.usecase.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val productsUseCase: ProductsUseCase) : ViewModel() {

    val productState = MutableStateFlow<Output<ProductDetailResponse>>(Output.loading(null))

    fun fetchProductDetail(token: String, id: Int) {
        viewModelScope.launch {
            productsUseCase.executeProductDetail(token, id).collect {
                productState.value = it
            }
        }
    }

}