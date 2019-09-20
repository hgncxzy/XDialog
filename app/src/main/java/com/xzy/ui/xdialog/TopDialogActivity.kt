package com.xzy.ui.xdialog

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dialog_top_in.*

/**
 * steps:
 *
 * 1. 自定义布局文件
 * 2. 自定义从顶部滑出的动画
 * 3. 在 Activity 中为布局设置动画并且显示出来
 * */
class TopDialogActivity : AppCompatActivity() {
    private var shadeView: View? = null
    private var llRank: LinearLayout? = null
    private var mHideAction: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_top_in)
        shadeView = findViewById(R.id.shadeView)
        llRank = findViewById(R.id.llRank)

        //延迟一秒显示位于顶部的 dialog
        Handler().postDelayed({
            //设置动画以及显示布局
            mHideAction = AnimationUtils.loadAnimation(this@TopDialogActivity, R.anim.dialog_top_in)
            llRank?.startAnimation(mHideAction)
            llRank?.visibility = View.VISIBLE
            //显示阴影
            shadeView?.visibility = View.VISIBLE
        }, 1000)

        //阴影点击事件，隐藏阴影和布局
        shadeView?.setOnClickListener {
            hideLayout()
        }
        // 点击事件
        listener()
    }

    private fun hideLayout() {
        shadeView?.visibility = View.GONE
        llRank?.visibility = View.GONE
    }

    private fun listener() {
        item1.setOnClickListener {
            Toast.makeText(this, "click1", Toast.LENGTH_SHORT).show()
            hideLayout()
        }
        item2.setOnClickListener {
            Toast.makeText(this, "click2", Toast.LENGTH_SHORT).show()
            hideLayout()
        }
        item3.setOnClickListener {
            Toast.makeText(this, "click3", Toast.LENGTH_SHORT).show()
            hideLayout()
        }
        item4.setOnClickListener {
            Toast.makeText(this, "click4", Toast.LENGTH_SHORT).show()
            hideLayout()
        }

    }
}
