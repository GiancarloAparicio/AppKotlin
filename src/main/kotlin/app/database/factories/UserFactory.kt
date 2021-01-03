package app.database.factories

import app.database.factories.abstract.Factory
import app.helpers.encrypt
import app.models.User
import java.sql.ResultSet

class UserFactory : Factory() {

    override fun create( quantity : Int ): Any {

        var listUsers = mutableListOf<User>()

        for(item in 1..quantity){
            var email = faker.internet().emailAddress()
            var password = encrypt("0000")
            var name = faker.name().firstName()
            var lastName = faker.name().lastName()

            var storeProcedure = "{CALL createUser(?,?,?,?)}"
            val params : Array<Any?> = arrayOf(email,password,name,lastName)
            val data: ResultSet = dataBase.execStoreProcedure( storeProcedure, params )

            if ( data.next() ) {
                listUsers.add( User(data) )
            }
        }

        return listUsers
    }
}