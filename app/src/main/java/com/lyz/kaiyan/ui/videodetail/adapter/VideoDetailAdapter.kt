package com.lyz.kaiyan.ui.videodetail.adapter

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.lyz.kaiyan.contract.BaseModel
import com.lyz.kaiyan.ui.home.recommend.adapter.RecommendItemType
import com.lyz.kaiyan.ui.home.recommend.adapter.model.SingleTitleModel
import com.lyz.kaiyan.ui.home.recommend.adapter.model.VideoSmallCardModel
import com.lyz.kaiyan.ui.home.recommend.adapter.provider.SingleTitleProvider
import com.lyz.kaiyan.ui.home.recommend.adapter.provider.VideoSmallCardProvider
import com.lyz.kaiyan.ui.videodetail.adapter.model.LeftAlignTextHeaderModel
import com.lyz.kaiyan.ui.videodetail.adapter.model.ReplyModel
import com.lyz.kaiyan.ui.videodetail.adapter.model.TopModel
import com.lyz.kaiyan.ui.videodetail.adapter.provider.LeftAlignTextHeaderProvider
import com.lyz.kaiyan.ui.videodetail.adapter.provider.ReplyProvider
import com.lyz.kaiyan.ui.videodetail.adapter.provider.TopProvider

/**
 * @author liyanze
 * @create 2021/01/29
 * @Describe
 */
class VideoDetailAdapter :BaseProviderMultiAdapter<BaseModel>(){

    init {
        addItemProvider(TopProvider() as BaseItemProvider<BaseModel>)
        addItemProvider(SingleTitleProvider() as BaseItemProvider<BaseModel>)
        addItemProvider(VideoSmallCardProvider() as BaseItemProvider<BaseModel>)
        addItemProvider(LeftAlignTextHeaderProvider() as BaseItemProvider<BaseModel>)
        addItemProvider(ReplyProvider() as BaseItemProvider<BaseModel>)
    }

    override fun getItemType(data: List<BaseModel>, position: Int): Int {
        if(data[position] is TopModel){
            return VideoDetailItemType.TOP
        }
        if(data[position] is SingleTitleModel){
            return RecommendItemType.SINGLE_TITLE_VIEW
        }
        if(data[position] is VideoSmallCardModel){
            return RecommendItemType.VIDEO_CARD_VIEW
        }
        if(data[position] is LeftAlignTextHeaderModel){
            return VideoDetailItemType.LEFT_ALIGN_TEXT_HEADER
        }
        if(data[position] is ReplyModel){
            return VideoDetailItemType.REPLY
        }
        return 0
    }

}