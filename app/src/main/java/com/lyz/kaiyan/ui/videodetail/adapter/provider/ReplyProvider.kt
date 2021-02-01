package com.lyz.kaiyan.ui.videodetail.adapter.provider

import android.widget.TextView
import com.blankj.utilcode.util.TimeUtils
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lyz.kaiyan.R
import com.lyz.kaiyan.ui.videodetail.adapter.VideoDetailItemType
import com.lyz.kaiyan.ui.videodetail.adapter.model.ReplyModel
import com.lyz.kaiyan.ui.videodetail.adapter.model.TopModel

/**
 * @author mac
 * @create 2021/01/30
 * @Describe
 */
class ReplyProvider : BaseItemProvider<ReplyModel>() {

    override val itemViewType: Int
        get() = VideoDetailItemType.REPLY
    override val layoutId: Int
        get() = R.layout.item_video_detail_reply

    override fun convert(helper: BaseViewHolder, item: ReplyModel) {
        Glide.with(context)
            .asBitmap()
            .circleCrop()
            .load(item.avatar)
            .into(helper.getView(R.id.ivAvatar))

        helper.getView<TextView>(R.id.tvNickname).text = item.nickname
        helper.getView<TextView>(R.id.tvLikeCount).text = item.likeCount.toString()
        helper.getView<TextView>(R.id.tvMessage).text = item.message
        helper.getView<TextView>(R.id.tvCreateTime).text =
            "发布于${TimeUtils.millis2String(item.createTime * 1000L)}"

    }

}