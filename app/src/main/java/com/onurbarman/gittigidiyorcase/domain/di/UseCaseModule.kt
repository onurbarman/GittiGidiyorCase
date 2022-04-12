package com.onurbarman.gittigidiyorcase.domain.di

import com.onurbarman.gittigidiyorcase.domain.usecase.ProductsUseCase
import com.onurbarman.gittigidiyorcase.domain.usecase.ProductsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    @Singleton
    internal abstract fun bindProductsUseCase(useCaseImpl: ProductsUseCaseImpl): ProductsUseCase
}