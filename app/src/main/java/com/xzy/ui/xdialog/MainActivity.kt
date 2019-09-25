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
            val confirmDialog = ConfirmDialog(
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
            confirmDialog.show()
        }

        loading_dialog.setOnClickListener {
            val loadingDialog = LoadingDialog(this)
            loadingDialog.show("加载中")
        }

        bottom_custom_dialog.setOnClickListener {
            val bottomDialog = BottomDialog(
                context = this,
                btn1Text = "test1",
                btn2Text = "test2",
                btn3Text = "test3",
                btn1ClickBlock = {
                    Toast.makeText(this, "1", Toast.LENGTH_SHORT).show()
                },
                btn2ClickBlock = {
                    Toast.makeText(this, "2", Toast.LENGTH_SHORT).show()
                },
                btn3ClickBlock = {
                    Toast.makeText(this, "3", Toast.LENGTH_SHORT).show()
                },
                confirmClickBlock = {
                    Toast.makeText(this, "confirm", Toast.LENGTH_SHORT).show()
                }
            )
            bottomDialog.show()
        }

    }
}
