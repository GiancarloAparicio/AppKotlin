package view.home.dashboard._home

import app.DAO.ProductDAO
import app.DTO.OrderInLatestOrdersTableDTO
import app.models.Order
import app.events.OrderCreateEvent
import app.events.interfaces.IObserver
import app.models.Product
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
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
    private val comboBoxOrderBy : ComboBox<String> by fxid()
    private val orderCreateEvent : OrderCreateEvent = OrderCreateEvent.getInstance()

    /**
     * Labels
     */
    private val labelTotalRevenue : Label by fxid()
    private val labelTotalCost : Label by fxid()
    private val labelTotalProfit : Label by fxid()
    private val labelGoalCompletions : Label by fxid()

    init{
        initializeLatestProductsAdded()
        initializeTableLatestOrders()
        initializeLabelsInfo()
        initializeComboBox()

        orderCreateEvent.addListener(this)
    }

    /**
     * Functions GUI
     */

    override fun event( order: Any) {
        if( order is Order ){

            var lastOrderDTO = OrderInLatestOrdersTableDTO( order.id, order.email, order._total, order.create_at )
            tableLatestOrders.add( lastOrderDTO )

        }
    }

    /**
     * Functions helpers
     */

    private fun initializeTableLatestOrders(){
        contentTableLatestOrders.center = tableLatestOrders.root
    }

    private fun initializeComboBox(){
        val products : MutableList<Product> = ProductDAO.getAll()

        for (product in products){
            comboBoxOrderBy.items.add(product.name)
        }
    }

    private fun initializeLatestProductsAdded(){
        val listLatestProductAdded = ProductDAO.getLatestProductsAdded()
        val listComponents = LastProductAdded.convertListToComponents(listLatestProductAdded)

        for ( componentProduct in listComponents ){
            contentLatestProduct.add( componentProduct.root )
        }

    }

    private fun initializeLabelsInfo(){
        labelGoalCompletions.text = "1211"
        labelTotalProfit.text = "$ 21,000"
        labelTotalCost.text = "$ 10,000"
        labelTotalRevenue.text = "$ 35,000"
    }

}