package view.home.dashboard._home.components

import app.DTO.OrderInLatestOrdersTableDTO
import javafx.collections.ObservableList
import tornadofx.*

class TableLatestOrders : Fragment()  {
    private var listLatestOrderDTOS : ObservableList<OrderInLatestOrdersTableDTO> = mutableListOf(
        OrderInLatestOrdersTableDTO(
            null,
            null,
            null,
            null)
    ).asObservable()


    override val root = tableview(listLatestOrderDTOS){
        readonlyColumn("#", OrderInLatestOrdersTableDTO::id).fixedWidth(70)
        readonlyColumn("User", OrderInLatestOrdersTableDTO::user).fixedWidth(130)
        readonlyColumn("Total", OrderInLatestOrdersTableDTO::total).fixedWidth(100)
        readonlyColumn("Actions", OrderInLatestOrdersTableDTO::actions)

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