package app.DAO

import app.database.Database
import app.models.User
import java.sql.ResultSet

class UserDAO {
    companion object{
        private var dataBase = Database.getInstance()

        fun getAll() : MutableList<User> {
            val storeProcedure = "{CALL getAllUsers()}"
            val data: ResultSet = dataBase.execStoreProcedure( storeProcedure )
            var listUsers: MutableList<User> = mutableListOf();

            while ( data.next() ){
                listUsers.add( User( data ) )
            }

            return listUsers
        }
    }
}