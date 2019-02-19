package com.ravi.movies.ui.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ravi.movies.ui.adapter.MainSliderAdapter
import com.ravi.movies.util.MovieUtil
import com.squareup.picasso.Picasso
import ss.com.bannerslider.Slider


object DBMovieDetail {
    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun ImageView.loadImage(url: String) {
        Picasso.get()
            .load(MovieUtil.getPosterUrl(url))
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("app:sliderAdapter")
    fun Slider.setSliderAdapter(imageUrlList: ArrayList<String>?) {
        imageUrlList?.let {
            if (it.size > 0) {
                this.setAdapter(MainSliderAdapter(it))
                this.setInterval(4000)
            }
        }
    }

}