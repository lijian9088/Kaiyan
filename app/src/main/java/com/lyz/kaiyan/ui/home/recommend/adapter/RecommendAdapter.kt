package com.lyz.kaiyan.ui.home.recommend.adapter

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.lyz.kaiyan.ui.home.recommend.adapter.entity.BaseEntity
import com.lyz.kaiyan.ui.home.recommend.adapter.entity.TitleEntity
import com.lyz.kaiyan.ui.home.recommend.adapter.provider.TITLE_VIEW
import com.lyz.kaiyan.ui.home.recommend.adapter.provider.TitleProvider

/**
 * @author liyanze
 * @create 2021/01/19
 * @Describe
 */
class RecommendAdapter : BaseProviderMultiAdapter<BaseEntity> {

    constructor() {
        addItemProvider(TitleProvider() as BaseItemProvider<BaseEntity>)
//        test(TitleProvider())
    }

//    fun test(provider : BaseItemProvider<out BaseEntity>){
//
//    }

    override fun getItemType(data: List<BaseEntity>, position: Int): Int {
        if(data[position] is TitleEntity) {
            return TITLE_VIEW
        }
        return TITLE_VIEW
    }
}