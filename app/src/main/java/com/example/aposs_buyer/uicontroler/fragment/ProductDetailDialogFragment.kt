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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.databinding.FragmentProductDetailDialogListDialogBinding
import com.example.aposs_buyer.model.CartItem
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.dto.CartDTO
import com.example.aposs_buyer.model.dto.TokenDTO
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.uicontroler.activity.CheckOutActivity
import com.example.aposs_buyer.uicontroler.activity.SearchActivity
import com.example.aposs_buyer.uicontroler.adapter.ColorDetailPropertyAdapter
import com.example.aposs_buyer.uicontroler.adapter.ColorPropertyAdapter
import com.example.aposs_buyer.uicontroler.adapter.StringDetailPropertyAdapter
import com.example.aposs_buyer.uicontroler.adapter.StringPropertyAdapter
import com.example.aposs_buyer.utils.DialogType
import com.example.aposs_buyer.viewmodel.DetailProductDiaLogViewModel
import com.example.aposs_buyer.viewmodel.DetailProductViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailDialogFragment : BottomSheetDialogFragment(),
    StringDetailPropertyAdapter.PropertyStringValueSelected,
    ColorDetailPropertyAdapter.PropertyColorValueSelected {

    private var _binding: FragmentProductDetailDialogListDialogBinding? = null

    private val binding get() = _binding!!
    private val viewModel: DetailProductViewModel by activityViewModels()
    private val viewModelDialog: DetailProductDiaLogViewModel by viewModels()

    private val args: ProductDetailDialogFragmentArgs by navArgs()

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

    private fun toCartItem(cartDTO: CartDTO): CartItem {
        val image = Image(cartDTO.imageUrl)
        return CartItem(
            id = cartDTO.id,
            image = image,
            name = cartDTO.name,
            price = cartDTO.price,
            amount = cartDTO.quantity,
            property = cartDTO.property,
            isChoose = cartDTO.select,
            product = cartDTO.productId,
        )
    }

    private fun dialogTypeButton() {
        binding.dialogButton.setOnClickListener {
            if (checkValidPropertyProduct()) {
                if (dialogType == DialogType.CheckOutDialog) {
                    if (isLogin()) {
                        startActivity(Intent(this.context, CheckOutActivity::class.java))
                    }
                } else {
                    if (isLogin()) {
                        viewModelDialog.addToCart()
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
        viewModelDialog.selectedProductQuantitiesDiaLog.observe(
            this
        ) {
            if (viewModelDialog.productTypeCart.value!!.quantity > viewModelDialog.selectedProductQuantitiesDiaLog.value!!) {
                viewModelDialog.productTypeCart.value!!.quantity =
                    viewModelDialog.selectedProductQuantitiesDiaLog.value!!
                viewModelDialog.productTypeCartAmount.value =
                    viewModelDialog.productTypeCart.value!!.quantity
            }
        }
    }

    private fun onAddAmount() {
        if (viewModelDialog.productTypeCart.value!!.quantity < viewModelDialog.selectedProductQuantitiesDiaLog.value!!) {
            viewModelDialog.productTypeCart.value!!.quantity++
            viewModelDialog.productTypeCartAmount.value =
                viewModelDialog.productTypeCart.value!!.quantity
        } else {
            Toast.makeText(this.requireContext(), "It's max in stock", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onReduceAmount() {
        if (viewModelDialog.productTypeCart.value!!.quantity > 1) {
            viewModelDialog.productTypeCart.value!!.quantity--
            viewModelDialog.productTypeCartAmount.value =
                viewModelDialog.productTypeCart.value!!.quantity
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
        dialogType = args.dialogType
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

    private fun isLogin(): Boolean {
        val accountDao = AccountDatabase.getInstance(this.requireContext()).accountDao
        val account = accountDao.getAccount()
        return if (account != null) {
            viewModelDialog.tokenDTO =
                TokenDTO(accessToken = account.accessToken, account.tokenType, account.refreshToken)
            true
        } else {
            val intent = Intent(this.context, SearchActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
            false
        }
    }
}