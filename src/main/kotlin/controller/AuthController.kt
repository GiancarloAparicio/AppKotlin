package controller

import helpers.encrypt
import database.SqlServer
import models.User
import java.sql.ResultSet

class AuthController {

    companion object {
        var dataBase = SqlServer()

        fun verify( email: String, password:String) : Boolean{

            //TODO: Refactor the code into a DAO class
            val storeProcedure="{CALL loginUser(?,?)}"
            val params=arrayOf(email,encrypt(password))
            val data:ResultSet?= dataBase.execStoreProcedure(storeProcedure,params)

            if (data != null && data.next()) {
                User.setInstance(data)

                return true
            }

            return false
        }
    }

}