package com.example.wanandroid.ui.tree

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wanandroid.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class TreeFragment : Fragment() {
    val viewModel by lazy {
        ViewModelProvider(this).get(TreeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tree, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val linearLayout: LinearLayout = requireView().findViewById(R.id.linear_layout)

        viewModel.treeLiveData.observe(viewLifecycleOwner) {
            val result = it.getOrNull()
            for (data in result?.data!!) {
                val category = TextView(activity)
                val chipGroup = ChipGroup(activity)
//                为体系分类
                category.text = data.name
                category.textSize = 16f
                val measuredWidth =
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                val measuredHeight =
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                category.measure(measuredWidth, measuredHeight)
                category.height = category.measuredHeight
                category.width = category.measuredWidth
                linearLayout.addView(category)

//              增加体系的流式布局
                for (child in data.children) {
                    val chip = Chip(activity)
                    chip.text = child.name
                    chip.setOnClickListener {
                        val id = child.id
                        val intent = Intent(context, ArticleInTree::class.java)
                        intent.putExtra("id", id.toString())
                        startActivity(intent)
                    }
                    chipGroup.addView(chip)
                }
                linearLayout.addView(chipGroup)

            }
        }
    }
}