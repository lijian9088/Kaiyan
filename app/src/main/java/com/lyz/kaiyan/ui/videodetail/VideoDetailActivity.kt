package com.lyz.kaiyan.ui.videodetail

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.hjq.http.EasyHttp
import com.hjq.http.listener.OnHttpListener
import com.lyz.kaiyan.R
import com.lyz.kaiyan.bean.*
import com.lyz.kaiyan.contract.BaseModel
import com.lyz.kaiyan.contract.VideoHeadBean
import com.lyz.kaiyan.http.request.VideoDetailCommentApi
import com.lyz.kaiyan.http.request.VideoDetailMoreApi
import com.lyz.kaiyan.ui.home.recommend.adapter.model.SingleTitleModel
import com.lyz.kaiyan.ui.home.recommend.adapter.model.VideoSmallCardModel
import com.lyz.kaiyan.ui.videodetail.adapter.VideoDetailAdapter
import com.lyz.kaiyan.ui.videodetail.adapter.model.LeftAlignTextHeaderModel
import com.lyz.kaiyan.ui.videodetail.adapter.model.ReplyModel
import com.lyz.kaiyan.ui.videodetail.adapter.model.TopModel
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.simple.SimpleMultiListener
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer


class VideoDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_VIDEO_HEAD = "videoHead"
    }

    lateinit var videoHead: VideoHeadBean
    lateinit var coverVideoView: StandardGSYVideoPlayer
    lateinit var refreshLayout: SmartRefreshLayout
    lateinit var recyclerView: RecyclerView
    lateinit var orientationUtils: OrientationUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_detail)


        initIntent()

