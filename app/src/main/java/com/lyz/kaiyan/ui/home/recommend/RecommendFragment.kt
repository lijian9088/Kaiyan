package com.lyz.kaiyan.ui.home.recommend

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.hjq.http.EasyHttp
import com.hjq.http.listener.OnHttpListener
import com.lyz.kaiyan.R
import com.lyz.kaiyan.base.MyLazyFragment
import com.lyz.kaiyan.bean.*
import com.lyz.kaiyan.contract.BaseModel
import com.lyz.kaiyan.contract.VideoHeadBean
import com.lyz.kaiyan.http.request.CustomApi
import com.lyz.kaiyan.http.request.RecommendApi
import com.lyz.kaiyan.ui.home.recommend.adapter.RecommendAdapter
import com.lyz.kaiyan.ui.home.recommend.adapter.model.*
import com.lyz.kaiyan.ui.videodetail.VideoDetailActivity
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.simple.SimpleMultiListener


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
        refreshLayout.autoRefresh()
    }

    private fun initView(root: View) {
        refreshLayout = root.findViewById(R.id.refreshLayout)
        recyclerView = root.findViewById(R.id.recyclerView)
    }

    private fun initData() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecommendAdapter()
    }

    private fun initEvent() {
        refreshLayout.setOnMultiListener(object : SimpleMultiListener(){
            override fun onRefresh(refreshLayout: RefreshLayout) {
                initHttp()
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                LogUtils.d("onLoadMore:$nextPageUrl")
                loadMore()
            }
        })

    }

    private fun loadMore() {
        EasyHttp.get(this)
            .api(CustomApi(nextPageUrl))
            .request(object : OnHttpListener<MultiPageBean> {
                override fun onSucceed(result: MultiPageBean?) {
                    parseResult(result)
                    refreshLayout.finishLoadMore()
                }

                override fun onFail(e: Exception?) {
                    showFail(e)
                    refreshLayout.finishLoadMore(false)
                }

            })
    }

    private fun initHttp() {
        EasyHttp.get(this)
            .api(RecommendApi())
            .request(object : OnHttpListener<MultiPageBean> {
                override fun onSucceed(result: MultiPageBean?) {
                    parseResult(result)
                    refreshLayout.finishRefresh()
                }

                override fun onFail(e: Exception?) {
                    showFail(e)
                    refreshLayout.finishRefresh(false)
                }

            })
    }

    private var nextPageUrl: String? = null

    private fun parseResult(result: MultiPageBean?) {

        nextPageUrl = result?.nextPageUrl
        LogUtils.d("parseResult:$nextPageUrl")

        val dataList = mutableListOf<BaseModel>()

        val itemList = result?.itemList
        if(itemList == null) {
            return
        }
        for(i in itemList.indices) {
            val item = itemList[i]
            val type = item.type
            val json = GsonUtils.toJson(item)
            println("json:${json}")
            if("squareCardCollection" == type) {
                val bean = GsonUtils.fromJson(json, SquareCardCollectionBean::class.java)
                parseSquareCard(dataList, bean)
            }
            if("textCard" == type) {
                val bean = GsonUtils.fromJson(json, TextCardBean::class.java)
                parseTextCard(dataList, bean)
            }
            if("followCard" == type) {
                val bean = GsonUtils.fromJson(json, FollowCardBean::class.java)
                parseFollowCard(dataList, bean)
            }
            if("videoSmallCard" == type) {
                val bean = GsonUtils.fromJson(json, VideoSmallCardBean::class.java)
                parseVideoSmallCard(dataList, bean)
            }
        }

        showData(dataList)

    }

    private fun parseSquareCard(list: MutableList<BaseModel>, squareCardCollectionBean: SquareCardCollectionBean) {
        val data = squareCardCollectionBean.data

        val titleViewViewModel = TitleModel()
        titleViewViewModel.title = data.header.title
        titleViewViewModel.actionTitle = data.header.rightText
        list.add(titleViewViewModel)

        for(i in data.itemList.indices) {
            val value = data.itemList[i]
            val followCardBean = GsonUtils.fromJson(GsonUtils.toJson(value), FollowCardBean::class.java)
            parseFollowCard(list, followCardBean)
        }
    }

    private fun parseTextCard(list: MutableList<BaseModel>, textCardBean: TextCardBean) {
        val singleTitleViewModel = SingleTitleModel()
        singleTitleViewModel.title = textCardBean.data.text
        list.add(singleTitleViewModel)
    }

    private fun parseFollowCard(list: MutableList<BaseModel>, followCardBean: FollowCardBean) {
        val data = followCardBean.data.content.data
        val followCardViewModel = FollowCardModel()
        followCardViewModel.coverUrl = data.cover.detail
        followCardViewModel.videoTime = data.duration
        followCardViewModel.authorUrl = data.author.icon
        followCardViewModel.title = data.title
        followCardViewModel.description = "${data.author.name} / #${data.category}"
        followCardViewModel.videoDescription = data.description
        followCardViewModel.userDescription = data.author.description
        followCardViewModel.nickName = data.author.name
        followCardViewModel.playerUrl = data.playUrl
        followCardViewModel.blurredUrl = data.cover.blurred
        followCardViewModel.videoId = data.id
        list.add(followCardViewModel)
    }

    private fun parseVideoSmallCard(list: MutableList<BaseModel>, videoSmallCardBean: VideoSmallCardBean) {
        val data = videoSmallCardBean.data
        val model = VideoSmallCardModel()
        model.coverUrl = data.cover.detail
        model.videoTime = data.duration
        model.authorUrl = data.author.icon
        model.title = data.title
        model.description = "${data.author.name} / #${data.category}"
        model.videoDescription = data.description
        model.userDescription = data.author.description
        model.nickName = data.author.name
        model.playerUrl = data.playUrl
        model.blurredUrl = data.cover.blurred
        model.videoId = data.id
        model.collectionCount = data.consumption.collectionCount
        model.shareCount = data.consumption.shareCount
        list.add(model)
    }

    private fun showData(result: MutableList<BaseModel>) {
        val recommendAdapter = recyclerView.adapter as RecommendAdapter
//        recommendAdapter.setList(result)
        if(recommendAdapter.data.size <= 0){
            recommendAdapter.setList(result)
        }else{
            recommendAdapter.addData(result)
        }
    }

    private fun showFail(e: Exception?) {
        val msg = "fail:${e?.message}"
        LogUtils.e("fail:$msg")
        ToastUtils.showShort("fail:$msg")
    }

}