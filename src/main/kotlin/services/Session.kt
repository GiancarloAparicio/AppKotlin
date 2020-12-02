package services

import models.User
import java.sql.ResultSet

class Session {

    companion object {

        fun start( user:User ){
            print("Save user: ${user.email}")
        }

    }
}