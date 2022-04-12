package com.onurbarman.gittigidiyorcase.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onurbarman.gittigidiyorcase.databinding.ItemSliderBinding
import com.onurbarman.gittigidiyorcase.domain.model.HomePageResponse.Slider
import com.onurbarman.gittigidiyorcase.utils.GlideUtils
import com.onurbarman.gittigidiyorcase.utils.setOnSafeClickListener

/**
 * RecyclerView Adapter to display *Sliders*.
 *
 * @property list the list of Sliders in this Adapter.
 * @property onSliderClick is the item click listener.
 */
class SlidersAdapter(
    private val list: MutableList<Slider>,
    private val onSliderClick: (id: Int) -> Unit
) : RecyclerView.Adapter<SlidersAdapter.SlidersHolder>() {

    /**
     * RecyclerView ViewHolder to display a Slider.
     *
     * @property binding the binding class item layout.
     */
    inner class SlidersHolder(private val binding: ItemSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Method to bind data to layout.
         */
        fun bind(item: Slider) {
            binding.run {
                root.setOnSafeClickListener {
                    onSliderClick.invoke(item.id)
                }

                GlideUtils.urlToImageView(imgSlider.context,item.image.url,imgSlider
                    ,item.image.width,item.image.height)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlidersHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSliderBinding.inflate(inflater, parent, false)
        return SlidersHolder(binding)
    }

    override fun onBindViewHolder(holder: SlidersHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    /**
     * Method to update the data set of adapter.
     */
    fun update(newList: List<Slider>) {
        list.clear()
        list.addAll(newList)
        notifyItemRangeChanged(0, list.size)
    }
}