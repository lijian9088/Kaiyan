package com.lyz.kaiyan.http.request

import com.hjq.http.config.IRequestApi
import com.hjq.http.config.IRequestServer

/**
 * @author liyanze
 * @create 2021/01/26
 * @Describe
 */
open class CustomApi(url: String?) : IRequestServer, IRequestApi {

    private var url: String? = null

    init {
        this.url = url
    }

    override fun getHost(): String {
        return ""
    }

    override fun getApi(): String {
        url?.let{
            return it
        }
        return ""
    }

}