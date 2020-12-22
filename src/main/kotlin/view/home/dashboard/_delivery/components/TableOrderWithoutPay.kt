package view.home.dashboard._delivery.components

import app.DTO.ProductInOrderTableDTO
import tornadofx.*

class TableOrderWithoutPay : Fragment()  {

    private var listOrderWithoutPayDTO = mutableListOf<ProductInOrderTableDTO>().asObservable()


    override val root = tableview(listOrderWithoutPayDTO){
        readonlyColumn("ID", ProductInOrderTableDTO::id).fixedWidth(80)
        readonlyColumn("Name", ProductInOrderTableDTO::product).fixedWidth(150)
        readonlyColumn("Quantity", ProductInOrderTableDTO::quantity).fixedWidth(100)
        readonlyColumn("Price", ProductInOrderTableDTO::price).fixedWidth(100)
        readonlyColumn("Sub-Total", ProductInOrderTableDTO::subTotal).fixedWidth(120)
        readonlyColumn("Actions", ProductInOrderTableDTO::actions).fixedWidth(150)

    }


    fun addProduct(productInOrderTableDTO : ProductInOrderTableDTO){
        listOrderWithoutPayDTO.add( productInOrderTableDTO )
    }

    fun removeProduct( id : String ){
        var auxList  = mutableListOf<ProductInOrderTableDTO>();

        println( root.indexInParent )

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