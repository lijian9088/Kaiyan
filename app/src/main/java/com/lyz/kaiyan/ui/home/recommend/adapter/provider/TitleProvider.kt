package com.lyz.kaiyan.ui.home.recommend.adapter.provider

import android.widget.TextView
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lyz.kaiyan.R
import com.lyz.kaiyan.ui.home.recommend.adapter.RecommendItemType
import com.lyz.kaiyan.ui.home.recommend.adapter.model.TitleModel

/**
 * @author liyanze
 * @create 2021/01/22
 * @Describe
 */
open class TitleProvider : BaseItemProvider<TitleModel>() {

    override val itemViewType: Int
        get() = RecommendItemType.TITLE_VIEW

    override val layoutId: Int
        get() = R.layout.item_title

    override fun convert(helper: BaseViewHolder, item: TitleModel) {
        helper.getView<TextView>(R.id.tvTitle).text = item.title
        helper.getView<TextView>(R.id.tvActionTitle).text = item.actionTitle
    }

}

