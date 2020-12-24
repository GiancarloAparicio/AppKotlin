package app.DTO

import com.jfoenix.controls.JFXButton
import javafx.scene.Cursor
import javafx.scene.layout.HBox
import tornadofx.action
import tornadofx.add


class OrderInLatestOrdersTableDTO (
    val id :Int,
    var user : String,
    var total : Double,
    var actions : HBox = HBox()
)
{

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
            println("Delete order")
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

}