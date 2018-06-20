package io.jpelczar.examhelper.user

import io.jpelczar.examhelper.user.db.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service("examUserDetailsService")
class ExamUserDetailsService @Autowired constructor(private var userDao: UserDao) : UserDetailsService {


    override fun loadUserByUsername(username: String): UserDetails {
        val userEntity = userDao.getUserByName(username) ?: throw UserNotFoundException(username)
        val authorities: Collection<GrantedAuthority> = userEntity.roles.map { role -> SimpleGrantedAuthority(role.toString()) }

        return User(userEntity.name, userEntity.password, userEntity.enabled, true, true, true, authorities)
    }
}

class UserNotFoundException(name: String) : RuntimeException("User $name not found")