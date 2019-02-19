package com.ravi.movies.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ravi.movies.AppEntry
import com.ravi.movies.R
import com.ravi.movies.factory.ViewModelFactory
import com.ravi.movies.factory.base.BaseActivity
import com.ravi.movies.ui.adapter.MovieListAdapter
import com.ravi.movies.viewmodel.MainListVM
import com.ravi.movies.viewmodel.contracts.MovieListContract
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MovieListActivity : BaseActivity(), MovieListContract {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var movieAdapter: MovieListAdapter
    private lateinit var rvMovieList: RecyclerView
    private lateinit var controller: LayoutAnimationController

    private lateinit var viewModel: MainListVM


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setToolBar()

        initView()
        AppEntry.isNetworkAvailable.observe(this, super.getNetworkObserver())
        initViewModel()

        viewModel.loadMovies()

    }

    private fun setToolBar() {
        val appBar = toolbar as Toolbar
        val mTitle = appBar.findViewById(R.id.toolbar_title) as TextView
        title = ""

        setSupportActionBar(appBar)
        mTitle.text = getString(R.string.activity_movie_list_title)

    }

    private fun initView() {
        //Default things for loading and n/w state feature,  that must be initialized
        super.loadingIndicator = loading_indicator
        super.networkSnackBar = getNetworkSnackBar(layout_root)

        movieAdapter = MovieListAdapter(this)

        controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
        rvMovieList = rv_movie_list
        with(rvMovieList) {
            adapter = movieAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            layoutAnimation = controller
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainListVM::class.java)
        viewModel.contract = this
        viewModel.response.observe(this, Observer { processResponse(it) })

        viewModel.movieList.observe(this, Observer {
            movieAdapter.setData(it)
            runLayoutAnimation(rvMovieList)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movie_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_about -> startActivity(Intent(this, AboutActivity::class.java))
        }
        return false
    }

    override fun onItemClick(view: View, position: Int) {
        val movie = viewModel.movieList.value!![position]
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOV_ID, movie.id)
        intent.putExtra(MovieDetailActivity.MOV_NAME, movie.title)
        startActivity(intent)
        //toast("Item : $position")
    }

    private fun runLayoutAnimation(recyclerView: RecyclerView) {
        recyclerView.scheduleLayoutAnimation()
    }

}

