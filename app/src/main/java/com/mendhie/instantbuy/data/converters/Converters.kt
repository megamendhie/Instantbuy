package com.mendhie.instantbuy.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mendhie.instantbuy.data.models.Prod

class InstantbuyConverter{
    companion object{
        @JvmStatic
        @TypeConverter
        fun weatherFromJson(json: String?): List<Prod>
                = if(json==null) listOf() else Gson().fromJson(json, object : TypeToken<List<Prod>>(){}.type)

        @JvmStatic
        @TypeConverter
        fun weatherToJson(data: List<Prod>)
                = Gson().toJson(data)
    }
}