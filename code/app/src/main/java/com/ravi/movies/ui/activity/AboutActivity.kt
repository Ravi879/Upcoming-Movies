package com.ravi.movies.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.ravi.movies.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        setToolBar()
    }


    private  fun setToolBar(){
        val appBar = toolbar as Toolbar
        val mTitle = appBar.findViewById(R.id.toolbar_title) as TextView
        title = ""

        setSupportActionBar(appBar)
        mTitle.text = getString(R.string.activity_about_title)

    }
}
