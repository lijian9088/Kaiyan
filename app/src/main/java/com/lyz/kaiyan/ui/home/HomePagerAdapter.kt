package com.lyz.kaiyan.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * @author liyanze
 * @create 2021/01/15
 * @Describe
 */
class HomePagerAdapter(fm: FragmentManager, list: MutableList<Fragment>) :
    FragmentStatePagerAdapter(fm) {

    var fragments: MutableList<Fragment> = list

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

}
