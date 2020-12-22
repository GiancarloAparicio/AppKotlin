package view.home.dashboard._delivery.components

import app.DTO.ProductInOrderTableDTO
import app.events.ProductAddedToOrderEvent
import app.events.interfaces.IObserver
import tornadofx.*

class TableOrderWithoutPay : Fragment(), IObserver {

    private var listOrderWithoutPayDTO = mutableListOf<ProductInOrderTableDTO>().asObservable()
    private var size : Int =0


    override val root = tableview(listOrderWithoutPayDTO){
        readonlyColumn("ID", ProductInOrderTableDTO::id).fixedWidth(80)
        readonlyColumn("Name", ProductInOrderTableDTO::product).fixedWidth(150)
        readonlyColumn("Quantity", ProductInOrderTableDTO::quantity).fixedWidth(100)
        readonlyColumn("Price", ProductInOrderTableDTO::price).fixedWidth(100)
        readonlyColumn("Sub-Total", ProductInOrderTableDTO::subTotal).fixedWidth(120)
        readonlyColumn("Actions", ProductInOrderTableDTO::actions).fixedWidth(150)

    }

    private val productAddedToOrderEvent = ProductAddedToOrderEvent.getInstance()

    init {
        productAddedToOrderEvent.addListener(this)
    }


    fun addProduct(productInOrderTableDTO : ProductInOrderTableDTO){
        listOrderWithoutPayDTO.add( productInOrderTableDTO )
        size++
    }

    fun removeProduct( id : Int ){

        var band : Int? = null
        for ( (index, element) in listOrderWithoutPayDTO.withIndex()){
            if( element.id == id ){
                band = index
                break
            }
        }

        if( band is Int){
            listOrderWithoutPayDTO.removeAt( band )
        }else{
            println("Error: element not exist")
        }

    }

    fun length() : Int {
        return size
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

}