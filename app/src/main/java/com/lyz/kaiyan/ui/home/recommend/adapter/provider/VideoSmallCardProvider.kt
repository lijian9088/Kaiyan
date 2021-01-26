package com.lyz.kaiyan.ui.home.recommend.adapter.provider

import android.widget.TextView
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.TimeUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lyz.kaiyan.R
import com.lyz.kaiyan.ui.home.recommend.adapter.RecommendItemType
import com.lyz.kaiyan.ui.home.recommend.adapter.model.VideoSmallCardModel

/**
 * @author liyanze
 * @create 2021/01/26
 * @Describe
 */
class VideoSmallCardProvider :BaseItemProvider<VideoSmallCardModel>(){

    override val itemViewType: Int
        get() = RecommendItemType.VIDEO_CARD_VIEW
    override val layoutId: Int
        get() = R.layout.item_small_video_card

    override fun convert(helper: BaseViewHolder, item: VideoSmallCardModel) {
        Glide.with(context)
            .asBitmap()
            .centerInside()
            .load(item.coverUrl)
            .transform(RoundedCorners(ConvertUtils.dp2px(16f)))
            .into(helper.getView(R.id.ivCover))

        helper.getView<TextView>(R.id.tvDuration).text = TimeUtils.millis2String(item.videoTime * 1000L,"mm:ss")
        helper.getView<TextView>(R.id.tvTitle).text = item.title
        helper.getView<TextView>(R.id.tvDes).text = item.description
    }

}