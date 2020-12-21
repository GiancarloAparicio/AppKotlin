package view.home.dashboard._home

import app.DAO.ProductDAO
import app.DTO.OrderInLatestOrdersTable
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


    override fun event(type: String, data: Any) {
        if( type == EventTypes.ORDER_CREATE && data is Order ){

            var lastOrder = OrderInLatestOrdersTable(data.id, data.email, data._total, "Nothing")
            tableLatestOrders.add( lastOrder )

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

        for ( product in listLatestProductAdded ){
            contentLatestProduct.add( product.root )
        }

    }

}