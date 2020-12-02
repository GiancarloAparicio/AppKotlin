package services

import models.User
import java.sql.ResultSet

class Session {

    companion object {

        fun start( user:User ){
            println("Save user: ${user.email}")
        }

    }
}