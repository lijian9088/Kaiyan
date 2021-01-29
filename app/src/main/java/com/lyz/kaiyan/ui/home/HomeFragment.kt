package com.lyz.kaiyan.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.ToastUtils
import com.lyz.kaiyan.R
import com.lyz.kaiyan.base.MyLazyFragment
import com.lyz.kaiyan.ui.home.daily.DailyFragment
import com.lyz.kaiyan.ui.home.follow.FollowFragment
import com.lyz.kaiyan.ui.home.recommend.RecommendFragment

class HomeFragment : MyLazyFragment(), View.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var tvHomeNav0: TextView
    private lateinit var tvHomeNav1: TextView
    private lateinit var tvHomeNav2: TextView
    private lateinit var tvBrand: TextView
    private lateinit var viewPager: ViewPager

    private lateinit var tvHomeNavList: List<TextView>

    override fun lazyInit() {
        view?.let { initView(it) }
        initEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private fun initView(root: View) {
        tvHomeNav0 = root.findViewById(R.id.tvNav0)
        tvHomeNav1 = root.findViewById(R.id.tvNav1)
        tvHomeNav2 = root.findViewById(R.id.tvNav2)
        tvBrand = root.findViewById(R.id.tvBrand)
        viewPager = root.findViewById(R.id.viewPager)

        tvHomeNavList = listOf(
            tvHomeNav0, tvHomeNav1, tvHomeNav2
        )

        val fragmentList = mutableListOf<Fragment>()
        fragmentList.add(RecommendFragment())
        fragmentList.add(FollowFragment())
        fragmentList.add(DailyFragment())

        viewPager.offscreenPageLimit = 2
        viewPager.adapter = HomePagerAdapter(
            childFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            fragmentList
        )
    }

    private fun initEvent() {
        for (tv in tvHomeNavList) {
            tv.setOnClickListener(this)
        }
        tvBrand.setOnClickListener(this)

        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateNav(position)
            }
        })
//        viewPager.currentItem = 1
        updateNav(0)
    }

    private fun updateNav(position: Int) {
        var view: View? = null
        if (position == 0) {
            view = tvHomeNav0
        }
        if (position == 1) {
            view = tvHomeNav1
        }
        if (position == 2) {
            view = tvHomeNav2
        }

        for (tv in tvHomeNavList) {
            if (tv == view) {
                tv.setTextColor(Color.BLACK)
                tv.textSize = 14f
            } else {
                tv.setTextColor(Color.GRAY)
                tv.textSize = 12f
            }
        }
    }

    override fun onClick(v: View?) {
        if (v == tvHomeNav0) {
            viewPager.currentItem = 0
        }

        if (v == tvHomeNav1) {
            viewPager.currentItem = 1
        }

        if (v == tvHomeNav2) {
            viewPager.currentItem = 2
        }

        if (v == tvBrand) {
            ToastUtils.showShort("品牌")
        }
    }
}