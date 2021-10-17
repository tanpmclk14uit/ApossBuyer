package com.example.aposs_buyer.uicontroler.fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.DeletePermissionDialogBinding
import com.example.aposs_buyer.databinding.FragmentAddressBinding
import com.example.aposs_buyer.uicontroler.activity.AddressActivity
import com.example.aposs_buyer.uicontroler.activity.CartSecondActivity
import com.example.aposs_buyer.uicontroler.adapter.AddressAdapter
import com.example.aposs_buyer.viewmodel.AddressViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressFragment : Fragment(), AddressAdapter.OnAddressCLickListener {

    private lateinit var binding: FragmentAddressBinding
    private val viewModel: AddressViewModel by viewModels()
    private lateinit var addressAdapter: AddressAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_address, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        addressAdapter = AddressAdapter(this)
        binding.rcAddress.adapter = addressAdapter
        binding.rcAddress.layoutManager = LinearLayoutManager(binding.rcAddress.context, LinearLayoutManager.VERTICAL, false)
        setOnClick()
        return binding.root
    }

    override fun onClick(position: Int) {
        viewModel.onChangeDefault(position)
        addressAdapter.notifyDataSetChanged()
    }

    fun setOnClick()
    {
        binding.btnEditDefault.setOnClickListener {
            findNavController().navigate(AddressFragmentDirections.actionAddressFragmentToAddressDialogFragment2(viewModel.getCurrentDefaultAddress()))
        }
        binding.tvAddNewAddress.setOnClickListener {
            findNavController().navigate(AddressFragmentDirections.actionAddressFragmentToAddressDialogFragment2(viewModel.getCreateAddress()))
        }
        binding.btnDelete.setOnClickListener {
            onOpenDeleteDialog()
        }
        binding.imgBack.setOnClickListener{
            requireActivity().onBackPressed()
        }
        binding.clCart.setOnClickListener {
            val intent = Intent(this.context, CartSecondActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onOpenDeleteDialog()
    {
        val dialogDelete = this.context?.let { Dialog(it) }
        dialogDelete?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogDelete?.setContentView(R.layout.delete_permission_dialog)

        val window = dialogDelete?.window ?: return

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val windowAttribute = window.attributes

        windowAttribute.gravity = Gravity.CENTER
        window.attributes= windowAttribute

        dialogDelete.setCancelable(false)

        val btnDelete:AppCompatButton = dialogDelete.findViewById(R.id.btn_delete)
        val btnCancel: AppCompatButton = dialogDelete.findViewById(R.id.btn_cancel)

        btnCancel.setOnClickListener {
            dialogDelete.dismiss()
        }

        btnDelete.setOnClickListener {
            viewModel.deleteDefaultAddress()
            Toast.makeText(this.context, "Delete success", Toast.LENGTH_SHORT).show()
            dialogDelete.dismiss()
        }

        dialogDelete.show()
    }
}