package view

import controller.AuthController
import controller.UserController
import helpers.encrypt
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
           val status=AuthController.login(email.text,password.text)

            if(status){
                replaceWith(HomeView::class, ViewTransition.Slide(0.4.seconds, ViewTransition.Direction.LEFT));
            }else{
                emailError.text=  "Email or password invalid"
            }
       }
    }

}