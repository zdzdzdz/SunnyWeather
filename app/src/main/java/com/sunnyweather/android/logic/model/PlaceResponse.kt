package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

class PlaceResponse(val status: String, val places: List<Place>)
//name为地址名 //该地区的地址
class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)
//经纬度
class Location(val lng: String, val lat: String)