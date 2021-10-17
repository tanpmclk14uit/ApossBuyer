package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aposs_buyer.R
import com.example.aposs_buyer.uicontroler.activity.MainActivity


class SuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Handler().postDelayed(
            {
                startActivity(Intent(requireActivity(), MainActivity::class.java))
                requireActivity().finish()
            },
            3000
        )
        return inflater.inflate(R.layout.fragment_success, container, false)
    }

}