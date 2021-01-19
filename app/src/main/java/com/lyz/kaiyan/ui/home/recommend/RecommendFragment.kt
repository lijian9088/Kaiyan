package com.lyz.kaiyan.ui.home.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.lyz.kaiyan.R
import com.lyz.kaiyan.base.MyLazyFragment
import com.lyz.kaiyan.ui.home.recommend.adapter.RecommendAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class RecommendFragment : MyLazyFragment() {

    companion object {
        fun newInstance() = RecommendFragment()
        val tagName: String = RecommendViewModel::class.java.simpleName
    }

    private lateinit var viewModel: RecommendViewModel
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtils.d("$tagName:onCreateView")
        return inflater.inflate(R.layout.fragment_recommend, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecommendViewModel::class.java)
        LogUtils.d("$tagName:onActivityCreated")
    }

    override fun lazyInit() {
        LogUtils.d("$tagName:lazyInit")
        view?.let { initView(it) }
        initData()
        initEvent()
    }

    private fun initView(root: View) {
        refreshLayout = root.findViewById(R.id.refreshLayout)
        recyclerView = root.findViewById(R.id.recyclerView)
    }

    private fun initData() {
        var list = mutableListOf("1","2","3")

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecommendAdapter(list)
    }

    private fun initEvent() {

    }

}