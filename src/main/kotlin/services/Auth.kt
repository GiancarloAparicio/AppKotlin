package services

import helpers.encrypt
import database.SqlServer
import models.User
import java.sql.ResultSet

class Auth {

    companion object {
        var dataBase = SqlServer()

        fun verifyAndReturnUser( email: String, password:String) : User?{
            //TODO: Refactor the code into a DAO class
            val storeProcedure="{CALL loginUser(?,?)}"

            val params : Array<Any> = arrayOf(email,encrypt(password))
            val data:ResultSet?= dataBase.execStoreProcedure( storeProcedure , params )

            if (data != null && data.next()) {

                User.setInstance(data)
                return User.getInstance()
            }

            return null
        }
    }

}