//        ImmersionBar.with(this).init()

        initView()
        initData()
        initEvent()

        initVideo()

        initHttp()
    }

    private fun initVideo() {

//        val source1 =
//            "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4"
//        coverVideoView.setUp(source1, true, "测试视频")
        coverVideoView.setUp(videoHead.playerUrl, true, videoHead.videoTitle)

//        //增加封面
//        val imageView = ImageView(this)
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
//        imageView.setImageResource(R.mipmap.xxx1)
//        coverVideoView.setThumbImageView(imageView)
        //增加title
        coverVideoView.getTitleTextView().setVisibility(View.VISIBLE)
        //设置返回键
        coverVideoView.getBackButton().setVisibility(View.VISIBLE)
        //设置旋转
        orientationUtils = OrientationUtils(this, coverVideoView)
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        coverVideoView.getFullscreenButton().setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                orientationUtils.resolveByClick()
//                coverVideoView.startWindowFullscreen(this@VideoDetailActivity, false, true)
            }
        })
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false)
        //是否可以滑动调整
        coverVideoView.setIsTouchWiget(true)
        //设置返回按键功能
        coverVideoView.getBackButton().setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                onBackPressed()
            }
        })
        coverVideoView.setVideoAllCallBack(object : GSYSampleCallBack() {
            override fun onPrepared(url: String?, vararg objects: Any?) {
                super.onPrepared(url, *objects)
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true)
                isPlay = true
            }

            override fun onQuitFullscreen(url: String?, vararg objects: Any?) {
                super.onQuitFullscreen(url, *objects)
                orientationUtils.backToProtVideo()
            }
        })
        coverVideoView.startPlayLogic()
    }

    private fun initHttp() {
        val videoId = videoHead.videoId

        requestDetailMore(videoId)

        requestComment(videoId)
    }

    private fun requestComment(videoId: Int) {
        EasyHttp.get(this)
            .api(VideoDetailCommentApi(videoId))
            .request(object : OnHttpListener<MultiPageBean> {
                override fun onSucceed(result: MultiPageBean?) {
                    parseComment(result)
                }

                override fun onFail(e: Exception?) {
                    showFail(e)
                }

            })
    }

    private fun parseComment(result: MultiPageBean?) {
        val itemList = result?.itemList
        if(itemList == null) {
            return
        }

        val dataList = mutableListOf<BaseModel>()

        for(i in itemList.indices) {
            val item = itemList[i]
            val type = item.type
            val json = GsonUtils.toJson(item)
            if("leftAlignTextHeader" == type) {
                val bean = GsonUtils.fromJson(json, LeftAlignTextHeaderBean::class.java)
                parseLeftAlignTextHeader(dataList, bean)
            }
            if("reply" == type) {
                val bean = GsonUtils.fromJson(json, ReplyBean::class.java)
                parseReply(dataList, bean)
            }
        }

        showCommentData(dataList)
    }

    private fun showCommentData(dataList: MutableList<BaseModel>) {
        (recyclerView.adapter as VideoDetailAdapter).addData(dataList)
    }

    private fun parseReply(dataList: MutableList<BaseModel>, bean: ReplyBean) {
        val model = ReplyModel()
        model.avatar = bean.data.user.avatar
        model.nickname = bean.data.user.nickname
        model.likeCount = bean.data.likeCount
        model.message = bean.data.message
        model.createTime = bean.data.createTime
        dataList.add(model)
    }

    private fun parseLeftAlignTextHeader(
        dataList: MutableList<BaseModel>,
        bean: LeftAlignTextHeaderBean
    ) {
        val model = LeftAlignTextHeaderModel()
        model.text = bean.data.text
        dataList.add(model)
    }

    private fun requestDetailMore(videoId: Int) {
        EasyHttp.get(this)
//            .api(VideoDetailMoreApi().setVideoId(videoId))
            .api(VideoDetailMoreApi(videoId))
            .request(object : OnHttpListener<MultiPageBean> {
                override fun onSucceed(result: MultiPageBean?) {
                    parseResult(result)
                    refreshLayout.finishRefresh(true)
                }

                override fun onFail(e: Exception?) {
                    showFail(e)
                    refreshLayout.finishRefresh(false)
                }

            })
    }

    private fun showFail(e: Exception?) {
        val msg = "fail:${e?.message}"
        LogUtils.e("fail:$msg")
        ToastUtils.showShort("fail:$msg")
    }

    private fun parseResult(result: MultiPageBean?) {
        val itemList = result?.itemList
        if(itemList == null) {
            return
        }

        val dataList = mutableListOf<BaseModel>()

        for(i in itemList.indices) {
            val item = itemList[i]
            val type = item.type
            val json = GsonUtils.toJson(item)
            if("textCard" == type) {
                val bean =
                    GsonUtils.fromJson<TextCardBean>(json,
                        TextCardBean::class.java)
                parseTextCard(dataList, bean)
            }
            if("videoSmallCard" == type) {
                val bean =
                    GsonUtils.fromJson<VideoSmallCardBean>(json,
                        VideoSmallCardBean::class.java)
                parseVideoSmallCard(dataList, bean)
            }
        }

        showDetailMoreData(dataList)
    }

    private fun showDetailMoreData(dataList: MutableList<BaseModel>) {
        (recyclerView.adapter as VideoDetailAdapter).addData(dataList)
    }

    private fun parseTextCard(dataList: MutableList<BaseModel>, bean: TextCardBean) {
        val model = SingleTitleModel()
        model.title = bean.data.text
        dataList.add(model)
    }

    private fun parseVideoSmallCard(dataList: MutableList<BaseModel>, bean: VideoSmallCardBean) {
        val data = bean.data
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
        dataList.add(model)
    }

    private fun initIntent() {
        videoHead = intent.getSerializableExtra(EXTRA_VIDEO_HEAD) as VideoHeadBean

        val view: View = window.decorView.findViewById(android.R.id.content)
        Glide.with(this)
            .asBitmap()
            .load(videoHead.blurredUrl)
            .into(object : CustomViewTarget<View, Bitmap>(view) {
                override fun onLoadFailed(errorDrawable: Drawable?) {

                }

                override fun onResourceCleared(placeholder: Drawable?) {

                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val bitmapDrawable = BitmapDrawable(resource)
                    view.background = bitmapDrawable
                }
            })
    }

    private fun initView() {
        coverVideoView = findViewById(R.id.coverVideoView)
        refreshLayout = findViewById(R.id.refreshLayout)
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initData() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = VideoDetailAdapter()

        val list = ArrayList<BaseModel>()
        val topModel = TopModel()
        topModel.title = videoHead.videoTitle
        topModel.category = videoHead.category
        topModel.description = videoHead.video_description
        topModel.goodCount = "点赞 ${videoHead.collectionCount}"
        topModel.shareCount = "分享 ${videoHead.shareCount}"
        topModel.authorUrl = videoHead.avatar
        topModel.nickName = videoHead.nickName
        topModel.userDes = videoHead.userDescription
        list.add(topModel)

        LogUtils.d("topModel:$topModel")

        (recyclerView.adapter as VideoDetailAdapter).setList(list)
    }

    private fun initEvent() {
        refreshLayout.setOnMultiListener(object : SimpleMultiListener() {
            override fun onLoadMore(refreshLayout: RefreshLayout) {

            }

            override fun onRefresh(refreshLayout: RefreshLayout) {

            }
        })
    }

    private var isPause: Boolean = false
    private var isPlay: Boolean = false

    override fun onPause() {
        coverVideoView.currentPlayer.onVideoPause()
        super.onPause()
        isPause = true
    }

    override fun onResume() {
        coverVideoView.currentPlayer.onVideoResume()
        super.onResume()
        isPause = false
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
        orientationUtils.releaseListener()
    }

    override fun onBackPressed() {
        //先返回正常状态
        if(orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            coverVideoView.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        coverVideoView.setVideoAllCallBack(null);
        super.onBackPressed()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        //如果旋转了就全屏
        if(isPlay && !isPause) {
            coverVideoView.onConfigurationChanged(this, newConfig, orientationUtils, true, true);
        }
    }
}