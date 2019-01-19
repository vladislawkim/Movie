package com.vladislawfox.auth.data.mapper

import com.vladislawfox.auth.data.model.RequestTokenEntity
import com.vladislawfox.auth.domain.model.RequestToken
import com.vladislawfox.base.domain.mapper.Mapper
import javax.inject.Inject

/**
 * Created by vladislawfox on 1/18/19.
 */
class RequestTokenEntityMapper @Inject constructor() : Mapper<RequestTokenEntity, RequestToken>() {
    override fun reverse(to: RequestToken): RequestTokenEntity = RequestTokenEntity(to.expiresAt, to.requestToken)

    override fun map(from: RequestTokenEntity): RequestToken = RequestToken(from.expiresAt, from.requestToken)
}