package com.lyz.kaiyan.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.lyz.kaiyan.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        initView(root)
        initEvent()
        return root
    }

    private fun initView(root: View) {
        val tvHomeNav0: TextView = root.findViewById(R.id.tvNav0)
        val tvHomeNav1: TextView = root.findViewById(R.id.tvNav1)
        val tvHomeNav2: TextView = root.findViewById(R.id.tvNav2)
        val tvBrand: TextView = root.findViewById(R.id.tvBrand)
        val viewPager: ViewPager = root.findViewById(R.id.viewPager)

        val list = MutableList<Fragment>(2) {
            Fragment(R.layout.fragment_community)
        }
        viewPager.adapter = HomePagerAdapter(childFragmentManager, list)
    }

    private fun initEvent() {

    }
}