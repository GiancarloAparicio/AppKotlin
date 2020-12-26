package view.home.dashboard._home

import app.DAO.OrderDAO
import app.DAO.ProductDAO
import app.DTO.OrderInLatestOrdersTableDTO
import app.models.Order
import app.events.OrderCreateEvent
import app.events.interfaces.IObserver
import app.events.types.EventsTypes
import javafx.scene.control.DatePicker
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.View
import view.home.dashboard._home.components.LastProductAdded
import view.home.dashboard._home.components.TableLatestOrders
import java.time.LocalDate
import javafx.scene.control.DateCell


class Home: View(), IObserver {

    //Components
    private var tableLatestOrders : TableLatestOrders = TableLatestOrders()

    //Events
    private val orderCreateEvent : OrderCreateEvent = OrderCreateEvent.getInstance()

    //Components GUI
    private val contentTableLatestOrders : BorderPane by fxid()
    private val datePickerFilterBy : DatePicker by fxid()
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
        initializeDataPicker()

        orderCreateEvent.addListener(this)
    }

    /**
     * Functions GUI
     */

    fun filterOrdersBy(){
        var dateSelected : String = datePickerFilterBy.value.toString()
        var listOrders = OrderDAO.filterBy( dateSelected )

        tableLatestOrders.clearList()
        tableLatestOrders.addAll(listOrders)
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

    private fun initializeDataPicker(){

        var minDate = LocalDate.of(2000, 1, 1)
        var maxDate = LocalDate.now()
        datePickerFilterBy.setDayCellFactory { d ->
            object : DateCell() {
                override fun updateItem(item: LocalDate, empty: Boolean) {
                    super.updateItem(item, empty)
                    isDisable = item.isAfter(maxDate) || item.isBefore(minDate)
                }
            }
        }

    }

    private fun initializeLatestProductsAdded(){
        val listLatestProductAdded = ProductDAO.getLatestProductsAdded()
        val listComponents = LastProductAdded.convertListToComponents(listLatestProductAdded)

        for ( componentProduct in listComponents ){
            contentLatestProduct.add( componentProduct.root )
        }

    }

    //TODO: Crear eventos para que los labels reaccionen
    private fun initializeLabelsInfo(){
        labelGoalCompletions.text = "1211"
        labelTotalProfit.text = "$ 21,000"
        labelTotalCost.text = "$ 10,000"
        labelTotalRevenue.text = "$ 35,000"
    }

}