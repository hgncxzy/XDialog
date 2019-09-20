
package com.xzy.ui.xdialog

import android.app.Dialog
import android.content.Context
import kotlinx.android.synthetic.main.dialog_loading.*

class LoadingDialog(context: Context) : Dialog(context, R.style.LoadingDialog) {
    private var defaultMsg: String

    init {
        setContentView(R.layout.dialog_loading)
        setCanceledOnTouchOutside(false)
        defaultMsg = "加载中"
    }

    fun show(msg: String = defaultMsg) {
        loading_msg.text = msg
        super.show()
    }
}
