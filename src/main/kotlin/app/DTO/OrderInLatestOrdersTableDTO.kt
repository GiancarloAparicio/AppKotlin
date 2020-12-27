package app.DTO

import app.events.DeleteOrderEvent
import app.events.interfaces.IObserver
import app.events.types.EventsTypes
import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.layout.HBox
import tornadofx.action
import tornadofx.add
import java.util.*


class OrderInLatestOrdersTableDTO (
    val id :Int,
    var user : String,
    var total : Double,
    var created : Date,
    var actions : HBox = HBox()
)
{

    //Events
    var deleteOrderEvent = DeleteOrderEvent.getInstance()

    init{
        initializeActions()
    }



    /**
     * Functions helpers
     */

    private fun createButtonDelete() : JFXButton {
        val button = JFXButton("Delete")

        button.action {
            deleteOrderEvent.throwEvent( EventsTypes.DELETE_ORDER, id)
        }

        button.style = "-fx-background-color: #EE254F;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 0.3em;" +
                "-fx-font-weight: bold;"

        button.cursor = Cursor.HAND


        return button
    }

    private fun createButtonUpdate() : JFXButton {
        val button = JFXButton("Update")

        button.action {
            println("Update order")
        }

        button.style = "-fx-background-color: #F6A000;" +
                "-fx-text-fill: white; " +
                "-fx-padding: 0.3em; " +
                "-fx-font-weight: bold;"

        button.cursor = Cursor.HAND

        return button
    }

    private fun initializeActions(){
        actions.add( createButtonDelete() )
        actions.add( createButtonUpdate() )
        actions.spacing = 5.0
        actions.alignment = Pos.CENTER
    }



}