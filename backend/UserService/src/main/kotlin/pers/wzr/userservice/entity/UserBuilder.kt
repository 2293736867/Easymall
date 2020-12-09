package pers.wzr.userservice.entity

class UserBuilder {
    private var user = User()
    fun id(id:String):UserBuilder
    {
        user.id = id
        return this
    }

    fun username(username:String):UserBuilder
    {
        user.username = username
        return this
    }

    fun password(password:String):UserBuilder
    {
        user.password = password
        return this
    }

    fun nickname(nickname:String):UserBuilder
    {
        user.nickname = nickname
        return this
    }

    fun email(email:String):UserBuilder
    {
        user.email = email
        return this
    }

    fun build() = user
}