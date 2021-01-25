package com.lyz.kaiyan.ui.home.recommend.adapter.provider

import android.widget.TextView
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lyz.kaiyan.R
import com.lyz.kaiyan.ui.home.recommend.adapter.model.SingleTitleViewViewModel

/**
 * @author liyanze
 * @create 2021/01/22
 * @Describe
 */
open class SingleTitleProvider : BaseItemProvider<SingleTitleViewViewModel>() {

    override val itemViewType: Int
        get() = RecommendItemType.SINGLE_TITLE_VIEW

    override val layoutId: Int
        get() = R.layout.item_single_title

    override fun convert(helper: BaseViewHolder, entity: SingleTitleViewViewModel) {
        helper.getView<TextView>(R.id.tvTitle).text = entity.title
    }

}

