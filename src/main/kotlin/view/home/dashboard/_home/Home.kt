package view.home.dashboard._home

import app.DAO.ProductDAO
import app.DTO.OrderInLatestOrdersTableDTO
import app.models.Order
import app.events.OrderCreateEvent
import app.events.interfaces.IObserver
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.View
import view.home.dashboard._home.components.LastProductAdded
import view.home.dashboard._home.components.TableLatestOrders

class Home: View(), IObserver {

    override val root : BorderPane by fxml()

    private val contentTableLatestOrders : BorderPane by fxid()
    private var tableLatestOrders : TableLatestOrders = TableLatestOrders()

    private val contentLatestProduct : VBox by fxid()
    private val orderCreateEvent : OrderCreateEvent = OrderCreateEvent.getInstance()

    init{
        initializeTableLatestOrders()
        initializeLatestProductsAdded()

        orderCreateEvent.addListener(this)
    }

    /**
     * Functions GUI
     */

    override fun event( order: Any) {
        if( order is Order ){

            var lastOrderDTO = OrderInLatestOrdersTableDTO( order.id, order.email, order._total )
            tableLatestOrders.add( lastOrderDTO )

        }
    }

    /**
     * Functions helpers
     */

    private fun initializeTableLatestOrders(){
        contentTableLatestOrders.center = tableLatestOrders.root
    }

    private fun initializeLatestProductsAdded(){
        val listLatestProductAdded = ProductDAO.getLatestProductsAdded()
        val listComponents = LastProductAdded.convertListToComponents(listLatestProductAdded)

        for ( componentProduct in listComponents ){
            contentLatestProduct.add( componentProduct.root )
        }

    }

}