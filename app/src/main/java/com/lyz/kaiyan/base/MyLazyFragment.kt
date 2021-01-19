package com.lyz.kaiyan.base

import com.blankj.utilcode.util.LogUtils

/**
 * @author liyanze
 * @create 2021/01/19
 * @Describe
 */
abstract class MyLazyFragment : MyFragment() {

    private var isLoaded = false

    override fun onResume() {
        super.onResume()
        //增加了Fragment是否可见的判断
        if (!isLoaded && !isHidden) {
            lazyInit()
            isLoaded = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyInit()
}
