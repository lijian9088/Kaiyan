package com.lyz.kaiyan.ui.videodetail

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyz.kaiyan.R
import com.lyz.kaiyan.contract.BaseModel
import com.lyz.kaiyan.contract.VideoHeadBean
import com.lyz.kaiyan.ui.videodetail.adapter.VideoDetailAdapter
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
        val EXTRA_VIDEO_HEAD = "videoHead"
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

    }

    private fun initIntent() {
        videoHead = intent.getSerializableExtra(EXTRA_VIDEO_HEAD) as VideoHeadBean
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
        list.add(topModel)

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
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
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
        if (isPlay && !isPause) {
            coverVideoView.onConfigurationChanged(this, newConfig, orientationUtils, true, true);
        }
    }
}