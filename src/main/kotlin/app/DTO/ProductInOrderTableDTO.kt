package app.DTO

import app.events.ProductAddedToOrderEvent
import javafx.scene.control.Button
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

    private val productAddedToOrderEvent = ProductAddedToOrderEvent.getInstance()

    init{
        actions.add( createButtonDelete() )
        actions.add( createButtonUpdate() )
    }


    private fun createButtonDelete() : Button {
        var button : Button = Button("Delete")

        button.action {
            if (id != null) {
                productAddedToOrderEvent.throwEvent( id )
            }
        }

        return button
    }

    private fun createButtonUpdate() : Button{

        return Button("Update")
    }

}