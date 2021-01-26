package com.lyz.kaiyan.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lyz.kaiyan.R
import com.lyz.kaiyan.base.MyLazyFragment
import kotlin.random.Random

class UserFragment : MyLazyFragment() {

    companion object {
        fun newInstance() = UserFragment()
    }

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_user, container, false)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        initView(root)
        return root
    }

    private fun initView(root: View) {
        val textView = root.findViewById<TextView>(R.id.textView)
        val btn = root.findViewById<Button>(R.id.btn)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val random = Random(101)

        btn.setOnClickListener(View.OnClickListener {
            val nextInt = random.nextInt()
            viewModel.text.value = ("nextInt:$nextInt")
        })
    }

    override fun lazyInit() {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}