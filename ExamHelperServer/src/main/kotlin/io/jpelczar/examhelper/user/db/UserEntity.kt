package io.jpelczar.examhelper.user.db

import io.jpelczar.examhelper.user.Role
import javax.persistence.*

@Entity
class UserEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var name = ""
    var password = ""
    var enabled = false

    @ElementCollection(targetClass = Role::class)
    @CollectionTable(name = "user_roles")
    @Enumerated(EnumType.STRING)
    var roles: MutableSet<Role> = mutableSetOf()

    constructor(name: String, password: String, enabled: Boolean, roles: MutableSet<Role>) : this() {
        this.name = name
        this.password = password
        this.enabled = enabled
        this.roles = roles
    }
}