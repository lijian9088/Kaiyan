package com.lyz.kaiyan.ui.home.recommend.adapter.provider

import android.widget.TextView
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lyz.kaiyan.R
import com.lyz.kaiyan.ui.home.recommend.adapter.model.TitleViewViewModel

/**
 * @author liyanze
 * @create 2021/01/22
 * @Describe
 */
open class TitleProvider : BaseItemProvider<TitleViewViewModel>() {

    override val itemViewType: Int
        get() = RecommendItemType.TITLE_VIEW

    override val layoutId: Int
        get() = R.layout.item_title

    override fun convert(helper: BaseViewHolder, entity: TitleViewViewModel) {
        helper.getView<TextView>(R.id.tvTitle).text = entity.title
        helper.getView<TextView>(R.id.tvActionTitle).text = entity.actionTitle
    }

}

