package com.example.aposs_buyer.uicontroler.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentMessageBinding
import com.example.aposs_buyer.uicontroler.activity.AboutUsActivity
import com.example.aposs_buyer.uicontroler.activity.NotificationActivity
import com.example.aposs_buyer.uicontroler.activity.SearchActivity
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
        viewModel.size.observe(viewLifecycleOwner, Observer { it ->
            messageAdapter.notifyItemInserted(0)
            binding.rcMessage.scrollToPosition(0)
            Log.d(TAG, "data change call")
        })
        binding.lnAboutUs.setOnClickListener {
            val intent = Intent(this.context, AboutUsActivity::class.java)
            startActivity(intent)
        }
        binding.imgNotification.setOnClickListener {
            val intent = Intent(this.context, NotificationActivity::class.java)
            startActivity(intent)
        }
        binding.searchBar.setOnClickListener {
            val intent = Intent(this.context, SearchActivity::class.java)
            startActivity(intent)
        }
        binding.tietSearchBar.setOnClickListener {
            val intent = Intent(this.context, SearchActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}