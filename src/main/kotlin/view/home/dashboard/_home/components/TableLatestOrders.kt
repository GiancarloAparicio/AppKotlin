package view.home.dashboard._home.components

import app.DTO.OrderInLatestOrdersTable
import javafx.collections.ObservableList
import tornadofx.*

class TableLatestOrders : Fragment()  {
    private var listLatestOrders : ObservableList<OrderInLatestOrdersTable> = mutableListOf(
        OrderInLatestOrdersTable(
            null,
            null,
            null,
            null)
    ).asObservable()


    override val root = tableview(listLatestOrders){
        readonlyColumn("#", OrderInLatestOrdersTable::id).fixedWidth(70)
        readonlyColumn("User", OrderInLatestOrdersTable::user).fixedWidth(130)
        readonlyColumn("Total", OrderInLatestOrdersTable::total).fixedWidth(100)
        readonlyColumn("Actions", OrderInLatestOrdersTable::actions)

    }

    fun add( orderInLatestOrdersTable : OrderInLatestOrdersTable ){

        listLatestOrders.add( orderInLatestOrdersTable )

    }

    fun length() : Int {
        return listLatestOrders.size
    }

    fun getList() : MutableList<OrderInLatestOrdersTable> {
        return listLatestOrders
    }

    fun clearList(){
        listLatestOrders.clear()
    }
}