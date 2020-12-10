package view.home.components


import javafx.collections.ObservableList
import javafx.scene.paint.Color
import models.Order
import tornadofx.*

class TableOrderWithoutPay : Fragment()  {

    private var listOrderWithoutPay : ObservableList<Order> = mutableListOf(
        Order(null,null,null, null,null, null)
    ).asObservable()

    var size : Int = listOrderWithoutPay.size

    private var listOrderWithoutPayIsEmpty : Boolean = true


    override val root = tableview(listOrderWithoutPay){
        readonlyColumn("ID", Order::id).fixedWidth(80)
        readonlyColumn("Name", Order::product).fixedWidth(150)
        readonlyColumn("Quantity", Order::quantity).fixedWidth(100)
        readonlyColumn("Price", Order::price).fixedWidth(100)
        readonlyColumn("Sub-Total", Order::subTotal).fixedWidth(120)
        readonlyColumn("Actions", Order::actions)

    }


    fun addProduct( order : Order ){

        if(listOrderWithoutPayIsEmpty){

            listOrderWithoutPay.removeAt(0)
            this.listOrderWithoutPayIsEmpty = false
        }

        size++
        listOrderWithoutPay.add(order)

    }

}