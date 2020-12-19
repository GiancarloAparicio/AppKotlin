package view.home.dashboard._home

import app.observer.DomainEvent
import app.observer.EventTypes
import app.observer.interfaces.IObserver
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.View

class Home: View(), IObserver {

    override val root : BorderPane by fxml()

    private val contentTableLatestOrders : VBox by fxid()

    private val eventBus : DomainEvent = DomainEvent.getInstance()

    init{
        eventBus.addListener(this)
    }

    /**
     * Functions GUI
     */


    override fun event(type: String, data: Any) {
        if( type == EventTypes.ORDER_CREATE ){
            println("Update interface Home")

        }
    }
}