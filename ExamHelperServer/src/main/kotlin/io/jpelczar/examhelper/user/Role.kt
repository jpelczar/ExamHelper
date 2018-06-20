package io.jpelczar.examhelper.user

enum class Role(vararg roles: Role) {
    STUDENT(STUDENT),
    EXAMINER(EXAMINER, STUDENT),
    TEACHER(TEACHER, EXAMINER, STUDENT),
    ADMIN(ADMIN, TEACHER, EXAMINER, STUDENT);

    val includedRoles = mutableSetOf<Role>()

    init {
        this.includedRoles.addAll(roles)
    }

    override fun toString(): String {
        return this.name
    }
}