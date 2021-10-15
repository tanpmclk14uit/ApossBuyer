package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentSignUpBinding
import com.example.aposs_buyer.databinding.FragmentVerifyBinding
import com.example.aposs_buyer.utils.SignUpState
import com.example.aposs_buyer.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyFragment : Fragment() {

    private lateinit var binding: FragmentVerifyBinding

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_verify, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.change.setOnClickListener {
            findNavController().navigate(VerifyFragmentDirections.actionVerifyFragmentToSignUpFragment())
            viewModel.signUpState.value = SignUpState.Wait
        }
        onVerifyNumberChange()
        toastMessageChange()
        return binding.root
    }
    private fun toastMessageChange() {
        viewModel.toastMessage.observe(this.viewLifecycleOwner, {
            Toast.makeText(this.requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun onVerifyNumberChange(){
        viewModel.verifyString.observe(this.viewLifecycleOwner, {
            if(viewModel.isVerifyRight()){
                Toast.makeText(this.requireContext(), "Register success", Toast.LENGTH_SHORT).show()
                //register to db, loading statement
                findNavController().navigate(VerifyFragmentDirections.actionVerifyFragmentToSuccessFragment())
            }
        })
    }
}