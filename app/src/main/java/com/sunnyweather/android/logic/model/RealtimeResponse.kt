package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName
//status为请求状态，返回当前实时天气信息
class RealtimeResponse(val status: String, val result: Result) {

    class Result(val realtime: Realtime)
    //skycon表示当前天气情况，temperature表示当前温度，
    class Realtime(val skycon: String, val temperature: Float,
                   @SerializedName("air_quality") val airQuality: AirQuality,
                   @SerializedName("wind") val wind: Wind)

    //包含一些空气质量的数据
    class AirQuality(val aqi: AQI)
    //AQI为空气质量指数
    class AQI(val chn: Float)

    class Wind(val speed: Float, val direction: Float)

}