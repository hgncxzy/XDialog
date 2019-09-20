package com.xzy.ui.xdialog

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        top_dialog.setOnClickListener {
            val intent = Intent(this@MainActivity, TopDialogActivity::class.java)
            startActivity(intent)
        }

        confirm_dialog.setOnClickListener {
            val dialog = ConfirmDialog(
                context = this,
                content = "需要显示的内容",
                title = "标题",
                positiveText = "确定",
                negativeText = "取消",
                positiveClickBlock = {
                    Toast.makeText(this, "确定", Toast.LENGTH_SHORT).show()
                },
                negativeClickBlock = {
                    Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show()
                })
            dialog.show()
        }

        loading_dialog.setOnClickListener {
            val loadingDialog = LoadingDialog(this)
            loadingDialog.show("加载中")
        }

    }
}
