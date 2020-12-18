package app.models

import java.sql.Date
import java.sql.ResultSet

class User(data:ResultSet) {
     var email: String = data.getString(1)
     var name: String = data.getString(2)
     var lastName: String = data.getString(3)
     var createAt: Date = data.getDate(4)
     var updateAt: Date? = data.getDate(5)
     var deleteAt: Date? = data.getDate(6)

    companion object {
        private lateinit var user: User

        fun getInstance(): User {
            return User.user
        }

        fun setInstance(data:ResultSet){
            User.user=User(data)
        }

    }
}