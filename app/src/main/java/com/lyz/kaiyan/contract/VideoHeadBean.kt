package com.lyz.kaiyan.contract

/**
 * @author mac
 * @create 2021/01/28
 * @Describe
 */
class VideoHeadBean(
    var videoTitle: String?,
    var category: String?,
    var video_description: String?,// 点赞
    var collectionCount: Int,// 分享
    var shareCount: Int,
    var avatar: String?,
    var nickName: String?,
    var userDescription: String?,
    var playerUrl: String?,
    var blurredUrl: String?,
    var videoId: Int
)