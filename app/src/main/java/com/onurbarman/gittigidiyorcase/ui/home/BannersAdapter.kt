package com.onurbarman.gittigidiyorcase.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onurbarman.gittigidiyorcase.databinding.ItemBannerBinding
import com.onurbarman.gittigidiyorcase.domain.model.HomePageResponse.Banner
import com.onurbarman.gittigidiyorcase.utils.GlideUtils
import com.onurbarman.gittigidiyorcase.utils.setOnSafeClickListener

/**
 * RecyclerView Adapter to display *Banners*.
 *
 * @property list the list of Banners in this Adapter.
 * @property onBannerClick is the item click listener.
 */
class BannersAdapter(
    private val list: MutableList<Banner>,
    private val onBannerClick: (id: Int) -> Unit
) : RecyclerView.Adapter<BannersAdapter.BannersHolder>() {

    /**
     * RecyclerView ViewHolder to display a Banner.
     *
     * @property binding the binding class item layout.
     */
    inner class BannersHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Method to bind data to layout.
         */
        fun bind(item: Banner) {
            binding.run {
                root.setOnSafeClickListener {
                    onBannerClick.invoke(item.id)
                }

                GlideUtils.urlToImageView(imgBanner.context,item.image.url,imgBanner
                    ,item.image.width,item.image.height)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBannerBinding.inflate(inflater, parent, false)
        return BannersHolder(binding)
    }

    override fun onBindViewHolder(holder: BannersHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    /**
     * Method to update the data set of adapter.
     */
    fun update(newList: List<Banner>) {
        list.clear()
        list.addAll(newList)
        notifyItemRangeChanged(0, list.size)
    }
}