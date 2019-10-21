

package com.xzy.ui.xdialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.EditText
import kotlinx.android.synthetic.main.confirm_dialog.*


class EditDialog(
    context: Context,
    private var yOffset: Int = 0,
    var positiveClickBlock: ((et:EditText) -> Unit)? = null,
    private var negativeClickBlock: (() -> Unit)? = null,
    private var cancelBlock: (() -> Unit)? = null
) : Dialog(context, R.style.EditDialog) {
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
        val view = LayoutInflater.from(context)
            .inflate(R.layout.edit_dialog,llActionArea,false)
        setContentView(view)
        btnPositive.setOnClickListener {
            positiveClickBlock?.invoke(view.findViewById(R.id.etContent))
            dismiss()
        }
        btnNegative.setOnClickListener {
            negativeClickBlock?.invoke()
            dismiss()
        }

        setCanceledOnTouchOutside(false)
        setCancelable(false)
        this.setCanceledOnTouchOutside(false)
    }

    override fun cancel() {
        super.cancel()
        cancelBlock?.invoke()
    }

}