package app.services

import app.models.User

class Session {

    companion object {

        fun start( user:User ){
            println("Save user: ${user.email}")
        }

    }
}