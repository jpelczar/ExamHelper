package io.jpelczar.examhelper.user.db

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDao : CrudRepository<UserEntity, Long> {
    fun getUserByName(name: String): UserEntity?
}