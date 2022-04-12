package com.onurbarman.gittigidiyorcase.di

import com.onurbarman.gittigidiyorcase.data.ApiMainHeadersProvider
import com.onurbarman.gittigidiyorcase.data.repository.ProductsRepositoryImpl
import com.onurbarman.gittigidiyorcase.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    internal abstract fun bindProductsRepository(repository: ProductsRepositoryImpl): ProductsRepository
}