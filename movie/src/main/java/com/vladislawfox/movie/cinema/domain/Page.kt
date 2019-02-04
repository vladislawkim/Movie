package com.vladislawfox.movie.cinema.domain

/**
 * Created by vladislawfox on 2/4/19.
 */
class Page<T>(
    val currentPage: Int = 0,
    val totalPages: Int = 0,
    val totalItems: Int = 0,
    val items: List<T> = emptyList()
)