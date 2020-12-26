package view.home

import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import app.models.User
import app.events.UserLoginEvent
import app.events.interfaces.IObserver
import tornadofx.*
import view.home.dashboard._delivery.Delivery
import view.home.dashboard._history.History
import view.home.dashboard._home.Home
import view.home.dashboard._products.Products
import view.home.dashboard._settings.Settings
import view.home.dashboard._users.Users

class HomeView() : View(), IObserver {

    //Views
    private val home: Home by inject()
    private val delivery : Delivery by inject()
    private val history : History by inject()
    private val settings : Settings by inject()
    private val users : Users by inject()
    private val products : Products by inject()

    //Components GUI
    /*TODO: Por alguna razon falla :(, no usar esta linea*/
    //private val dashBoard : BorderPane by fxid()
    @FXML
    lateinit var labelNameUser : Label
    @FXML
    lateinit var dashBoard: BorderPane


    //Events
    private val userLoginEvent = UserLoginEvent.getInstance()

    //Root
    override val root : BorderPane by fxml()

    init{
        initializeFirstScene()
        userLoginEvent.addListener(this)
    }

    /**
     * Functions GUI
     */

    fun setScene(event:Event){
        val buttonPressed = event.target.toString()
        val buttonName : String = buttonPressed.substring( buttonPressed.indexOf("'")+1, buttonPressed.length-1)

        when (buttonName) {
            "Home"-> dashBoard.center = home.root
            "Delivery"-> dashBoard.center = delivery.root
            "History"-> dashBoard.center = history.root
            "Settings"-> dashBoard.center = settings.root
            "Users"-> dashBoard.center = users.root
            "Products"-> dashBoard.center = products.root
            else -> {
                dashBoard.center = home.root
                println("Error: scene not found")
            }
        }
    }

    override fun event(typeEvent: String, data: Any) {
        initialiseDataUser()
    }

    /**
     * Functions helpers
     */

    private fun initializeFirstScene(){
        dashBoard.center = home.root
    }

    private fun initialiseDataUser(){
        labelNameUser.text = User.getInstance().name
    }

}