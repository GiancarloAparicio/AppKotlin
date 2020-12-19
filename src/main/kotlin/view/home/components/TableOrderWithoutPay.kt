package view.home.components

import app.DTO.ProductInOrderTable
import javafx.collections.ObservableList
import tornadofx.*

class TableOrderWithoutPay : Fragment()  {


    private var listOrderWithoutPay : ObservableList<ProductInOrderTable> = mutableListOf(
        ProductInOrderTable(null,
            null,
            null,
            null,
            null,
            null)
    ).asObservable()


    override val root = tableview(listOrderWithoutPay){
        readonlyColumn("ID", ProductInOrderTable::id).fixedWidth(80)
        readonlyColumn("Name", ProductInOrderTable::product).fixedWidth(150)
        readonlyColumn("Quantity", ProductInOrderTable::quantity).fixedWidth(100)
        readonlyColumn("Price", ProductInOrderTable::price).fixedWidth(100)
        readonlyColumn("Sub-Total", ProductInOrderTable::subTotal).fixedWidth(120)
        readonlyColumn("Actions", ProductInOrderTable::actions)

    }

    fun addProduct(productInOrderTable : ProductInOrderTable){

        listOrderWithoutPay.add(productInOrderTable)

    }

    fun length() : Int {
        return listOrderWithoutPay.size
    }

    fun getList() : MutableList<ProductInOrderTable> {
        return listOrderWithoutPay
    }

    fun clearList(){
        listOrderWithoutPay.clear()
    }

}