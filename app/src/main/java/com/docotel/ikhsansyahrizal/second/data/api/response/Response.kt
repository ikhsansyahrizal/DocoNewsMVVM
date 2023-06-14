package com.docotel.ikhsansyahrizal.second.data.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("totalResults")
	val totalResults: Int,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>,

	@field:SerializedName("status")
	val status: String
)