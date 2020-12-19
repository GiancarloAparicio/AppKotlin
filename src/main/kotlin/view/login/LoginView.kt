package view.login

import app.services.Auth
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import app.models.User
import app.observer.DomainEvent
import app.observer.EventTypes
import app.observer.interfaces.IObserver
import app.services.Session
import tornadofx.View
import tornadofx.ViewTransition
import tornadofx.seconds
import view.home.HomeView

class LoginView: View(), IObserver {
    override val root : BorderPane by fxml()

    private val inputEmail : TextField by fxid()
    private val inputPassword : PasswordField by fxid()

    private val labelEmailError : Label by fxid()
    private val labelPasswordError : Label by fxid()

    private val eventBus : DomainEvent = DomainEvent.getInstance()

    init{
        eventBus.addListener(this)
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
                eventBus.throwEvent( EventTypes.USER_LOGIN, currentUser )

            }else{
                labelEmailError.text =  "Email or password invalid"
            }
       }

    }

    override fun event(type: String, data: Any) {
        if( type == EventTypes.USER_LOGIN && data is User ){
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