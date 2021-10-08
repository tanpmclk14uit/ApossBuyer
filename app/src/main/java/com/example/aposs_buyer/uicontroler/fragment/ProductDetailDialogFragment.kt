package com.example.aposs_buyer.uicontroler.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.aposs_buyer.databinding.FragmentProductDetailDialogListDialogBinding
import com.example.aposs_buyer.uicontroler.adapter.ColorDetailPropertyAdapter
import com.example.aposs_buyer.uicontroler.adapter.ColorPropertyAdapter
import com.example.aposs_buyer.uicontroler.adapter.StringDetailPropertyAdapter
import com.example.aposs_buyer.uicontroler.adapter.StringPropertyAdapter
import com.example.aposs_buyer.utils.DialogType
import com.example.aposs_buyer.viewmodel.DetailProductDiaLogViewModel
import com.example.aposs_buyer.viewmodel.DetailProductViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ProductDetailDialogFragment : BottomSheetDialogFragment(),
    StringDetailPropertyAdapter.PropertyStringValueSelected,
    ColorDetailPropertyAdapter.PropertyColorValueSelected {

    private var _binding: FragmentProductDetailDialogListDialogBinding? = null

    private val binding get() = _binding!!
    private val viewModel: DetailProductViewModel by activityViewModels()
    private val viewModelDialog: DetailProductDiaLogViewModel by viewModels()

    private lateinit var dialogType: DialogType

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailDialogListDialogBinding.inflate(inflater, container, false)
        binding.viewModel = viewModelDialog
        binding.lifecycleOwner = this
        viewModelDialog.setUpDialogData(viewModel)
        setUpDialogButton()
        setUpProductProperty()
        setupDecreaseAmount()
        setupIncreaseAmount()
        onAvailableQuantitiesChange()
        binding.cancelButton.setOnClickListener {
            this.dismiss()
        }
        dialogTypeButton()
        return binding.root
    }

    private fun checkValidPropertyProduct(): Boolean {
        if (viewModelDialog.isSpecialPropertyHaveNoneValueSelected()) {
            Toast.makeText(
                this.requireContext(),
                "There is one special property has none value selected",
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else {
            return if (viewModelDialog.isPropertyValueError.value!!) {
                Toast.makeText(
                    this.requireContext(),
                    "There is special property selected more than one value",
                    Toast.LENGTH_SHORT
                ).show()
                false
            } else {
                true
            }
        }
    }

    private fun dialogTypeButton() {
        binding.dialogButton.setOnClickListener {
            if (checkValidPropertyProduct()) {
                if (dialogType == DialogType.CheckOutDialog) {
                    viewModelDialog.goToCheckOut()
                    Toast.makeText(requireContext(), "Go to check out", Toast.LENGTH_SHORT).show()
                    this.dismiss()
                } else {
                    viewModelDialog.addToCart()
                    Toast.makeText(requireContext(), "Add to cart successfully", Toast.LENGTH_SHORT).show()
                    this.dismiss()
                }
            }

        }
    }

    private fun setupDecreaseAmount() {
        binding.imgMinus.setOnClickListener {
            onReduceAmount()
        }
    }

    private fun setupIncreaseAmount() {
        binding.imgPlus.setOnClickListener {
            onAddAmount()
        }
    }

    private fun setUpProductProperty() {
        val stringPropertyAdapter = StringPropertyAdapter(this)
        val colorPropertyAdapter = ColorPropertyAdapter(this)
        binding.stringProperty.adapter = stringPropertyAdapter
        binding.colorProperty.adapter = colorPropertyAdapter
    }

    private fun onAvailableQuantitiesChange() {
        viewModelDialog.selectedProductQuantitiesDiaLog.observe(this, {
            if (viewModelDialog.productTypeCart.value!!.amount > viewModelDialog.selectedProductQuantitiesDiaLog.value!!) {
                viewModelDialog.productTypeCart.value!!.amount =
                    viewModelDialog.selectedProductQuantitiesDiaLog.value!!
                viewModelDialog.productTypeCartAmount.value =
                    viewModelDialog.productTypeCart.value!!.amount
            }
        })
    }

    private fun onAddAmount() {
        if (viewModelDialog.productTypeCart.value!!.amount < viewModelDialog.selectedProductQuantitiesDiaLog.value!!) {
            viewModelDialog.productTypeCart.value!!.amount++
            viewModelDialog.productTypeCartAmount.value =
                viewModelDialog.productTypeCart.value!!.amount
        } else {
            Toast.makeText(this.requireContext(), "It's max in stock", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onReduceAmount() {
        if (viewModelDialog.productTypeCart.value!!.amount > 1) {
            viewModelDialog.productTypeCart.value!!.amount--
            viewModelDialog.productTypeCartAmount.value =
                viewModelDialog.productTypeCart.value!!.amount
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpDialogButton() {
        if (dialogType == DialogType.CheckOutDialog) {
            binding.dialogButton.text = "Check out"
        } else {
            binding.dialogButton.text = "Add to cart"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val dialogTypeString = requireArguments().getString("dialogType")
            dialogType = if (dialogTypeString == DialogType.CheckOutDialog.toString()) {
                DialogType.CheckOutDialog
            } else {
                DialogType.CartDialog
            }
        }
    }

    companion object {
        fun newInstance(dialogType: DialogType): ProductDetailDialogFragment =
            ProductDetailDialogFragment().apply {
                arguments = Bundle().apply {
                    putString("dialogType", dialogType.toString())
                }
            }
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun notifySelectedColorValueChange(propertyId: Long) {
        viewModelDialog.notifySelectedColorPropertyChange(propertyId)
    }

    override fun notifySelectedStringValueChange(propertyId: Long) {
        viewModelDialog.notifySelectedStringPropertyChange(propertyId)
    }
}