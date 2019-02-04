package com.vladislawfox.movie.cinema.domain

import com.vladislawfox.movie.cinema.domain.model.Movie

class MovieWrapper(
    var top: Page<Movie>? = null,
    var popular: Page<Movie>? = null,
    var now: Page<Movie>? = null,
    var upcoming: Page<Movie>? = null)