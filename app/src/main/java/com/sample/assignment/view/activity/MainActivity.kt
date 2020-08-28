package com.sample.assignment.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.assignment.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.toolbar.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Fragment will be loaded based on the start destination in navgragh in the layout
    }

    fun setToolbarText(text: String) {
        tool_title.text = text
    }

}