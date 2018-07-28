package com.synowkrz.sportrpg.View

import android.app.Activity
import android.os.Bundle
import com.synowkrz.sportrpg.R
import kotlinx.android.synthetic.main.user_main_layout.*

class UserActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_main_layout)
        loadUserScreen()

    }

    fun loadUserScreen() {
        avatarView.setImageResource(R.drawable.son);
        progressBarView.max = 1000
        progressBarView.progress = 245

    }
}