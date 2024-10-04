package com.cse5236.headsUpStudy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class HowToPlayFragment: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("HowToFragment", "onCreateView called")

        val rootView: View = inflater.inflate(R.layout.how_to_play, container, false)

        val exitButton = rootView.findViewById<Button>(R.id.exit_button)
        exitButton.setOnClickListener{
            dismiss()
        }

        return rootView
    }

    override fun onStart() {
        super.onStart()
        Log.d("HowToFragment", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("HowToFragment", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("HowToFragment", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("HowToFragment", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("HowToFragment", "onDestroy called")
    }

}