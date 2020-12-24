package app.DTO

import app.events.ProductRemovedToOrderEvent
import com.jfoenix.controls.JFXButton
import javafx.scene.Cursor
import javafx.scene.layout.HBox
import tornadofx.action
import tornadofx.add


/**
* This class Order is used by the tables, so its structure should not be changed
*/
class ProductInOrderTableDTO(
            val id :Int,
            var product : String,
            var quantity : Int,
            var price : Double,
            var subTotal : Double,
            var actions: HBox = HBox()
            )
{

    private val productRemovedToOrderEvent = ProductRemovedToOrderEvent.getInstance()

    init{
        actions.add( createButtonDelete() )
        actions.add( createButtonUpdate() )
        actions.spacing = 5.0
    }

    /**
     * Functions helpers
     */

    private fun createButtonDelete() : JFXButton {
        val button = JFXButton("Delete")

        button.action {
            productRemovedToOrderEvent.throwEvent( id )
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
          println("Update list")
        }

        button.style = "-fx-background-color: #F6A000;" +
                       "-fx-text-fill: white; " +
                       "-fx-padding: 0.3em; " +
                       "-fx-font-weight: bold;"

        button.cursor = Cursor.HAND

        return button
    }

}