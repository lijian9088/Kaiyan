package com.lyz.kaiyan.ui.home.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.hjq.http.EasyHttp
import com.hjq.http.listener.OnHttpListener
import com.lyz.kaiyan.R
import com.lyz.kaiyan.base.MyLazyFragment
import com.lyz.kaiyan.bean.*
import com.lyz.kaiyan.http.request.RecommendApi
import com.lyz.kaiyan.ui.home.recommend.adapter.RecommendAdapter
import com.lyz.kaiyan.ui.home.recommend.adapter.model.BaseViewModel
import com.lyz.kaiyan.ui.home.recommend.adapter.model.SingleTitleViewViewModel
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
        initHttp()
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

    }

    private fun initHttp() {
        EasyHttp.get(this)
            .api(RecommendApi())
            .request(object : OnHttpListener<MultiPageBean> {
                override fun onSucceed(result: MultiPageBean?) {
                    parseResult(result)
                }

                override fun onFail(e: Exception?) {
                    showFail(e)
                }

            })
    }

    private var nextPageUrl: String? = null

    private fun parseResult(result: MultiPageBean?) {
        nextPageUrl = result?.nextPageUrl

        val dataList = mutableListOf<BaseViewModel>()

        val itemList = result?.itemList
        if(itemList == null) {
            return
        }
        for(i in itemList.indices) {
            val item = itemList[i]
            val type = item.type
            val toJson = GsonUtils.toJson(item)
            println("toJson:${toJson}")
            if("squareCardCollection" == type) {
                val bean = GsonUtils.fromJson<SquareCardCollectionBean>(GsonUtils.toJson(item),
                    SquareCardCollectionBean::class.java)
                parseSquareCard(dataList, bean)
            }
            if("textCard" == type) {
                val bean = GsonUtils.fromJson<TextCardBean>(GsonUtils.toJson(item),
                    TextCardBean::class.java)
                println("bean:$bean")
                parseTextCard(dataList, bean)
            }
            if("followCard" == type) {
//                parseFollowCard(dataList, item.data as FollowCardBean)
            }
            if("videoSmallCard" == type) {
//                parseVideoSmallCard(dataList, item.data as VideoSmallCardBean)
            }
        }

        showData(dataList)

    }

    private fun parseSquareCard(list: MutableList<BaseViewModel>, squareCardCollectionBean: SquareCardCollectionBean) {

    }

    private fun parseTextCard(list: MutableList<BaseViewModel>, textCardBean: TextCardBean) {
        val singleTitleEntity = SingleTitleViewViewModel()
        singleTitleEntity.title = textCardBean.data.text
        list.add(singleTitleEntity)
    }

    private fun parseFollowCard(list: MutableList<BaseViewModel>, followCardBean: FollowCardBean) {

    }

    private fun parseVideoSmallCard(list: MutableList<BaseViewModel>, videoSmallCardBean: VideoSmallCardBean) {

    }

    private fun showData(result: MutableList<BaseViewModel>) {
        val recommendAdapter = recyclerView.adapter as RecommendAdapter
        recommendAdapter.setList(result)
    }

    private fun showFail(e: Exception?) {

    }

}