package view.home.dashboard._home.components

import app.DTO.OrderInLatestOrdersTableDTO
import tornadofx.*

class TableLatestOrders : Fragment()  {
    private var listLatestOrderDTOS = mutableListOf<OrderInLatestOrdersTableDTO>().asObservable()


    override val root = tableview(listLatestOrderDTOS){
        readonlyColumn("ID", OrderInLatestOrdersTableDTO::id).prefWidth(70)
        readonlyColumn("User", OrderInLatestOrdersTableDTO::user).prefWidth(130)
        readonlyColumn("Total", OrderInLatestOrdersTableDTO::total).prefWidth(100)
        readonlyColumn("Created", OrderInLatestOrdersTableDTO::created).prefWidth(100)
        readonlyColumn("Actions", OrderInLatestOrdersTableDTO::actions).prefWidth(170)

    }

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
}