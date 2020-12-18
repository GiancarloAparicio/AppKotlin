package application.services

import application.models.User

class Session {

    companion object {

        fun start( user:User ){
            println("Save user: ${user.email}")
        }

    }
}