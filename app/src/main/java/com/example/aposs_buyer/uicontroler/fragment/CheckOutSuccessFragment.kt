package com.example.aposs_buyer.uicontroler.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentFinishCheckOutBinding
import com.example.aposs_buyer.uicontroler.activity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CheckOutSuccessFragment : Fragment() {

    private lateinit var binding: FragmentFinishCheckOutBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_finish_check_out, container, false)
        val intent = Intent(this.context, MainActivity::class.java)
        binding.btnContinue.setOnClickListener {
            startActivity(intent)
        }
        CoroutineScope(Dispatchers.Main).launch {
            var i = 3
            while (i > 0) {
                binding.btnContinue.text =
                    resources.getString(R.string.continue_shopping) + " " + i + " ..."
                i--
                delay(1000)
            }
            startActivity(intent)
        }

        return binding.root
    }
}