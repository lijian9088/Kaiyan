package com.lyz.kaiyan.ui.home.recommend.adapter.model

/**
 * @author liyanze
 * @create 2021/01/26
 * @Describe
 */
class FollowCardModel : BaseModel() {

    var coverUrl: String? = null
    var videoTime = 0
    var authorUrl: String? = null
    var description: String? = null
    var title: String? = null
    var videoDescription: String? = null
    var userDescription: String? = null
    var nickName: String? = null
    var playerUrl: String? = null
    var blurredUrl: String? = null
    var videoId = 0

    // 点赞
    var collectionCount = 0

    // 分享
    var shareCount = 0

}