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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonFragment : Fragment() {

    private var binding: FragmentPersonBinding? = null
    private val viewModel: PersonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person, container, false)
        binding?.lifecycleOwner = this.viewLifecycleOwner
        binding?.viewModel = viewModel
        val account = AccountDatabase.getInstance(this.requireContext()).accountDao.getAccount()
        viewModel.isSignIn.value = account != null
        viewModel.isSignIn.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding?.lnNoAccount?.visibility = View.GONE
                binding?.lnHavingAccount?.visibility = View.VISIBLE
                binding?.userName?.text = account!!.userName
            } else {
                binding?.lnNoAccount?.visibility = View.VISIBLE
                binding?.lnHavingAccount?.visibility = View.GONE
            }
        })
        binding?.btnAddress?.setOnClickListener {
            val intent = Intent(this.context, AddressActivity::class.java)
            startActivity(intent)
        }
//        binding?.btnRating?.setOnClickListener {
//            val intent = Intent(this.context, RatingActivity::class.java)
//            startActivity(intent)
//        }
        binding?.order?.setOnClickListener {
            startActivity(Intent(this.context, OrderActivity::class.java))
        }
        binding?.signOut?.setOnClickListener {
            val account =
                AccountDatabase.getInstance(this.requireContext()).accountDao.getAccount()
            if (account != null) {
                AccountDatabase.getInstance(this.requireContext()).accountDao.deleteAccount(
                    account
                )
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestProfile()
                    .build()
                val mGoogleSignInClient = GoogleSignIn.getClient(this.requireActivity(), gso);
                mGoogleSignInClient.signOut()
                startActivity(Intent(this.context, LoginActivity::class.java))
                requireActivity().finish()
            }

        }

        binding?.signIn?.setOnClickListener {
            startActivity(Intent(this.context, LoginActivity::class.java))
            requireActivity().finish()
        }
        return binding?.root!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}