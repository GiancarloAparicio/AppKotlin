package view.home.components

import application.DTO.ProductInOrderTable
import tornadofx.*

class TableOrderWithoutPay : Fragment()  {

    private var listOrderWithoutPay = mutableListOf(
        ProductInOrderTable(null,
            null,
            null,
            null,
            null,
            null)
    ).asObservable()

    private var size : Int = 1

    private var listOrderWithoutPayIsEmpty : Boolean = true


    override val root = tableview(listOrderWithoutPay){
        readonlyColumn("ID", ProductInOrderTable::id).fixedWidth(80)
        readonlyColumn("Name", ProductInOrderTable::product).fixedWidth(150)
        readonlyColumn("Quantity", ProductInOrderTable::quantity).fixedWidth(100)
        readonlyColumn("Price", ProductInOrderTable::price).fixedWidth(100)
        readonlyColumn("Sub-Total", ProductInOrderTable::subTotal).fixedWidth(120)
        readonlyColumn("Actions", ProductInOrderTable::actions)

    }

    fun addProduct(productInOrderTable : ProductInOrderTable){

        if(listOrderWithoutPayIsEmpty){

            listOrderWithoutPay.removeAt(0)
            this.listOrderWithoutPayIsEmpty = false
        }

        size++
        listOrderWithoutPay.add(productInOrderTable)

    }

    fun length() : Int {
        return listOrderWithoutPay.size
    }

    fun getList() : MutableList<ProductInOrderTable> {
        return listOrderWithoutPay
    }

}