package com.lyz.kaiyan.http.request

import com.hjq.http.config.IRequestApi

/**
 * @author liyanze
 * @create 2021/02/01
 * @Describe
 */
class VideoDetailMoreApi(var id: Int = 0) : IRequestApi {

    override fun getApi(): String {
        return "v4/video/related"
    }

}