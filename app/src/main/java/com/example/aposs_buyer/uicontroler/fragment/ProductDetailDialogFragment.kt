package com.example.aposs_buyer.uicontroler.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.databinding.FragmentProductDetailDialogListDialogBinding
import com.example.aposs_buyer.model.Order
import com.example.aposs_buyer.model.PropertyValue
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.uicontroler.activity.CheckOutActivity
import com.example.aposs_buyer.uicontroler.adapter.ColorDetailPropertyAdapter
import com.example.aposs_buyer.uicontroler.adapter.ColorPropertyAdapter
import com.example.aposs_buyer.uicontroler.adapter.StringDetailPropertyAdapter
import com.example.aposs_buyer.uicontroler.adapter.StringPropertyAdapter
import com.example.aposs_buyer.uicontroler.dialog.LoadingDialog
import com.example.aposs_buyer.utils.DialogType
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.viewmodel.DetailProductViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ProductDetailDialogFragment : BottomSheetDialogFragment(),
    StringDetailPropertyAdapter.PropertyStringValueSelected,
    ColorDetailPropertyAdapter.PropertyColorValueSelected {

    private var _binding: FragmentProductDetailDialogListDialogBinding? = null

    private val binding get() = _binding!!
    private val viewModel: DetailProductViewModel by activityViewModels()

    private val args: ProductDetailDialogFragmentArgs by navArgs()
    private lateinit var dialogType: DialogType
    private lateinit var loadingDialog: LoadingDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailDialogListDialogBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.validatePropertyValue()
        loadingDialog = LoadingDialog(this.requireActivity())
        setUpDialogButton()
        setUpProductProperty()
        setupDecreaseAmount()
        setupIncreaseAmount()
        binding.cancelButton.setOnClickListener {
            this.dismiss()
        }
        dialogTypeButton()
        return binding.root
    }

    private fun checkValidAmount(): Boolean {
        return if (viewModel.cartAmount.value!! > 0) {
            true
        } else {
            Toast.makeText(
                this.requireContext(),
                "Please specify amount!",
                Toast.LENGTH_SHORT
            ).show()
            false
        }
    }

    private fun checkValidPropertyProduct(): Boolean {
        return if (viewModel.isSpecialPropertyHaveNoneValueSelected()) {
            Toast.makeText(
                this.requireContext(),
                "There is one special property has none value selected",
                Toast.LENGTH_SHORT
            ).show()
            false
        } else {
            true
        }
    }

    private fun dialogTypeButton() {
        binding.dialogButton.setOnClickListener {
            if (checkValidPropertyProduct() && checkValidAmount()) {

                loadingDialog.startLoading()
                val context = this.context
                if (dialogType == DialogType.CheckOutDialog) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val order: Order? = viewModel.makeOrder()
                        loadingDialog.dismissDialog()
                        val intent = Intent(context, CheckOutActivity::class.java)
                        if (order != null) {
                            intent.putExtra("order", order)
                            withContext(Dispatchers.Main)
                            {
                                startActivity(intent)
                            }
                        }
                    }
                } else {
                    viewModel.addNewCart()
                    loadingDialog.dismissDialog()
                    Toast.makeText(
                        requireContext(),
                        "Add to cart successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    this.dismiss()
                }
            }
        }
    }

    private fun setupDecreaseAmount() {
        binding.imgMinus.setOnClickListener {
            if (viewModel.cartAmount.value!! > 0) {
                viewModel.cartAmount.value = viewModel.cartAmount.value!! - 1
            }
        }
    }

    private fun setupIncreaseAmount() {
        binding.imgPlus.setOnClickListener {
            var amount = viewModel.cartAmount.value!!
            if (amount < viewModel.selectedProductQuantities.value!!) {
                amount++
            }
            viewModel.cartAmount.value = amount
        }
    }

    private fun setUpProductProperty() {
        val stringPropertyAdapter = StringPropertyAdapter(this)
        val colorPropertyAdapter = ColorPropertyAdapter(this)
        binding.stringProperty.adapter = stringPropertyAdapter
        binding.colorProperty.adapter = colorPropertyAdapter
    }

    @SuppressLint("SetTextI18n")
    private fun setUpDialogButton() {
        if (dialogType == DialogType.CheckOutDialog) {
            binding.dialogButton.text = "Thanh toán"
        } else {
            binding.dialogButton.text = "Thêm vào giỏ hàng"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogType = args.dialogType
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun notifySelectedColorValueChange(propertyValue: PropertyValue) {
        viewModel.notifySelectedPropertyChange(propertyValue)
    }

    override fun notifySelectedStringValueChange(propertyValue: PropertyValue) {
        viewModel.notifySelectedPropertyChange(propertyValue)
    }

}