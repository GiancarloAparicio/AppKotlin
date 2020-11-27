package view

import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.layout.BorderPane
import tornadofx.*
import view.dashboard.Delivery
import view.dashboard.History
import view.dashboard.Home
import view.dashboard.Settings

class HomeView() : View(){

    override val root : BorderPane by fxml()

    private val home:Home by inject()
    private val delivery : Delivery by inject()
    private val history : History by inject()
    private val settings : Settings by inject()

    @FXML
    lateinit var dashBoard: BorderPane
    //private val dashBoard : BorderPane by fxid() /*Por alguna razon falla :(, no usar esta linea*/

    init{
        dashBoard.center = home.root
    }

    fun setScene(event:Event){
        val buttonPressed=event.target.toString()
        val buttonName:String= buttonPressed.substring(buttonPressed.indexOf("'")+1,buttonPressed.length-1)

        when (buttonName) {
            "Home"-> dashBoard.center = home.root
            "Delivery"-> dashBoard.center = delivery.root
            "History"-> dashBoard.center = history.root
            "Settings"-> dashBoard.center = settings.root
            else -> {
                dashBoard.center = home.root
                println("Error: scene not found")
            }
        }
    }
}