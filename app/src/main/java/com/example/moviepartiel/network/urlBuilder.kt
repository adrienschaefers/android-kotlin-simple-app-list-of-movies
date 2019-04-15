package com.example.moviepartiel.network

import com.example.moviepartiel.Env
import com.example.moviepartiel.Env.Companion.currentEnvironment

/**
 * Based on work of Yvan Moté on 15/02/2019.
 * Edited by Valentin Pigeau on 14/04/2019
 * Class with getters of url to request Api
 * Data change: baseUrl and apiKey
 */
class UrlBuilder {
    companion object {

        private enum class EnvironmentUrlBuilder(val baseUrl: String, val apiKey: String) {
            PRODUCTION("https://comicvine.gamespot.com/api", ""),
            DEVELOPMENT("https://comicvine.gamespot.com/api", ""),
            TEST("https://comicvine.gamespot.com/api", "")
        }

        private val requestVal by lazy {
            when (currentEnvironment) {
                Env.Companion.Environment.PRODUCTION -> EnvironmentUrlBuilder.PRODUCTION
                Env.Companion.Environment.DEVELOPMENT -> EnvironmentUrlBuilder.DEVELOPMENT
                Env.Companion.Environment.TEST -> EnvironmentUrlBuilder.TEST
            }
        }

        fun getUrlAllMovie(): String {
            return "${requestVal.baseUrl}/movies/?api_key=${requestVal.apiKey}&format=json"
        }

        fun getUrlMovieFilter(filter: ArrayList<String>): String {
            return "${requestVal.baseUrl}/movies/?api_key=${requestVal.apiKey}&filter=${filter[0]}:${filter[1]}&format=json"
        }
        fun getUrlMovieField(field: String): String {
            return "${requestVal.baseUrl}/movies/?api_key=${requestVal.apiKey}&field_list=$field&format=json"
        }
        fun getUrlMovieFilterField(filter: ArrayList<String>,field:String): String {
            return "${requestVal.baseUrl}/movies/?api_key=${requestVal.apiKey}&filter=${filter[0]}:${filter[1]}&field_list=$field&format=json"
        }
    }
}
