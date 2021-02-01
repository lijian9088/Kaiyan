package com.lyz.kaiyan.ui.videodetail.adapter.provider

import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lyz.kaiyan.R
import com.lyz.kaiyan.ui.videodetail.adapter.VideoDetailItemType
import com.lyz.kaiyan.ui.videodetail.adapter.model.LeftAlignTextHeaderModel
import com.lyz.kaiyan.ui.videodetail.adapter.model.TopModel

/**
 * @author mac
 * @create 2021/01/30
 * @Describe
 */
class LeftAlignTextHeaderProvider : BaseItemProvider<LeftAlignTextHeaderModel>() {

    override val itemViewType: Int
        get() = VideoDetailItemType.LEFT_ALIGN_TEXT_HEADER
    override val layoutId: Int
        get() = R.layout.item_video_detail_left_text_header

    override fun convert(helper: BaseViewHolder, item: LeftAlignTextHeaderModel) {
        helper.getView<TextView>(R.id.tvTitle).text = item.text
    }

}