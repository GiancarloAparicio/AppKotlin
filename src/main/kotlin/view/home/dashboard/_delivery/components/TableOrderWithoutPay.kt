package view.home.dashboard._delivery.components

import app.DTO.ProductInOrderTableDTO
import app.events.OrderCreateEvent
import app.events.ProductAddedToOrderEvent
import app.events.ProductRemovedToOrderEvent
import app.events.interfaces.IObserver
import app.events.types.EventsTypes
import javafx.scene.control.TableView
import tornadofx.*

class TableOrderWithoutPay : Fragment(), IObserver {

    var listOrderWithoutPayDTO = mutableListOf<ProductInOrderTableDTO>().asObservable()

    // Events
    private val productRemovedToOrderEvent = ProductRemovedToOrderEvent.getInstance()
    private val productAddedToOrderEvent = ProductAddedToOrderEvent.getInstance()
    private val orderCreateEvent = OrderCreateEvent.getInstance()

    // Root
    override val root = initializeTable()

    init {
        productRemovedToOrderEvent.addListener(this)
        productAddedToOrderEvent.addListener(this)
        orderCreateEvent.addListener(this)
    }


    /**
     * Functions GUI
     */

    fun addProduct(productInOrderTableDTO : ProductInOrderTableDTO){
        listOrderWithoutPayDTO.add( productInOrderTableDTO )
    }

    fun removeProduct( id : Int ){

        var index : Int = getItemIndexToDelete( id )
        listOrderWithoutPayDTO.removeAt( index )

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

    override fun event(typeEvent: String, data : Any) {
        if( typeEvent == EventsTypes.PRODUCT_REMOVED_TO_ORDER && data is Int){
            removeProduct( data )
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

    private fun initializeTable(): TableView<ProductInOrderTableDTO> {
        return tableview(listOrderWithoutPayDTO){

            readonlyColumn("#", ProductInOrderTableDTO::id).prefWidth(80)
            readonlyColumn("Name", ProductInOrderTableDTO::product).prefWidth(150)
            readonlyColumn("Quantity", ProductInOrderTableDTO::quantity).prefWidth(100)
            readonlyColumn("Price", ProductInOrderTableDTO::price).prefWidth(100)
            readonlyColumn("Sub-Total", ProductInOrderTableDTO::subTotal).prefWidth(120)
            readonlyColumn("Actions", ProductInOrderTableDTO::actions).prefWidth(120)

        }
    }

}