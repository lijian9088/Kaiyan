package com.lyz.kaiyan.ui.videodetail.adapter.model

import com.lyz.kaiyan.contract.BaseModel

/**
 * @author mac
 * @create 2021/01/30
 * @Describe
 */
class TopModel : BaseModel() {
    var title: String? = null
    var category: String? = null
    var description: String? = null
    var goodCount: String? = null
    var shareCount: String? = null
    var download: String? = null
    var favorite: String? = null

    var authorUrl: String? = null
    var nickName: String? = null
    var userDes: String? = null

    override fun toString(): String {
        return "TopModel(title=$title, category=$category, description=$description, goodCount=$goodCount, shareCount=$shareCount, download=$download, favorite=$favorite, authorUrl=$authorUrl, nickName=$nickName, userDes=$userDes)"
    }

}