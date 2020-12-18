package view.login

import application.services.Auth
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import application.models.User
import application.services.Session
import tornadofx.View
import tornadofx.ViewTransition
import tornadofx.seconds
import view.home.HomeView

class LoginView: View(){
    override val root : BorderPane by fxml()

    private val inputEmail : TextField by fxid()
    private val inputPassword : PasswordField by fxid()

    private val labelEmailError : Label by fxid()
    private val labelPasswordError : Label by fxid()

    fun login(){
        val email = inputEmail.text
        val password = inputPassword.text

        labelEmailError.text = if( email == "" ) "Email invalid" else ""
        labelPasswordError.text = if( password == "" ) "Password invalid" else ""

       if( email != "" && password != "" ){
            val currentUser: User? = Auth.verifyAndReturnUser( email,password )

            if(currentUser != null){
                Session.start( currentUser )
                replaceWith( HomeView::class, ViewTransition.Slide(0.7.seconds, ViewTransition.Direction.LEFT) );
            }else{
                labelEmailError.text =  "Email or password invalid"
            }
       }
    }

}