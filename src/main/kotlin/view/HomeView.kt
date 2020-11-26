package view

import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.layout.BorderPane
import tornadofx.*
import view.dashboard.Home

class HomeView() : View(){

    override val root : BorderPane by fxml()

    private val home:Home by inject()
    private val delivery : BorderPane by fxml("/view/dashboard/Delivery.fxml")
    private val history : BorderPane by fxml("/view/dashboard/History.fxml")
    private val settings : BorderPane by fxml("/view/dashboard/Settings.fxml")


     //private val dashBoard : BorderPane by fxid()
    @FXML
    lateinit var dashBoard: BorderPane
    //private val dashBoard by fxid<BorderPane>()

    fun setScene(event:Event){
        val buttonPressed=event.target.toString()
        val buttonName:String= buttonPressed.substring(buttonPressed.indexOf("'")+1,buttonPressed.length-1)



        when (buttonName) {
            "Home"-> dashBoard.center = home.root
            "Delivery"-> dashBoard.center = delivery
            "History"-> dashBoard.center = history
            "Settings"-> dashBoard.center = settings
            else -> {
                dashBoard.center = home.root
                println("Error: scene not found")
            }
        }


    }
}