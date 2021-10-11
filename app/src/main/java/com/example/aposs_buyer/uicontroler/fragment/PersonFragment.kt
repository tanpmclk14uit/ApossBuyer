package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentPersonBinding
import com.example.aposs_buyer.uicontroler.activity.AddressActivity
import com.example.aposs_buyer.viewmodel.PersonViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.zip.Inflater

@AndroidEntryPoint
class PersonFragment : Fragment() {

    private lateinit var binding: FragmentPersonBinding
    private val viewModel: PersonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_person, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.isSignIn.observe(viewLifecycleOwner, Observer {
            if (it == true)
            {
                binding.lnNoAccount.visibility = View.GONE
                binding.lnHavingAccount.visibility = View.VISIBLE
            }
            else
            {
                binding.lnNoAccount.visibility = View.VISIBLE
                binding.lnHavingAccount.visibility = View.GONE
            }
        })
        binding.btnAddress.setOnClickListener {
            val intent = Intent(this.context, AddressActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}