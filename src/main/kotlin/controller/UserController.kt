package controller

import helpers.encrypt
import repositories.SqlServer
import java.sql.ResultSet



class UserController(email: String,password: String){

    private var SQL = SqlServer()
    private lateinit var email: String

    init {
        this.login(email,password)
    }

     fun getUsers(): String? {
         return try {
             val data:ResultSet= this.SQL.select("SELECT * FROM users;")
             var response:String =""
             while(data.next()) {
                 response+="\n ID: "+data.getString(1)+" ; User: "+ data.getString(2) + " ; Password: " + data.getString(3)
             }
             response
         }catch (e:Error){
             println(e.message)
             null
         }
    }


    fun login( email: String, password:String):Boolean{

        return false
    }

}