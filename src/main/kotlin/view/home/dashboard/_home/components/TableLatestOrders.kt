package view.home.dashboard._home.components

import app.DAO.OrderDAO
import app.DTO.OrderInLatestOrdersTableDTO
import app.events.DeleteOrderEvent
import app.events.interfaces.IObserver
import app.events.types.EventsTypes
import app.models.Order
import javafx.scene.control.TableView
import tornadofx.*

class TableLatestOrders : Fragment(), IObserver  {

    //Properties
    private var listLatestOrderDTOS = mutableListOf<OrderInLatestOrdersTableDTO>().asObservable()

    //Events
    private val deleteOrderEvent = DeleteOrderEvent.getInstance()

    //Root
    override val root = initializeRootComponent()

    init {
        deleteOrderEvent.addListener(this)
        initializeTable()
    }

    /**
     * Function GUI
     */
    fun add(orderInLatestOrdersTableDTO : OrderInLatestOrdersTableDTO ){
        listLatestOrderDTOS.add( 0, orderInLatestOrdersTableDTO )
    }

    fun addAll(ordersList : List<Order> ){

        for(order in ordersList){
            var orderDTO = OrderInLatestOrdersTableDTO( order.id, order.email, order._total, order.create_at )
            listLatestOrderDTOS.add( orderDTO )
        }
    }

    fun length() : Int {
        return listLatestOrderDTOS.size
    }

    fun getList() : MutableList<OrderInLatestOrdersTableDTO> {
        return listLatestOrderDTOS
    }

    fun clearList(){
        listLatestOrderDTOS.clear()
    }

    override fun event(typeEvent: String, data: Any) {

        if( typeEvent == EventsTypes.DELETE_ORDER && data is Int ){
            var index = getItemIndexToDelete( data )
            listLatestOrderDTOS.removeAt( index )

            softDeleteID( data )
        }
    }

    /**
     * Functions helpers
     */

    private fun initializeTable(){
        var ordersList = OrderDAO.getAll()

        for ( order in ordersList ) {
            var lastOrderDTO = OrderInLatestOrdersTableDTO( order.id, order.email, order._total, order.create_at )
            add( lastOrderDTO )
        }

    }

    private fun initializeRootComponent(): TableView<OrderInLatestOrdersTableDTO> {
        return tableview(listLatestOrderDTOS){
            readonlyColumn("ID", OrderInLatestOrdersTableDTO::id).prefWidth(70)
            readonlyColumn("User", OrderInLatestOrdersTableDTO::user).prefWidth(130)
            readonlyColumn("Total", OrderInLatestOrdersTableDTO::total).prefWidth(100)
            readonlyColumn("Created", OrderInLatestOrdersTableDTO::created).prefWidth(100)
            readonlyColumn("Actions", OrderInLatestOrdersTableDTO::actions).prefWidth(170)

        }
    }

    private fun getItemIndexToDelete( id : Int) : Int {
        var item : Int? = null
        for ( (index, element) in listLatestOrderDTOS.withIndex()){
            if( element.id == id ){
                item = index
                break
            }
        }

        return item as Int
    }

    private fun softDeleteID(  id : Int): Order {
        return OrderDAO.deleteOrder( id ) as Order
    }


}