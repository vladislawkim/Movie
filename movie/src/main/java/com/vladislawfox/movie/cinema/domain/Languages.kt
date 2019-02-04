package com.vladislawfox.movie.cinema.domain

import androidx.annotation.StringDef

/**
 * Created by vladislawfox on 2/4/19.
 */
class Languages {
    @StringDef(US)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Locale
    companion object {
        const val US = "en-US"
    }
}