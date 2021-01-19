package com.lyz.kaiyan.ui.home.recommend.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lyz.kaiyan.R

/**
 * @author liyanze
 * @create 2021/01/19
 * @Describe
 */
class RecommendAdapter(data: MutableList<String>?) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_recommend, data) {

    override fun convert(holder: BaseViewHolder, item: String) {
        val layoutPosition = holder.layoutPosition
        holder.getView<TextView>(R.id.tvTitle).text = "posistion:$layoutPosition, item:$item"
    }
}