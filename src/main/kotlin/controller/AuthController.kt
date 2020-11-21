package controller

import helpers.encrypt
import repositories.SqlServer
import java.sql.ResultSet

class AuthController {

    companion object {
        var sqlServer = SqlServer()

        fun login( email: String, password:String) : Boolean{
            val storeProcedure="{CALL loginUser(?,?)}"
            val params=arrayOf(email,encrypt(password))
            val data:ResultSet?= sqlServer.execStoreProcedure(storeProcedure,params)

            if (data != null && data.next()) {
                println(data.getInt(1))
                println(data.getString(2))

                return true
            }

            return false

        }
    }

}