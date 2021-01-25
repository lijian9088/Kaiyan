package com.lyz.kaiyan.ui.home.recommend.adapter

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.lyz.kaiyan.ui.home.recommend.adapter.model.BaseViewModel
import com.lyz.kaiyan.ui.home.recommend.adapter.model.SingleTitleViewViewModel
import com.lyz.kaiyan.ui.home.recommend.adapter.model.TitleViewViewModel
import com.lyz.kaiyan.ui.home.recommend.adapter.provider.*

/**
 * @author liyanze
 * @create 2021/01/19
 * @Describe
 */
class RecommendAdapter : BaseProviderMultiAdapter<BaseViewModel> {

    constructor() {
        addItemProvider(TitleProvider() as BaseItemProvider<BaseViewModel>)
        addItemProvider(SingleTitleProvider() as BaseItemProvider<BaseViewModel>)
    }

    override fun getItemType(data: List<BaseViewModel>, position: Int): Int {
        if(data[position] is TitleViewViewModel) {
            return RecommendItemType.TITLE_VIEW
        }
        if(data[position] is SingleTitleViewViewModel) {
            return RecommendItemType.SINGLE_TITLE_VIEW
        }
        return RecommendItemType.TITLE_VIEW
    }
}