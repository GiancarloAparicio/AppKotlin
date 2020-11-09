package controller

class UserController(var email: String,var password:String){
    init {
        println("Login success")
        println(email)
        println(password)
    }

    fun login(){
        print("hello")
    }
}