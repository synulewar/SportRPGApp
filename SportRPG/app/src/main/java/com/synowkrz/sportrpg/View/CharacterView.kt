package com.synowkrz.sportrpg.View

import com.synowkrz.sportrpg.Model.User

interface CharacterView {
    fun bindUserWithView(user: User)
}