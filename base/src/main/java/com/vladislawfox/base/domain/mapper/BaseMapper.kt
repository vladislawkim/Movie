package com.vladislawfox.base.domain.mapper

import java.util.ArrayList

/**
 * Created by vladislawfox on 1/18/19.
 */
abstract class BaseMapper<From, To> {
    abstract fun map(from: From): To
    fun map(froms: List<From>): List<To> {
        val result = ArrayList<To>(froms.size)
        for (from in froms) {
            result.add(map(from))
        }
        return result
    }
}