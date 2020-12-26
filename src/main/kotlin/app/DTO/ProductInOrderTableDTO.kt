package app.DTO

import app.events.ProductRemovedToOrderEvent
import app.events.types.EventsTypes
import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.layout.HBox
import tornadofx.action
import tornadofx.add
import tornadofx.getProperty
import tornadofx.property


/**
* This class Order is used by the tables, so its structure should not be changed
*/
class ProductInOrderTableDTO(
            val id :Int,
            var product : String,
            quantity : Int,
            var price : Double,
            var actions: HBox = HBox()
            )
{

    var quantity: Int by property( quantity )
    fun quantityProperty() = getProperty( ProductInOrderTableDTO::quantity )


    var subTotal : Double =  quantity * price

    private val productRemovedToOrderEvent = ProductRemovedToOrderEvent.getInstance()

    init{
        initializeActions()
    }

    /**
     * Functions helpers
     */

    private fun createButtonDelete() : JFXButton {
        val button = JFXButton("Delete")

        button.action {
            productRemovedToOrderEvent.throwEvent( EventsTypes.PRODUCT_REMOVED_TO_ORDER, id )
        }

        button.style = "-fx-background-color: #EE254F;" +
                       "-fx-text-fill: white;" +
                       "-fx-padding: 0.3em;" +
                       "-fx-font-weight: bold;"

        button.cursor = Cursor.HAND


        return button
    }

    private fun initializeActions(){
        actions.add( createButtonDelete() )
        actions.spacing = 5.0
        actions.alignment = Pos.CENTER
    }

}