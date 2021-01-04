package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName
import java.util.*
//表示当前地区未来几天的天气信息，temperature表示温度，skycon表示天气情情况
class DailyResponse(val status: String, val result: Result) {

    class Result(val daily: Daily)

    class Daily(val temperature: List<Temperature>, val skycon: List<Skycon>,
                @SerializedName("life_index") val lifeIndex: LifeIndex,//生活指数
    )//风力
    //最高温最低温
    class Temperature(val max: Float, val min: Float)
    //天气状况|空气指数

    class Skycon(val value: String, val date: Date)

    //生活指数-coldRisk表示感冒指数-carWashing表示洗车指数-ultraviolet紫外线指数-dressing表示穿衣指数
    class LifeIndex(val coldRisk: List<LifeDescription>, val carWashing: List<LifeDescription>,
                    val ultraviolet: List<LifeDescription>, val dressing: List<LifeDescription>,
                    val comfort: List<LifeDescription>)

    //风力

    class LifeDescription(val desc: String)


}