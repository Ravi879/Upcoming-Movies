package com.ravi.movies.ui.adapter

import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

class MainSliderAdapter(private val movieImages: ArrayList<String>) : SliderAdapter() {

    override fun getItemCount(): Int {
        return movieImages.size
    }

    override fun onBindImageSlide(position: Int, viewHolder: ImageSlideViewHolder) {
        viewHolder.bindImageSlide(movieImages[position])
    }
}