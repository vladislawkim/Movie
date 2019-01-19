package com.vladislawfox.auth.data.mapper

import com.vladislawfox.auth.data.model.SessionEntity
import com.vladislawfox.auth.domain.model.Session
import com.vladislawfox.base.domain.mapper.Mapper
import javax.inject.Inject

/**
 * Created by vladislawfox on 1/18/19.
 */
class SessionEntityMapper @Inject constructor() : Mapper<SessionEntity, Session>() {
    override fun reverse(to: Session): SessionEntity = SessionEntity(to.sessionId)

    override fun map(from: SessionEntity): Session = Session(from.sessionId)
}