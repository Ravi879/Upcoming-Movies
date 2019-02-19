package com.ravi.movies.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ravi.movies.AppEntry
import com.ravi.movies.R
import com.ravi.movies.databinding.ActivityDetailBinding
import com.ravi.movies.domain.model.MovieDetail
import com.ravi.movies.factory.ViewModelFactory
import com.ravi.movies.factory.base.BaseActivity
import com.ravi.movies.ui.adapter.PicassoImageLoadingService
import com.ravi.movies.viewmodel.DetailsVM
import com.ravi.movies.viewmodel.contracts.MovieDetailContract
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail.*
import ss.com.bannerslider.Slider
import javax.inject.Inject

class MovieDetailActivity : BaseActivity(), MovieDetailContract {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailsVM
    private lateinit var toolbarTitle: TextView

    companion object {
        const val MOV_ID = "mov_id"
        const val MOV_NAME = "mov_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        setToolBar()

        initView()
        AppEntry.isNetworkAvailable.observe(this, getNetworkObserver())
        initViewModel()

        val movId = intent.getIntExtra(MOV_ID, 0)
        if (movId != 0) {
            viewModel.loadMovieDetails(movId)
        }
    }

    private fun initView() {
        loadingIndicator = loading_indicator
        networkSnackBar = getNetworkSnackBar(layout_root)

        Slider.init(PicassoImageLoadingService(this))
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsVM::class.java)
        viewModel.contract = this
        viewModel.response.observe(this, Observer { processResponse(it) })
    }

    private fun setToolBar() {
        val appBar = toolbar as Toolbar
        toolbarTitle = appBar.findViewById(R.id.toolbar_title) as TextView
        title = ""

        val movName = intent.getStringExtra(MOV_NAME) ?: getString(R.string.activity_movie_detail_title)
        setSupportActionBar(appBar)
        toolbarTitle.text = movName
    }

    override fun onDetailsLoaded(movieDetail: MovieDetail) {
        binding.movDetail = movieDetail
    }


}
