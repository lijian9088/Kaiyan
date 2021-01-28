package com.lyz.kaiyan.ui.home.recommend.adapter.provider

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.TimeUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lyz.kaiyan.R
import com.lyz.kaiyan.ui.home.recommend.RecommendFragment
import com.lyz.kaiyan.ui.home.recommend.adapter.RecommendItemType
import com.lyz.kaiyan.ui.home.recommend.adapter.model.FollowCardModel

/**
 * @author liyanze
 * @create 2021/01/26
 * @Describe
 */
open class FollowCardProvider : BaseItemProvider<FollowCardModel>() {

    override val itemViewType: Int
        get() = RecommendItemType.FOLLOW_CARD_VIEW

    override val layoutId: Int
        get() = R.layout.item_follow_card

    override fun convert(helper: BaseViewHolder, item: FollowCardModel) {
        val ivCover = helper.getView<ImageView>(R.id.ivCover)

        Glide.with(context)
            .asBitmap()
            .centerInside()
            .load(item.coverUrl)
            .transform(RoundedCorners(ConvertUtils.dp2px(16f)))
            .into(ivCover)

        Glide.with(context)
            .asBitmap()
            .circleCrop()
            .load(item.authorUrl)
            .into(helper.getView(R.id.ivAvatar))

        helper.getView<TextView>(R.id.tvDuration).text =
            TimeUtils.millis2String(item.videoTime * 1000L, "mm:ss")
        helper.getView<TextView>(R.id.tvTitle).text = item.title
        helper.getView<TextView>(R.id.tvDes).text = item.description

        ivCover.setOnClickListener(View.OnClickListener {
//            goVideoDetail(item)
            Navigation.findNavController(it).navigate(R.id.navigation_video_detail)
        })

    }

}