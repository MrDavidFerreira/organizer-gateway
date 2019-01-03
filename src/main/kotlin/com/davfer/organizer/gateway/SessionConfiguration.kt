package com.davfer.organizer.gateway

import org.springframework.session.data.redis.RedisFlushMode
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer

@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE) //Any changes that have occurred on the session will be persisted immediately
class SessionConfiguration : AbstractHttpSessionApplicationInitializer()