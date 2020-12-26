package view.home.dashboard._home.components

import app.DAO.OrderDAO
import app.DTO.OrderInLatestOrdersTableDTO
import javafx.scene.control.TableView
import tornadofx.*

class TableLatestOrders : Fragment()  {

    //Properties
    private var listLatestOrderDTOS = mutableListOf<OrderInLatestOrdersTableDTO>().asObservable()

    //Root
    override val root = initializeRootComponent()

    init {
        initializeTable()
    }

    /**
     * Function GUI
     */
    fun add(orderInLatestOrdersTableDTO : OrderInLatestOrdersTableDTO ){
        listLatestOrderDTOS.add( orderInLatestOrdersTableDTO )
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
}