package com.example.moviepartiel.model

import android.os.Parcelable
import com.example.moviepartiel.removeHTML
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

/**
 * Created by Valentin Pigeau on 14/04/2019
 * Data class model of Movie with a Factory
 */

@Parcelize
data class Movie(val id: String, val title: String, val description: String, val image: Map<String, String>) :
    Parcelable {

    companion object {
        fun movieFactory(jsonObject: JSONObject,remHTML:Boolean=false): Movie {
            fun getKey(s: String): String {

                var res = if(remHTML){
                    removeHTML(s)
                }else{
                    jsonObject.getString(s).replace("</p>","</p><br><br>")
                }
                return res
            }

            fun getImage(s: String): String {
                return jsonObject.getJSONObject("image").getString(s)
            }

            return Movie(
                getKey("id"),
                getKey("name"),
                getKey("description").replace("null", "No description available"),
                mapOf("thumb_url" to getImage("thumb_url"), "screen_large_url" to getImage("screen_large_url"))
            )
        }
    }

}

