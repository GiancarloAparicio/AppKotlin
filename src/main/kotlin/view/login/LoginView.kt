package view.login

import app.services.Auth
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import app.models.User
import app.events.UserLoginEvent
import app.events.interfaces.IObserver
import app.events.types.EventsTypes
import app.services.Session
import tornadofx.View
import tornadofx.ViewTransition
import tornadofx.seconds
import view.home.HomeView

class LoginView: View(), IObserver {

    //Components GUI
    private val inputEmail : TextField by fxid()
    private val inputPassword : PasswordField by fxid()
    private val labelEmailError : Label by fxid()
    private val labelPasswordError : Label by fxid()

    //Events
    private val userLoginEvent : UserLoginEvent = UserLoginEvent.getInstance()

    //Root
    override val root : BorderPane by fxml()

    init{
        userLoginEvent.addListener(this)
    }

    /**
     * Functions GUI
     */

    fun login(){
        val email = inputEmail.text
        val password = inputPassword.text

        labelEmailError.text = if( email == "" ) "Email invalid" else ""
        labelPasswordError.text = if( password == "" ) "Password invalid" else ""

       if( email != "" && password != "" ){
            val currentUser: User? = Auth.verifyAndReturnUser( email, password )

            if(currentUser is User){
                changeSceneToHome()
                userLoginEvent.throwEvent( EventsTypes.USER_LOGIN, currentUser )

            }else{
                labelEmailError.text =  "Email or password invalid"
            }
       }

    }

    override fun event( typeEvent : String, data : Any) {
        if( typeEvent == EventsTypes.USER_LOGIN && data is User ){
            Session.start( data )
        }

    }

    /**
     * Functions helpers
     */

    private fun changeSceneToHome(){
        replaceWith( HomeView::class, ViewTransition.Slide(0.7.seconds, ViewTransition.Direction.LEFT) );
    }

}