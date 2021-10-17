package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentSignUpBinding
import com.example.aposs_buyer.utils.SignUpState
import com.example.aposs_buyer.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setUpSignInButton()
        toastMessageChange()
        setCheckingName()
        setCheckingEmail()
        setCheckingPassword()
        setCheckingConfirmPassword()
        setCheckingCellPhone()
        setOnStateChange()
        return binding.root
    }

    private fun setOnStateChange(){
        viewModel.signUpState.observe(this.viewLifecycleOwner,{
            if(it == SignUpState.Verify){
                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToVerifyFragment())
            }
        })
    }

    private fun setCheckingEmail(){
        viewModel.email.observe(this.viewLifecycleOwner,{
            viewModel.isValidEmail()
            binding.emailLayout.error = viewModel.emailErrorMessage;
        })
    }
    private fun setCheckingPassword(){
        viewModel.password.observe(this.viewLifecycleOwner,{
            viewModel.isValidPassword()
            binding.passwordLayout.error = viewModel.passwordErrorMessage
        })
    }

    private fun setCheckingName(){
        viewModel.name.observe(this.viewLifecycleOwner,{
            viewModel.isValidName()
            binding.nameLayout.error = viewModel.nameErrorMessage
        })
    }

    private fun setCheckingConfirmPassword(){
        viewModel.confirmPassword.observe(this.viewLifecycleOwner,{
            viewModel.isValidConfirmPassword()
            binding.confirmPasswordLayout.error = viewModel.confirmErrorMessage
        })
    }

    private fun setCheckingCellPhone(){
        viewModel.cellNumber.observe(this.viewLifecycleOwner,{
            viewModel.isValidPhoneNumber()
            binding.cellPhoneLayout.error = viewModel.cellNumberErrorMessage
        })
    }
    private fun setUpSignInButton(){
        binding.signIn.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
        }
    }
    private fun toastMessageChange() {
        viewModel.toastMessage.observe(this.viewLifecycleOwner, {
            Toast.makeText(this.requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

}