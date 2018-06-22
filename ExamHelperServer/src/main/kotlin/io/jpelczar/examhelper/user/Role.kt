package io.jpelczar.examhelper.user

enum class Role(vararg roles: Role) {
    STUDENT(),
    EXAMINER(STUDENT),
    TEACHER(EXAMINER, STUDENT),
    ADMIN(TEACHER, EXAMINER, STUDENT);

    val includedRoles = mutableSetOf<Role>()

    init {
        this.includedRoles.add(this)
        this.includedRoles.addAll(roles)
    }

    override fun toString(): String {
        return this.name
    }
}