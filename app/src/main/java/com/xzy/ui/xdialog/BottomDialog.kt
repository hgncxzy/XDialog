package com.xzy.ui.xdialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import kotlinx.android.synthetic.main.custom_buttom_alert_layout.*

class BottomDialog(
    context: Context,
    var btn1Text: String? = null,
    var btn2Text: String? = null,
    var btn3Text: String? = null,
    var btn1ClickBlock: (() -> Unit)? = null,
    var btn2ClickBlock: (() -> Unit)? = null,
    var btn3ClickBlock: (() -> Unit)? = null,
    var confirmClickBlock: (() -> Unit)? = null
) : Dialog(context, R.style.BottomDialog) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = window ?: return
        window.decorView.setPadding(0, 0, 0, 0)
        val lp = window.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.BOTTOM
        window.attributes = lp
        setContentView(R.layout.custom_buttom_alert_layout)
        configView()
    }

    private fun configView() {
        btn1Text?.let {
            btn1.visibility = View.VISIBLE
            btn1.text = btn1Text
        }

        btn2Text?.let {
            btn2.visibility = View.VISIBLE
            btn2.text = btn2Text
        }

        btn3Text?.let {
            btn3.visibility = View.VISIBLE
            btn3.text = btn3Text
        }

        if (!btn1Text.isNullOrEmpty()) {
            btn1.visibility = View.VISIBLE
            btn1.text = btn1Text
            btn1.setOnClickListener {
                initAlertButtonBg()
                setAlertButtonBg(btn1)
                btn1ClickBlock?.invoke()
//                dismiss()
            }
        }

        if (!btn2Text.isNullOrEmpty()) {
            btn2.visibility = View.VISIBLE
            btn2.text = btn2Text
            btn2.setOnClickListener {
                initAlertButtonBg()
                setAlertButtonBg(btn2)
                btn2ClickBlock?.invoke()
//                dismiss()
            }
        }

        if (!btn3Text.isNullOrEmpty()) {
            btn3.visibility = View.VISIBLE
            btn3.text = btn3Text
            btn3.setOnClickListener {
                initAlertButtonBg()
                setAlertButtonBg(btn3)
                btn3ClickBlock?.invoke()
//                dismiss()
            }
        }

        btn_ok.setOnClickListener {
            confirmClickBlock?.invoke()
            dismiss()
        }



        setCanceledOnTouchOutside(true)
        setCancelable(true)
        this.setCanceledOnTouchOutside(true)
    }

    private fun initAlertButtonBg() {
        btn1?.setBackgroundResource(R.drawable.bg_bottom_btn_alert_normal)
        btn1?.setTextColor(context.resources.getColor(R.color.text_color_333,null))

        btn2?.setBackgroundResource(R.drawable.bg_bottom_btn_alert_normal)
        btn2?.setTextColor(context.resources.getColor(R.color.text_color_333,null))

        btn3?.setBackgroundResource(R.drawable.bg_bottom_btn_alert_normal)
        btn3?.setTextColor(context.resources.getColor(R.color.text_color_333,null))
    }

    private fun setAlertButtonBg(btn: Button){
        btn.setBackgroundResource(R.drawable.bg_bottom_btn_alert_focused)
        btn.setTextColor(context.resources.getColor(R.color.text_color_fff,null))
    }
}