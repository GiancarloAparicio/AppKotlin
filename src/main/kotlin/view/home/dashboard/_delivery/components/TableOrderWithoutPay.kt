package view.home.dashboard._delivery.components

import app.DTO.ProductInOrderTableDTO
import javafx.collections.ObservableList
import tornadofx.*

class TableOrderWithoutPay : Fragment()  {


    private var listOrderWithoutPayDTO : ObservableList<ProductInOrderTableDTO> = mutableListOf(
        ProductInOrderTableDTO(null,
            null,
            null,
            null,
            null,
            null)
    ).asObservable()


    override val root = tableview(listOrderWithoutPayDTO){
        readonlyColumn("ID", ProductInOrderTableDTO::id).fixedWidth(80)
        readonlyColumn("Name", ProductInOrderTableDTO::product).fixedWidth(150)
        readonlyColumn("Quantity", ProductInOrderTableDTO::quantity).fixedWidth(100)
        readonlyColumn("Price", ProductInOrderTableDTO::price).fixedWidth(100)
        readonlyColumn("Sub-Total", ProductInOrderTableDTO::subTotal).fixedWidth(120)
        readonlyColumn("Actions", ProductInOrderTableDTO::actions)

    }

    fun addProduct(productInOrderTableDTO : ProductInOrderTableDTO){

        listOrderWithoutPayDTO.add(productInOrderTableDTO)

    }

    fun length() : Int {
        return listOrderWithoutPayDTO.size
    }

    fun getList() : MutableList<ProductInOrderTableDTO> {
        return listOrderWithoutPayDTO
    }

    fun clearList(){
        listOrderWithoutPayDTO.clear()
    }

}