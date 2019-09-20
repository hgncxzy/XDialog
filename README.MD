### 效果图

- ![效果演示图](https://github.com/hgncxzy/XDialog/blob/master/gif/GIF.gif)

### 加载对话框

1. 定义 style

   ```xml
     <style name="LoadingDialog" parent="@android:style/Theme.Dialog">
           <!--去掉Dialog的windowFrame框-->
           <item name="android:windowFrame">@null</item>
           <!--浮在activity之上-->
           <item name="android:windowIsFloating">true</item>
           <!--非半透明-->
           <item name="android:windowIsTranslucent">false</item>
           <!--去掉title-->
           <item name="android:windowNoTitle">true</item>
           <!--dialog的背景透明-->
           <item name="android:windowBackground">@android:color/transparent</item>
       </style>
   ```

   

2. 定义 layout

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:tools="http://schemas.android.com/tools"
       android:layout_width="80dp"
       android:layout_height="82dp"
       android:layout_gravity="center"
       android:background="#E63A3A3A"
       android:orientation="vertical"
       tools:ignore="SpUsage">
   
       <ProgressBar
           android:id="@+id/progress"
           style="?android:progressBarStyleLargeInverse"
           android:indeterminate="true"
           android:indeterminateTintMode="src_atop"
           android:layout_width="24dp"
           android:layout_height="24dp"
           android:layout_centerHorizontal="true"
           android:layout_marginTop="20dp"
          />
   
       <TextView
           android:id="@+id/loading_msg"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:layout_below="@id/progress"
           android:layout_centerHorizontal="true"
           android:layout_marginTop="6dp"
           android:gravity="center"
           android:textColor="#ffffff"
           android:textSize="10dp"
           tools:text="加载中"
            />
   </RelativeLayout>
   ```

   

3. 定义 LoadingDialog 类，继承自 Dialog

   ```kotlin
   
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
   ```

   

4. 调用方式

   ```kotlin
    loading_dialog.setOnClickListener {
               val loadingDialog = LoadingDialog(this)
               loadingDialog.show("加载中")
           }
   ```

### 确定取消对话框

1. 定义 style

   ```xml
   <style name="ConfirmDialog" parent="android:Theme.Dialog">
           <item name="android:windowIsFloating">true</item>
           <item name="android:windowIsTranslucent">false</item>
           <item name="android:windowNoTitle">true</item>
           <item name="android:windowFullscreen">false</item>
           <item name="android:windowAnimationStyle">@null</item>
           <item name="android:backgroundDimEnabled">true</item>
           <item name="android:backgroundDimAmount">0.4</item>
       </style>
   ```

   

2. 定义 layout

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:tools="http://schemas.android.com/tools"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       android:layout_gravity="center"
       android:background="@drawable/hc_confirm_dialog_bg"
       tools:ignore="MergeRootFrame,Overdraw,SpUsage">
   
       <LinearLayout
           android:id="@+id/llPresetArea"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:layout_gravity="center"
           android:orientation="vertical"
           android:gravity="center"
           tools:ignore="Overdraw">
   
           <LinearLayout
               android:id="@+id/llIcon"
               android:layout_width="match_parent"
               android:layout_height="wrap_content"
               android:gravity="center"
               android:paddingLeft="24dp"
               android:paddingTop="24dp"
               android:paddingRight="24dp">
   
               <ImageView
                   android:id="@+id/ivIcon"
                   android:layout_width="wrap_content"
                   android:layout_height="wrap_content"
                   tools:ignore="ContentDescription"/>
           </LinearLayout>
   
   
           <TextView
               android:id="@+id/tvTitle"
               android:layout_width="match_parent"
               android:layout_height="wrap_content"
               android:layout_marginLeft="24dp"
               android:layout_marginTop="24dp"
               android:layout_marginRight="24dp"
               android:gravity="center"
               android:maxEms="10"
               android:textColor="#333333"
               android:visibility="gone"
               tools:visibility="visible" />
   
           <TextView
               android:id="@+id/tvContent"
               android:layout_width="match_parent"
               android:layout_height="wrap_content"
               android:layout_marginLeft="24dp"
               android:layout_marginTop="20dp"
               android:layout_marginRight="24dp"
               android:gravity="center"
               android:textColor="#333333"
               android:textSize="14dp"
               android:visibility="gone"
               tools:ignore="SpUsage"
               tools:text="test"
               tools:visibility="visible" />
   
           <View
               android:layout_width="match_parent"
               android:layout_height="1dp"
               android:layout_marginTop="25dp"
               android:background="#cccccc" />
   
           <LinearLayout
               android:id="@+id/llActionArea"
               android:layout_width="match_parent"
               android:layout_height="40dp"
               android:gravity="center"
               android:visibility="invisible"
               tools:visibility="visible">
   
               <Button
                   android:id="@+id/btnNegative"
                   android:layout_width="0dp"
                   android:layout_height="wrap_content"
                   android:layout_weight="1"
                   android:background="#ffffff"
                   android:textColor="#333333"
                   android:textSize="18dp"
                   android:gravity="center"
                   android:layout_gravity="center"
                   android:textAlignment="center" />
   
               <View
                   android:layout_width="1dp"
                   android:layout_height="match_parent"
                   android:background="#cccccc" />
   
               <Button
                   android:id="@+id/btnPositive"
                   android:layout_width="0dp"
                   android:layout_height="wrap_content"
                   android:layout_weight="1"
                   android:background="#ffffff"
                   android:textColor="#333333"
                   android:textSize="18dp"
                   android:gravity="center"
                   android:textAlignment="center"
                   android:layout_gravity="center" />
   
           </LinearLayout>
   
       </LinearLayout>
   
       <LinearLayout
           android:id="@+id/llCustomArea"
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:layout_gravity="center"
           android:orientation="vertical"
           android:visibility="gone"
           tools:ignore="Overdraw" />
   </FrameLayout>
   ```

   

3. 定义 ConfirmDialog，继承自 Dialog

   ```kotlin
   
   
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
   ```

   

4. 调用方式

   ```kotlin
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
   ```

### 自定义对话框

参考确认取消对话框，修改布局和位置即可。不再赘述。

### 顶部滑出 Dialog

1. 定义 layout

   ```xml
   <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:tools="http://schemas.android.com/tools"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       tools:ignore="HardcodedText">
   
       <TextView
           android:id="@+id/textView"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:layout_centerInParent="true"
           android:text="Hello World!" />
   
       <!-- 下滑弹出的布局阴影部分 -->
       <View
           android:id="@+id/shadeView"
           android:layout_width="match_parent"
           android:layout_height="match_parent"
           android:background="#70000000"
           android:visibility="gone" />
   
       <!-- 下滑弹出的布局 -->
       <LinearLayout
           android:id="@+id/llRank"
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:background="#FFFFFF"
           android:orientation="vertical"
           android:paddingStart="20dp"
           android:visibility="gone"
           tools:ignore="RtlSymmetry">
   
           <TextView
               android:id="@+id/item1"
               android:layout_width="match_parent"
               android:layout_height="50dp"
               android:gravity="center_vertical"
               android:text="综合排序" />
   
           <TextView
               android:id="@+id/item2"
               android:layout_width="match_parent"
               android:layout_height="50dp"
               android:gravity="center_vertical"
               android:text="价格又高到低" />
   
           <TextView
               android:id="@+id/item3"
               android:layout_width="match_parent"
               android:layout_height="50dp"
               android:gravity="center_vertical"
               android:text="价格又高到低" />
   
           <TextView
               android:id="@+id/item4"
               android:layout_width="match_parent"
               android:layout_height="50dp"
               android:gravity="center_vertical"
               android:text="月销量" />
   
       </LinearLayout>
   </RelativeLayout>
   
   ```

   

2. 定义滑出动画 dialog_top_in.xml

   ```xml
   <set xmlns:android="http://schemas.android.com/apk/res/android">
       <scale
           android:duration="200"
           android:fillAfter="false"
           android:fromXScale="1.0"
           android:fromYScale="0.0"
           android:interpolator="@android:anim/accelerate_interpolator"
           android:pivotX="100%"
           android:pivotY="0%"
           android:toXScale="1.0"
           android:toYScale="1.0" />
   </set>
   ```

   

3. 调用方式

```kotlin
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

```

