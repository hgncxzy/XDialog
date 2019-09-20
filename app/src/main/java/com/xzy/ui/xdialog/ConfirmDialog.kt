/*
 * Copyright 2015-2019 Hive Box.
 */

package com.xzy.ui.xdialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import kotlinx.android.synthetic.main.confirm_dialog.*

class ConfirmDialog(
    context: Context,
    private var yOffset: Int = 0,
    var view: View? = null,
    @LayoutRes
    var viewLayoutResId: Int = 0,
    var title: String? = null,
    var content: String? = null,
    var positiveText: String? = null,
    var negativeText: String? = null,
    var positiveClickBlock: (() -> Unit)? = null,
    private var negativeClickBlock: (() -> Unit)? = null,
    private var cancelBlock: (() -> Unit)? = null,
    @DrawableRes
    var iconRes: Int = 0
) : Dialog(context, R.style.ConfirmDialog) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = window ?: return
        window.decorView.setPadding(0, 0, 0, 0)
        val lp = window.attributes
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER

        if (yOffset != 0) {
            lp.y = yOffset
        }

        window.attributes = lp
        setContentView(R.layout.confirm_dialog)
        configView()
    }

    override fun cancel() {
        super.cancel()
        cancelBlock?.invoke()
    }

    private fun configView() {
        if (iconRes != 0) {
            llIcon.visibility = View.VISIBLE
            ivIcon.setImageResource(iconRes)
        } else {
            llIcon.visibility = View.GONE
        }

        title?.let {
            tvTitle.visibility = View.VISIBLE
            tvTitle.text = title
        }

        content?.let {
            tvContent.visibility = View.VISIBLE
            tvContent.text = content
        }

        var customView: View? = null
        if (view != null) {
            customView = view
        } else if (viewLayoutResId != 0) {
            customView = LayoutInflater.from(context)
                .inflate(viewLayoutResId, llCustomArea, false)
        }

        if (customView != null) {
            llPresetArea.visibility = View.GONE
            llCustomArea.visibility = View.VISIBLE
            llCustomArea.addView(customView)
        } else {
            llPresetArea.visibility = View.VISIBLE
            llCustomArea.visibility = View.GONE
        }

        if (!positiveText.isNullOrEmpty()) {
            llActionArea.visibility = View.VISIBLE
            btnPositive.visibility = View.VISIBLE
            btnPositive.text = positiveText
            btnPositive.setOnClickListener {
                positiveClickBlock?.invoke()
                dismiss()
            }
        }

        if (!negativeText.isNullOrEmpty()) {
            llActionArea.visibility = View.VISIBLE
            btnNegative.visibility = View.VISIBLE
            btnNegative.text = negativeText
            btnNegative.setOnClickListener {
                negativeClickBlock?.invoke()
                dismiss()
            }
        }

        setCanceledOnTouchOutside(false)
        setCancelable(false)
        this.setCanceledOnTouchOutside(false)
    }
}