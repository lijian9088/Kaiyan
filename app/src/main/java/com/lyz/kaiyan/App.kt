package com.lyz.kaiyan

import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.hjq.http.EasyConfig
import com.hjq.http.config.IRequestServer
import com.lyz.kaiyan.http.model.RequestHandler
import com.lyz.kaiyan.http.server.ReleaseServer
import com.lyz.kaiyan.other.AppConfig
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.tencent.mmkv.MMKV
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


/**
 * @author liyanze
 * @create 2021/01/19
 * @Describe
 */
class App : Application() {

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.purple_500, android.R.color.white)//全局设置主题颜色
            ClassicsHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator(){ context: Context, refreshLayout: RefreshLayout ->
            //指定为经典Footer，默认是 BallPulseFooter
            ClassicsFooter(context).setDrawableSize(20f)
        }
    }

    override fun onCreate() {
        super.onCreate()
        initMMKV()
        initNetwork()
    }

    private fun initMMKV() {
        val rootDir = MMKV.initialize(this)
        LogUtils.d("mmkv root: $rootDir")
    }

    private fun initNetwork() {


        // 网络请求框架初始化
        var server:IRequestServer
        if (AppConfig.isDebug()) {
//            server = TestServer()
            server = ReleaseServer()
        } else {
            server = ReleaseServer()
        }

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10 * 1000L,TimeUnit.MILLISECONDS)
            .build()

        EasyConfig.with(okHttpClient)
            // 是否打印日志
            .setLogEnabled(BuildConfig.DEBUG)
//            .setLogEnabled(false)
            // 设置服务器配置
            .setServer(server)
            // 设置请求处理策略
            .setHandler(RequestHandler(this))
            // 设置请求重试次数
            .setRetryCount(3)
            // 添加全局请求参数
            //.addParam("token", "6666666")
            // 添加全局请求头
            //.addHeader("time", "20191030")
            // 启用配置
            .into()
    }
}