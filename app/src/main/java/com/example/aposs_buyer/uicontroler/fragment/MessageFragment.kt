package com.example.aposs_buyer.uicontroler.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentMessageBinding
import com.example.aposs_buyer.uicontroler.adapter.MessageAdapter
import com.example.aposs_buyer.viewmodel.MessageViewModel

class MessageFragment : Fragment() {

    private val TAG ="MessageFragment"
    private lateinit var binding:FragmentMessageBinding
    private val messageAdapter = MessageAdapter()
    private val viewModel: MessageViewModel by lazy {
        ViewModelProvider(this).get(MessageViewModel::class.java)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_message, container, false)
        binding.viewModel= viewModel
        binding.lifecycleOwner =this
        binding.rcMessage.adapter =  messageAdapter
        binding.rcMessage.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, true);
        viewModel.lstMessageItem.observe(viewLifecycleOwner, Observer { it ->
            messageAdapter.submitList(it)
            messageAdapter.notifyItemInserted(viewModel.lstMessageItem.value!!.size)
            Log.d(TAG, "data change call")
        })
        return binding.root
    }


}