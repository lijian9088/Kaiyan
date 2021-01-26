package com.lyz.kaiyan.ui.home.recommend.adapter

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.lyz.kaiyan.bean.VideoSmallCardBean
import com.lyz.kaiyan.ui.home.recommend.adapter.model.*
import com.lyz.kaiyan.ui.home.recommend.adapter.provider.FollowCardProvider
import com.lyz.kaiyan.ui.home.recommend.adapter.provider.SingleTitleProvider
import com.lyz.kaiyan.ui.home.recommend.adapter.provider.TitleProvider
import com.lyz.kaiyan.ui.home.recommend.adapter.provider.VideoSmallCardProvider

/**
 * @author liyanze
 * @create 2021/01/19
 * @Describe
 */
class RecommendAdapter : BaseProviderMultiAdapter<BaseModel>() {

    init {
        addItemProvider(TitleProvider() as BaseItemProvider<BaseModel>)
        addItemProvider(SingleTitleProvider() as BaseItemProvider<BaseModel>)
        addItemProvider(FollowCardProvider() as BaseItemProvider<BaseModel>)
        addItemProvider(VideoSmallCardProvider() as BaseItemProvider<BaseModel>)
    }

    override fun getItemType(data: List<BaseModel>, position: Int): Int {
        if(data[position] is TitleModel) {
            return RecommendItemType.TITLE_VIEW
        }
        if(data[position] is SingleTitleModel) {
            return RecommendItemType.SINGLE_TITLE_VIEW
        }
        if(data[position] is FollowCardModel) {
            return RecommendItemType.FOLLOW_CARD_VIEW
        }
        if(data[position] is VideoSmallCardModel) {
            return RecommendItemType.VIDEO_CARD_VIEW
        }
        return -1
    }
}