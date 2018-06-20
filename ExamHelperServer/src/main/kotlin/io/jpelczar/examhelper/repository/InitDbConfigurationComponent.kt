package io.jpelczar.examhelper.repository

import io.jpelczar.examhelper.user.Role
import io.jpelczar.examhelper.user.db.UserDao
import io.jpelczar.examhelper.user.db.UserEntity
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class InitDbConfigurationComponent @Autowired constructor(private var userDao: UserDao) {

    val logger = LoggerFactory.getLogger(InitDbConfigurationComponent::class.java)!!

    @EventListener(ApplicationReadyEvent::class)
    fun applicationReadyEvent() {
        if (userDao.getUserByName("admin") == null) {
            userDao.save(UserEntity("admin", BCryptPasswordEncoder(11).encode("admin"), true, Role.ADMIN.includedRoles))
        }
    }
}