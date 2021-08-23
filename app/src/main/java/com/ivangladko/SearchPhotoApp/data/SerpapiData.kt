package com.ivangladko.SearchPhotoApp

data class SerpapiData(
    val images_results: List<Result>
)

data class Result(
    val thumbnail: String?,
    val original: String?,
    val title: String?,
    val link: String?,
    val source: String
)
