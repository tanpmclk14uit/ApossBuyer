package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentLoginBinding
import com.example.aposs_buyer.model.entity.Account
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.uicontroler.activity.MainActivity
import com.example.aposs_buyer.uicontroler.dialog.LoadingDialog
import com.example.aposs_buyer.utils.LoginState
import com.example.aposs_buyer.viewmodel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: SignInViewModel by viewModels()

    private lateinit var dialog: LoadingDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        dialog = LoadingDialog(requireActivity())
        setUpBackButton()
        setSignUpButton()
        toastMessageChange()
        setCheckingEmail()
        setCheckingPassword()
        onLoginStateChange()
        return binding.root
    }

    private fun onLoginStateChange() {
        viewModel.loginState.observe(this.viewLifecycleOwner, {
            if (it == LoginState.Wait) {
                dialog.dismissDialog()
            } else {
                if (it == LoginState.Loading) {
                    dialog.startLoading()
                } else {
                    dialog.dismissDialog()
                    if (viewModel.token != null && viewModel.email.value != null && viewModel.password.value != null) {
                        val account: Account =
                            Account(
                                viewModel.email.value!!,
                                viewModel.password.value!!,
                                viewModel.token!!.accessToken,
                                viewModel.token!!.tokenType,
                                viewModel.token!!.refreshToken
                            )
                        AccountDatabase.getInstance(this.requireContext()).accountDao.insertAccount(
                            account
                        )
                        startActivity(Intent(this.context, MainActivity::class.java))
                    } else {
                        Toast.makeText(this.context, "An error occur!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setCheckingEmail() {
        viewModel.email.observe(this.viewLifecycleOwner, {
            viewModel.isValidEmail()
            binding.emailLayout.error = viewModel.emailErrorMessage;
        })
    }

    private fun setCheckingPassword() {
        viewModel.password.observe(this.viewLifecycleOwner, {
            viewModel.isValidPassword()
            binding.passwordLayout.error = viewModel.passwordErrorMessage
        })
    }

    private fun setSignUpButton() {
        binding.signUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment());
        }
    }

    private fun setUpBackButton() {
        binding.backButton.setOnClickListener {
            startActivity(Intent(this.context, MainActivity::class.java))
        }
    }

    private fun toastMessageChange() {
        viewModel.toastMessage.observe(this.viewLifecycleOwner, {
            Toast.makeText(this.requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }
}