package com.sunnyweather.android.ui.setting

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sunnyweather.android.R
import kotlinx.android.synthetic.main.activity_setting.*


class SettingActivity1 : AppCompatActivity() {
    private var seekBar: SeekBar? = null
    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //使背景与上框融合
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }
        setContentView(R.layout.activity_setting1)
        radioButton_no.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        seekBar = findViewById<View>(R.id.seekBar1) as SeekBar
        textView = findViewById<View>(R.id.text1) as TextView
        seekBar!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // 当拖动条的滑块位置发生改变时触发该方法,在这里直接使用参数progress，即当前滑块代表的进度值
                textView!!.setText(Integer.toString(progress) + "分钟")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                Log.e("------------", "开始滑动！")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Log.e("------------", "停止滑动！")
            }
        })
    }

}