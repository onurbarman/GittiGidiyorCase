package com.onurbarman.gittigidiyorcase.domain.model

data class ProductDetailResponse(
    val active: Boolean,
    val description: String,
    val discountRate: String,
    val discountedPrice: Int,
    val images: List<Image>,
    val price: Int,
    val remainingCount: Int,
    val seller: Seller,
    val soldCount: Int,
    val subTitle: String,
    val title: String
) {
    data class Image(
        val height: Int,
        val url: String,
        val width: Int
    )

    data class Seller(
        val nick: String
    )
}