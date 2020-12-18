package application.models

import java.sql.Date
import java.sql.ResultSet

class User(data:ResultSet) {
     var id: Int = data.getInt(1)
     var email: String = data.getString(2)
     var name: String = data.getString(3)
     var lastName: String = data.getString(4)
     var createAt: Date = data.getDate(5)
     var updateAt: Date? = data.getDate(6)
     var deleteAt: Date? = data.getDate(7)

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