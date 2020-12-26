package view.home.dashboard._home

import app.DAO.ProductDAO
import app.DTO.OrderInLatestOrdersTableDTO
import app.models.Order
import app.events.OrderCreateEvent
import app.events.interfaces.IObserver
import app.events.types.EventsTypes
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.View
import tornadofx.selectedItem
import view.home.dashboard._home.components.LastProductAdded
import view.home.dashboard._home.components.TableLatestOrders
import java.util.*
import java.util.Calendar


class Home: View(), IObserver {

    //Components
    private var tableLatestOrders : TableLatestOrders = TableLatestOrders()

    //Events
    private val orderCreateEvent : OrderCreateEvent = OrderCreateEvent.getInstance()

    //Components GUI
    private val contentTableLatestOrders : BorderPane by fxid()
    private val comboBoxOrderBy : ComboBox<String> by fxid()
    private val labelGoalCompletions : Label by fxid()
    private val contentLatestProduct : VBox by fxid()
    private val labelTotalRevenue : Label by fxid()
    private val labelTotalProfit : Label by fxid()
    private val labelTotalCost : Label by fxid()

    //Root
    override val root : BorderPane by fxml()

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

    fun sortOrdersBy(){
        var category = comboBoxOrderBy.selectedItem.toString()

        println( category )
    }

    override fun event( typeEvent : String, order : Any) {
        if( typeEvent == EventsTypes.ORDER_CREATE && order is Order ){

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

        val cal = Calendar.getInstance()
        cal.time = Date()

        val yearCurrent : Int = cal[Calendar.YEAR]

        for ( item in 0..10 ){
            var year = yearCurrent - item
            comboBoxOrderBy.items.add( year.toString() )
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