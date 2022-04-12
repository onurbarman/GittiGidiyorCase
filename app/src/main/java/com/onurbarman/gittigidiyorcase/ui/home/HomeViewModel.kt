package com.onurbarman.gittigidiyorcase.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onurbarman.gittigidiyorcase.domain.model.HomePageResponse
import com.onurbarman.gittigidiyorcase.domain.model.Output
import com.onurbarman.gittigidiyorcase.domain.model.TokenResponse
import com.onurbarman.gittigidiyorcase.domain.usecase.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val productsUseCase: ProductsUseCase) : ViewModel() {

    val tokenState = MutableStateFlow<Output<TokenResponse>>(Output.loading(null))
    val homePageState = MutableStateFlow<Output<HomePageResponse>>(Output.loading(null))

    init {
        fetchToken()
    }

    private fun fetchToken() {
        viewModelScope.launch {
            productsUseCase.executeToken().collect {
                tokenState.value = it
            }
        }
    }

    fun fetchHomePage(token: String) {
        viewModelScope.launch {
            productsUseCase.executeHomePage(token).collect {
                homePageState.value = it
            }
        }
    }
}