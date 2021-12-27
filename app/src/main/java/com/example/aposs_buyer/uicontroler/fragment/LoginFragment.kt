package com.example.aposs_buyer.uicontroler.fragment

import android.R.attr
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.AndroidEntryPoint
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import android.R.attr.data
import android.util.Log
import com.example.aposs_buyer.model.dto.SignInWithSocialDTO

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException





@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val RC_SIGN_IN: Int = 1
    private val googleSecretKey: String ="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"
    private lateinit var binding: FragmentLoginBinding

    private val viewModel: SignInViewModel by viewModels()

    private lateinit var dialog: LoadingDialog

    private lateinit var mGoogleSignInClient: GoogleSignInClient
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
        setLoginWithGoogleButton()
        setUpGoogleSignIn()
        return binding.root
    }
    private fun setUpGoogleSignIn(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this.requireActivity(), gso);
    }
    private fun setLoginWithGoogleButton(){
        binding.loginWithGoogle.setOnClickListener {
            val googleIntent: Intent =mGoogleSignInClient.signInIntent
            startActivityForResult(googleIntent, RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN){
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Sign in with gg", "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }
    private fun updateUI(account: GoogleSignInAccount?){
        if(account!=null){
            val socialDTO = SignInWithSocialDTO(account.email,account.displayName,account.photoUrl.toString(),"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")
            viewModel.signInWithSocialAccount(socialDTO)
            mGoogleSignInClient.signOut()
        }
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