package view

import controller.UserController
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import tornadofx.View
import tornadofx.ViewTransition
import tornadofx.seconds

class LoginView: View(){
    override val root : BorderPane by fxml()

    private val email : TextField by fxid()
    private val password : PasswordField by fxid()

    private val emailError : Label by fxid()
    private val passwordError : Label by fxid()

    fun login(){

        emailError.text= if(email.text=="") "Email invalid" else ""
        passwordError.text= if(password.text=="") "Password invalid" else ""

       if(email.text != "" && password.text != ""){
           val user=UserController(email.text,password.text)
           replaceWith(HomeView::class, ViewTransition.Slide(0.4.seconds, ViewTransition.Direction.LEFT));
       }

    }



}


/*
import tornadofx.View

import tornadofx.button
import tornadofx.label
import tornadofx.vbox

class LoginView: View(){
    override val root = vbox {
        button("Login")
        label("Hello Work")
    }
}
*/