package com.onurbarman.gittigidiyorcase.ui.product_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onurbarman.gittigidiyorcase.databinding.ItemSliderBinding
import com.onurbarman.gittigidiyorcase.domain.model.HomePageResponse.Slider
import com.onurbarman.gittigidiyorcase.domain.model.ProductDetailResponse.Image
import com.onurbarman.gittigidiyorcase.utils.GlideUtils

/**
 * RecyclerView Adapter to display *Product Images*.
 *
 * @property list the list of Product Images in this Adapter.
 */
class ProductImagesAdapter(
    private val list: MutableList<Image>
) : RecyclerView.Adapter<ProductImagesAdapter.ProductImagesHolder>() {

    /**
     * RecyclerView ViewHolder to display a Product Images.
     *
     * @property binding the binding class item layout.
     */
    inner class ProductImagesHolder(private val binding: ItemSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Method to bind data to layout.
         */
        fun bind(item: Image) {
            binding.run {
                GlideUtils.urlToImageView(imgSlider.context,item.url,imgSlider
                    ,item.width,item.height)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImagesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSliderBinding.inflate(inflater, parent, false)
        return ProductImagesHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductImagesHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    /**
     * Method to update the data set of adapter.
     */
    fun update(newList: List<Image>) {
        list.clear()
        list.addAll(newList)
        notifyItemRangeChanged(0, list.size)
    }
}