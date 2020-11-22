package view

import javafx.event.Event
import javafx.scene.layout.BorderPane
import tornadofx.View

class HomeView() : View(){

    override val root : BorderPane by fxml()

    fun hoverButton(event:Event){
        println(event)
        println(event.target)

    }

}