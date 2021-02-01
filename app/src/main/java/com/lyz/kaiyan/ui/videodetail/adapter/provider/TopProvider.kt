package com.lyz.kaiyan.ui.videodetail.adapter.provider

import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lyz.kaiyan.R
import com.lyz.kaiyan.ui.videodetail.adapter.VideoDetailItemType
import com.lyz.kaiyan.ui.videodetail.adapter.model.TopModel

/**
 * @author mac
 * @create 2021/01/30
 * @Describe
 */
class TopProvider : BaseItemProvider<TopModel>() {

    override val itemViewType: Int
        get() = VideoDetailItemType.TOP
    override val layoutId: Int
        get() = R.layout.item_video_detail_top

    override fun convert(helper: BaseViewHolder, item: TopModel) {
        helper.getView<TextView>(R.id.tvTitle).text = item.title
        helper.getView<TextView>(R.id.tvCategory).text = item.category
        helper.getView<TextView>(R.id.tvDes).text = item.description
        helper.getView<TextView>(R.id.tvGood).text = item.goodCount
        helper.getView<TextView>(R.id.tvShare).text = item.shareCount

        Glide.with(context)
            .asBitmap()
            .circleCrop()
            .load(item.authorUrl)
            .into(helper.getView(R.id.ivAvatar))

        helper.getView<TextView>(R.id.tvAuthorTitle).text = item.nickName
        helper.getView<TextView>(R.id.tvAuthorDes).text = item.userDes
    }

}