package com.lyz.kaiyan.ui.videodetail.adapter

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.lyz.kaiyan.contract.BaseModel
import com.lyz.kaiyan.ui.videodetail.adapter.model.TopModel
import com.lyz.kaiyan.ui.videodetail.adapter.provider.TopProvider

/**
 * @author liyanze
 * @create 2021/01/29
 * @Describe
 */
class VideoDetailAdapter :BaseProviderMultiAdapter<BaseModel>(){

    init {
        addItemProvider(TopProvider() as BaseItemProvider<BaseModel>)
    }

    override fun getItemType(data: List<BaseModel>, position: Int): Int {
        if(data[position] is TopModel){
            return VideoDetailItemType.TOP
        }
        return 0
    }

}