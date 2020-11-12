package controller

import helpers.encrypt
import repositories.SqlServer
import java.sql.ResultSet



class UserController(){

    private var SQL = SqlServer()
    private lateinit var email: String

    init {
        println(this.getUsers())

    }

     fun getUsers(): String {
        var data:ResultSet= this.SQL.select("SELECT * FROM users;")
        var response:String =""
        while(data.next()) {
            response+="\n ID: "+data.getString(1)+" ; Password: "+data.getString(2);
        }
        return response
    }

    fun createUser(email: String,password: String,name:String, last_name:String){
        encrypt(password)
        var data= this.SQL.insert("")
    }

    fun login( email: String, password:String):Boolean{
        this.email=email
        return false
    }

}