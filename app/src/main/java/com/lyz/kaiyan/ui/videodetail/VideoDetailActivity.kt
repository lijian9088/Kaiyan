package com.lyz.kaiyan.ui.videodetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gyf.immersionbar.ImmersionBar
import com.lyz.kaiyan.R
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnMultiListener
import com.scwang.smart.refresh.layout.simple.SimpleMultiListener

class VideoDetailActivity : AppCompatActivity() {

    lateinit var coverVideoView: View
    lateinit var refreshLayout: SmartRefreshLayout
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_detail)

//        ImmersionBar.with(this).init()

        initView()
        initData()
        initEvent()

    }

    private fun initView() {
        coverVideoView = findViewById(R.id.coverVideoView)
        refreshLayout = findViewById(R.id.refreshLayout)
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initData() {
        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter =
    }

    private fun initEvent() {
        refreshLayout.setOnMultiListener(object : SimpleMultiListener() {
            override fun onLoadMore(refreshLayout: RefreshLayout) {

            }

            override fun onRefresh(refreshLayout: RefreshLayout) {

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}