package com.example.aposs_buyer.uicontroler.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatButton
import com.example.aposs_buyer.R

class YesNoOrderSuccessStatusDialog(activity: Activity, val successClick: SuccessClick) {
    private var myActivity: Activity = activity
    private lateinit var dialog: AlertDialog

    interface SuccessClick{
        fun onSuccessClick()
    }

    fun loadingDialog()
    {
        val builder: AlertDialog.Builder = AlertDialog.Builder(myActivity)
        val inflater =  myActivity.layoutInflater
        builder.setView(R.layout.dialog_yes_no_order_success_status)
        builder.setCancelable(true)
        dialog = builder.create()
        dialog.show()

        val btnCancel: AppCompatButton = dialog.findViewById(R.id.btn_cancel)
        val btnSave: AppCompatButton = dialog.findViewById(R.id.btn_save)


        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnSave.setOnClickListener {
            successClick.onSuccessClick()
            dialog.dismiss()
        }
    }

    fun dismissDialog()
    {
        dialog.dismiss()
    }
}