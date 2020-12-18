package application.services

import application.database.Database
import application.helpers.encrypt
import application.models.User
import java.sql.ResultSet

class Auth {

    companion object {
        var dataBase = Database.getInstance()

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