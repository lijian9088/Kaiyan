package com.lyz.kaiyan.ui.home.follow

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lyz.kaiyan.R
import com.lyz.kaiyan.base.MyLazyFragment

class FollowFragment : MyLazyFragment() {

    companion object {
        fun newInstance() = FollowFragment()
    }

    private lateinit var viewModel: FollowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_follow, container, false)
    }

    override fun lazyInit() {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FollowViewModel::class.java)
        // TODO: Use the ViewModel
    }

}