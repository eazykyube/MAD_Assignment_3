package com.example.android.lasttorture.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.lasttorture.LastTortureApplication
import com.example.android.lasttorture.R
import com.example.android.lasttorture.viewmodel.SearchViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as LastTortureApplication).appComponent.inject(this)
    }
}
