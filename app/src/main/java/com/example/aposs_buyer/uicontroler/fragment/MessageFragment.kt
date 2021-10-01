package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentMessageBinding
import com.example.aposs_buyer.uicontroler.adapter.MessageAdapter
import com.example.aposs_buyer.viewmodel.MessageViewModel
import kotlinx.android.synthetic.main.fragment_message.*

class MessageFragment : Fragment() {

    private lateinit var binding:FragmentMessageBinding
    private val viewModel: MessageViewModel by lazy {
        ViewModelProvider(this).get(MessageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_message, container, false)
        binding.viewModel= viewModel
        binding.lifecycleOwner =this
        binding.rcMessage.adapter = MessageAdapter()
        binding.rcMessage.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, true);
        return binding.root
    }


}