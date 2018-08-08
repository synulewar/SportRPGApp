package com.synowkrz.sportrpg.View

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.synowkrz.sportrpg.R
import kotlinx.android.synthetic.main.fragment_choose_training.view.*

class ChooseTrainingFragment : Fragment() {

    lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_choose_training, container, false)
        rootView.runButton.setOnClickListener { Toast.makeText(context, "Run", Toast.LENGTH_LONG).show() }
        rootView.bikeButton.setOnClickListener { Toast.makeText(context, "Bike", Toast.LENGTH_LONG).show() }
        rootView.walkButton.setOnClickListener { Toast.makeText(context, "Walk", Toast.LENGTH_LONG).show() }
        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context != null) {
            mContext = context
        }

    }
}