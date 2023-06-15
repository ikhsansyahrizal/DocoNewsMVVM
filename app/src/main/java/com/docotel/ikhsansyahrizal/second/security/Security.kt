package com.docotel.ikhsansyahrizal.second.security

object Security {

    init {
        System.loadLibrary("native-lib")
    }

    external fun apiKey(): String

    external fun baseUrl() : String

    external fun country() : String


}