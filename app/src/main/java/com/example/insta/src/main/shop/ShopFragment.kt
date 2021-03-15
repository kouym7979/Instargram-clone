package com.example.insta.src.main.shop

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.insta.R
import com.example.insta.config.BaseFragment
import com.example.insta.databinding.FragmentShopBinding

class ShopFragment : BaseFragment<FragmentShopBinding>(FragmentShopBinding::bind, R.layout.fragment_shop) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.shopView.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.shopView.adapter=ShopAdapter(requireContext())
    }
}