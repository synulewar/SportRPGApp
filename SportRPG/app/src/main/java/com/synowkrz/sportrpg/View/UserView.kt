package com.synowkrz.sportrpg.View

import com.synowkrz.sportrpg.Model.User

interface UserView {
    fun bindUserWithView(user: User)
}