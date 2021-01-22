package com.lyz.kaiyan.http.server

import com.hjq.http.config.IRequestServer
import com.hjq.http.model.BodyType

/**
 * @author liyanze
 * @create 2021/01/22
 * @Describe
 */
class ReleaseServer : IRequestServer{

    override fun getHost(): String {
        return "https://baobab.kaiyanapp.com/"
    }

    override fun getPath(): String? {
//        return "api/v5/index/tab/allRec"
        return "api/"
    }

    override fun getType(): BodyType? {
        // 参数以 Json 格式提交（默认是表单）
        return BodyType.JSON
    }

}