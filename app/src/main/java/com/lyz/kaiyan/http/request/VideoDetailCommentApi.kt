package com.lyz.kaiyan.http.request

import com.hjq.http.config.IRequestApi

/**
 * @author liyanze
 * @create 2021/02/01
 * @Describe
 */
class VideoDetailCommentApi(var videoId: Int = 0) : IRequestApi {

    //    fun setVideoId(value: Int): VideoDetailMoreApi {
//        videoId = value
//        return this
//    }

    override fun getApi(): String {
        return "v2/replies/video"
    }

}