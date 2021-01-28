package com.lyz.kaiyan.ui.home.recommend.adapter.provider

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.TimeUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lyz.kaiyan.R
import com.lyz.kaiyan.contract.VideoHeadBean
import com.lyz.kaiyan.ui.home.recommend.adapter.RecommendItemType
import com.lyz.kaiyan.ui.home.recommend.adapter.model.VideoSmallCardModel

/**
 * @author liyanze
 * @create 2021/01/26
 * @Describe
 */
class VideoSmallCardProvider : BaseItemProvider<VideoSmallCardModel>() {

    override val itemViewType: Int
        get() = RecommendItemType.VIDEO_CARD_VIEW
    override val layoutId: Int
        get() = R.layout.item_small_video_card

    override fun convert(helper: BaseViewHolder, item: VideoSmallCardModel) {
        val ivCover = helper.getView<ImageView>(R.id.ivCover)
        Glide.with(context)
            .asBitmap()
            .centerInside()
            .load(item.coverUrl)
            .transform(RoundedCorners(ConvertUtils.dp2px(16f)))
            .into(ivCover)

        helper.getView<TextView>(R.id.tvDuration).text =
            TimeUtils.millis2String(item.videoTime * 1000L, "mm:ss")
        helper.getView<TextView>(R.id.tvTitle).text = item.title
        helper.getView<TextView>(R.id.tvDes).text = item.description

        ivCover.setOnClickListener(View.OnClickListener {
//            goVideoDetail(item)
            Navigation.findNavController(it).navigate(R.id.navigation_user)
        })
    }

    private fun goVideoDetail(item: VideoSmallCardModel) {
        val videoHeadBean = VideoHeadBean(
            item.title,
            item.description,
            item.videoDescription,
            item.collectionCount,
            item.shareCount,
            item.authorUrl,
            item.nickName,
            item.userDescription,
            item.playerUrl,
            item.blurredUrl,
            item.videoId
        )


    }

}