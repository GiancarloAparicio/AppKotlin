package view.home.dashboard._home

import app.DAO.ProductDAO
import app.DTO.OrderInLatestOrdersTableDTO
import app.models.Order
import app.observer.DomainEvent
import app.observer.EventTypes
import app.observer.interfaces.IObserver
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
    private val eventBus : DomainEvent = DomainEvent.getInstance()

    init{
        initializeTableLatestOrders()
        initializeLatestProductsAdded()

        eventBus.addListener(this)
    }

    /**
     * Functions GUI
     */

    override fun event(type: String, order: Any) {
        if( type == EventTypes.ORDER_CREATE && order is Order ){

            var lastOrderDTO = OrderInLatestOrdersTableDTO( order.id, order.email, order._total, "Nothing")
            tableLatestOrders.add( lastOrderDTO )

        }
    }

    /**
     * Functions helpers
     */

    private fun initializeTableLatestOrders(){
        contentTableLatestOrders.center = tableLatestOrders.root
        tableLatestOrders.clearList()
    }

    private fun initializeLatestProductsAdded(){
        val listLatestProductAdded = ProductDAO.getLatestProductsAdded()
        val listComponents = LastProductAdded.convertListToComponents(listLatestProductAdded)

        for ( componentProduct in listComponents ){
            contentLatestProduct.add( componentProduct.root )
        }

    }

}