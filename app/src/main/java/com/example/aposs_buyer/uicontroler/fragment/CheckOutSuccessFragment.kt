package com.example.aposs_buyer.uicontroler.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentFinishCheckOutBinding
import com.example.aposs_buyer.uicontroler.activity.MainActivity
import com.example.aposs_buyer.uicontroler.activity.OrderActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CheckOutSuccessFragment : Fragment() {

    private lateinit var binding: FragmentFinishCheckOutBinding
    private var isGoToOrderActivity = false

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_finish_check_out, container, false)

        binding.checkOrder.setOnClickListener {
            goToOrderActivity()
        }

        binding.btnContinue.setOnClickListener {
            goToMainActivity()
        }
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            var i = 5
            while (i > 0) {
                binding.btnContinue.text =
                    resources.getString(R.string.continue_shopping) + " " + i + " ..."
                i--
                delay(1000)
            }
            if(!isGoToOrderActivity) {
                goToMainActivity()
            }
        }

        return binding.root
    }

    private fun goToOrderActivity(){
        isGoToOrderActivity = true
        val intent = Intent(this.context, OrderActivity::class.java)
        startActivity(intent)
        this.requireActivity().finish()
    }

    private fun goToMainActivity(){
        isGoToOrderActivity = true
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        this.requireActivity().finish()
    }
}