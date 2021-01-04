package com.sunnyweather.android.ui.setting

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.weather.WeatherViewModel
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.activity_weather.*


class SettingActivity : AppCompatActivity() {
    //设置是否允许后台刷新的变量
    private var a: Int? =1;
    private var seekBar: SeekBar? = null
    private var textView: TextView? = null
    val viewModel by lazy { ViewModelProviders.of(this).get(WeatherViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        //使背景与上框融合
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }

        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                // TODO Auto-generated method stub
                if(radioButton_yes.getText().toString().equals("否")){
                    a=0;
                }
            }

        })

        seekBar = findViewById<View>(R.id.seekBar1) as SeekBar
        textView = findViewById<View>(R.id.text1) as TextView
        seekBar!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // 当拖动条的滑块位置发生改变时触发该方法,在这里直接使用参数progress，即当前滑块代表的进度值
                textView!!.setText(Integer.toString(progress) + "分钟")
                if(a==1){
                    //如果允许刷新，则根据用户输入的更新频率更新
                    handler?.postDelayed(runnable, 1000 * progress.toLong())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                Log.e("------------", "开始滑动！")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Log.e("------------", "停止滑动！")
            }


        })




    }

    private var handler: Handler? = Handler()
    private val runnable: Runnable = object : Runnable {
        override fun run() {
            viewModel.refreshWeather(viewModel.locationLng, viewModel.locationLat)
            swipeRefresh.isRefreshing = true
            handler?.postDelayed(this, 1000 * 120) // 间隔120秒
        }

    }
    override fun onDestroy() {
        handler?.removeCallbacks(runnable) //停止刷新
        super.onDestroy()
    }

}