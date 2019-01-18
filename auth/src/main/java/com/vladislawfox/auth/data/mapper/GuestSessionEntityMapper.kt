package com.vladislawfox.auth.data.mapper

import com.vladislawfox.auth.data.model.GuestSessionEntity
import com.vladislawfox.auth.domain.model.GuestSession
import com.vladislawfox.base.mapper.Mapper
import javax.inject.Inject

/**
 * Created by vladislawfox on 1/18/19.
 */
class GuestSessionEntityMapper @Inject constructor() : Mapper<GuestSessionEntity, GuestSession>() {
    override fun reverse(to: GuestSession): GuestSessionEntity = GuestSessionEntity(to.sessionId, to.expiresAt)

    override fun map(from: GuestSessionEntity): GuestSession = GuestSession(from.sessionId, from.expiresAt)
}