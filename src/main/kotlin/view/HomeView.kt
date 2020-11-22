package view

import javafx.event.Event
import javafx.scene.layout.BorderPane
import tornadofx.*

class HomeView() : View(){

    override val root : BorderPane by fxml()
    private val dashboard : BorderPane by fxid()

    private val home : BorderPane by fxml("/dashboard/Home")

    fun setScene(event:Event){

        val button=event.target.toString()
        val nameButton:String= button.substring(button.indexOf("'")+1,button.length-1)

        dashboard.center=home

        when (nameButton) {
            "Home"-> println("Scene Home")
            "Delivery"-> println("Scene Delivery")
            "History"-> println("Scene History")
            "Settings"-> println("Scene Settings")
            else -> {
                println("Error: scene not found")
            }
        }

    }
}