package com.example.insta.src.main.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.insta.R
import com.example.insta.config.ApplicationClass
import com.example.insta.config.BaseFragment
import com.example.insta.databinding.FragmentSearchBinding
import com.example.insta.signup.adapter.PageAdapter
import com.example.insta.src.main.home.adapter.FeedRecycler
import com.example.insta.src.main.search.adapter.searchAdapter
import com.example.insta.src.main.search.models.SearchResponse

class SearchFragment:BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search),SearchView {

    //private val adapter by lazy { searchAdapter(supportFragmentManager) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.searchView.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        showLoadingDialog(requireContext())
        SearchService(this).tryGetSearch(ApplicationClass.sSharedPreferences.getInt("userNickNameIdx",0))
    }

    override fun onGetSearchSuccess(response: SearchResponse) {
        response.message.let{
            Log.d("확인",it.toString())
        }

        Log.d("확인",response.message.toString())
        binding.searchView.adapter= searchAdapter(requireContext(),response.userList)
        dismissLoadingDialog()

    }

    override fun onGetSearchFailure(message: String) {
        dismissLoadingDialog()
        Log.d("확인","실패:"+message)
    }
}