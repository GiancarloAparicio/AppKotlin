package view.home

import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import models.User
import tornadofx.*
import view.home.dashboard.*

class HomeView() : View(){

    override val root : BorderPane by fxml()

    private val home:Home by inject()
    private val delivery : Delivery by inject()
    private val history : History by inject()
    private val settings : Settings by inject()
    private val users : Users by inject()
    private val products : Products by inject()


    @FXML
    lateinit var labelNameUser : Label

    @FXML
    lateinit var dashBoard: BorderPane
    //private val dashBoard : BorderPane by fxid() /*TODO: Por alguna razon falla :(, no usar esta linea*/


    private val currentUser : User = User.getInstance()

    init{
        dashBoard.center = home.root
        initializeUserData()
    }

    fun setScene(event:Event){
        val buttonPressed=event.target.toString()
        val buttonName:String= buttonPressed.substring(buttonPressed.indexOf("'")+1,buttonPressed.length-1)

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

    private fun initializeUserData(){
        labelNameUser.text = currentUser.name
    }
}