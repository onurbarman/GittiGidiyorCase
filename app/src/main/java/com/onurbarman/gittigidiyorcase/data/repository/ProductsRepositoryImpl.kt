package com.onurbarman.gittigidiyorcase.data.repository


import com.onurbarman.gittigidiyorcase.data.remote.ProductsRemoteDataSource
import com.onurbarman.gittigidiyorcase.domain.model.HomePageResponse
import com.onurbarman.gittigidiyorcase.domain.model.Output
import com.onurbarman.gittigidiyorcase.domain.model.ProductDetailResponse
import com.onurbarman.gittigidiyorcase.domain.model.TokenResponse
import com.onurbarman.gittigidiyorcase.domain.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Implementation of ProductsRepository
 * @param productsRemoteDataSource the object of remote data source
 */
internal class ProductsRepositoryImpl @Inject constructor(
    private val productsRemoteDataSource: ProductsRemoteDataSource
) : ProductsRepository {

    override suspend fun fetchToken(): Flow<Output<TokenResponse>> {
        return flow {
            emit(Output.loading())
            val result = productsRemoteDataSource.fetchToken()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchHomePage(token: String): Flow<Output<HomePageResponse>> {
        return flow {
            emit(Output.loading())
            val result = productsRemoteDataSource.fetchHomePage(token)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchProductDetail(token: String,id: Int): Flow<Output<ProductDetailResponse>> {
        return flow {
            emit(Output.loading())
            val result = productsRemoteDataSource.fetchProductDetail(token,id)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}