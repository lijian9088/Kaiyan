package com.lyz.kaiyan.http.request

import com.hjq.http.config.IRequestApi

/**
 * @author liyanze
 * @create 2021/01/22
 * @Describe
 */
class RecommendApi : IRequestApi {
    override fun getApi(): String {
        return "v5/index/tab/allRec"
    }
}