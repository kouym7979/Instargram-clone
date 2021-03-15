package com.example.insta.src.main.home

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.insta.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet :BottomSheetDialogFragment() {

    override fun getTheme(): Int = R.style.CustomBottomSheet

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.modal_bottom_sheet_layout,container,false)
        return view
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }
}