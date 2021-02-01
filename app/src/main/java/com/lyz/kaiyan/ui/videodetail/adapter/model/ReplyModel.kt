package com.lyz.kaiyan.ui.videodetail.adapter.model

import com.lyz.kaiyan.contract.BaseModel

/**
 * @author mac
 * @create 2021/01/30
 * @Describe
 */
class ReplyModel : BaseModel() {
    var avatar: String? = null
    var nickname: String? = null
    var likeCount: Int = 0
    var message: String? = null
    var createTime: Long = 0
}