package controller

import repositories.SqlServer

class UserController(private val email: String, private val password:String){
    init {
        var DB=SqlServer()
        println(email)
        println(password)
    }

}