package view.home.dashboard._delivery.components

import app.DTO.ProductInOrderTableDTO
import app.events.ProductRemovedToOrderEvent
import app.events.interfaces.IObserver
import tornadofx.*

class TableOrderWithoutPay : Fragment(), IObserver {

    private var listOrderWithoutPayDTO = mutableListOf<ProductInOrderTableDTO>().asObservable()
    private var size : Int =0


    override val root = tableview(listOrderWithoutPayDTO){
        readonlyColumn("#", ProductInOrderTableDTO::id).prefWidth(80)
        readonlyColumn("Name", ProductInOrderTableDTO::product).prefWidth(150)
        readonlyColumn("Quantity", ProductInOrderTableDTO::quantity).prefWidth(100)
        readonlyColumn("Price", ProductInOrderTableDTO::price).prefWidth(100)
        readonlyColumn("Sub-Total", ProductInOrderTableDTO::subTotal).prefWidth(120)
        readonlyColumn("Actions", ProductInOrderTableDTO::actions).prefWidth(150)

    }

    private val productRemovedToOrderEvent = ProductRemovedToOrderEvent.getInstance()

    init {
        productRemovedToOrderEvent.addListener(this)
    }

    /**
     * Functions GUI
     */


    fun addProduct(productInOrderTableDTO : ProductInOrderTableDTO){
        listOrderWithoutPayDTO.add( productInOrderTableDTO )
        size++
    }

    fun removeProduct( id : Int ){

        var index : Int = getItemIndexToDelete( id )
        listOrderWithoutPayDTO.removeAt( index )

    }

    fun index() : Int {
        return size
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

    override fun event(id: Any) {
        if( id is Int){
            removeProduct( id )
        }
    }

    /**
     * Functions helpers
     */

    private fun getItemIndexToDelete( id : Int) : Int {
        var item : Int? = null
        for ( (index, element) in listOrderWithoutPayDTO.withIndex()){
            if( element.id == id ){
                item = index
                break
            }
        }

        return item as Int
    }

}