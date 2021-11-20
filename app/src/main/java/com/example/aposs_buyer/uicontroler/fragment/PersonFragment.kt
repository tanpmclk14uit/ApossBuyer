package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentPersonBinding
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.uicontroler.activity.*
import com.example.aposs_buyer.viewmodel.PersonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonFragment : Fragment() {

    private lateinit var binding: FragmentPersonBinding
    private val viewModel: PersonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.isSignIn.value =
            AccountDatabase.getInstance(this.requireContext()).accountDao.getAccount() != null
        viewModel.isSignIn.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.lnNoAccount.visibility = View.GONE
                binding.lnHavingAccount.visibility = View.VISIBLE
            } else {
                binding.lnNoAccount.visibility = View.VISIBLE
                binding.lnHavingAccount.visibility = View.GONE
            }
        })
        binding.btnAddress.setOnClickListener {
            val intent = Intent(this.context, AddressActivity::class.java)
            startActivity(intent)
        }
        binding.btnRating.setOnClickListener {
            val intent = Intent(this.context, RatingActivity::class.java)
            startActivity(intent)
        }
        binding.order.setOnClickListener {
            startActivity(Intent(this.context, OrderActivity::class.java))
        }
        binding.signOut.setOnClickListener {
            val account =
                AccountDatabase.getInstance(this.requireContext()).accountDao.getAccount()
            if (account != null) {
                AccountDatabase.getInstance(this.requireContext()).accountDao.deleteAccount(
                    account
                )
                startActivity(Intent(this.context, LoginActivity::class.java))
                requireActivity().finish()
            }
        }
        binding.imgNotification.setOnClickListener {
            val intent = Intent(this.context, NotificationActivity::class.java)
            startActivity(intent)
        }
        binding.lnEditInfo.setOnClickListener {
            val intent = Intent(this.context, UserDetailActivity::class.java)
            startActivity(intent)
        }
        binding.signIn.setOnClickListener {
            startActivity(Intent(this.context, LoginActivity::class.java))
            requireActivity().finish()
        }
        return binding.root
    }


}