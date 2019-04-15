package com.example.moviepartiel

/**
 * Created by Valentin Pigeau on 14/04/2019
 */


fun removeHTML(s: String): String {
    return s.replace("</p>","\n\n").replace("<[^>]*>".toRegex(), "")
}
