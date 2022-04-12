package com.onurbarman.gittigidiyorcase.domain.model

data class HomePageResponse(
    val banners: List<Banner>,
    val sliders: List<Slider>
) {
    data class Banner(
        val id: Int,
        val image: Image
    )

    data class Slider(
        val id: Int,
        val image: Image
    )

    data class Image(
        val height: Int,
        val url: String,
        val width: Int
    )
}