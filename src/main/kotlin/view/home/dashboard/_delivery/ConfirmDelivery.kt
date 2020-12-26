package view.home.dashboard._delivery

import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import app.events.OrderCreateEvent
import app.events.ProductAddedToOrderEvent
import app.events.interfaces.IObserver
import app.events.types.EventsTypes
import app.models.Order
import tornadofx.*
import view.home.dashboard._delivery.components.TableOrderWithoutPay


class ConfirmDelivery : View(), IObserver {

    //Items to GUI
    private val inputQuantity : TextField by fxid()
    private val contentTableOrderWithoutPay : VBox by fxid()

    //Components
    private var tableOrderWithoutPay : TableOrderWithoutPay = TableOrderWithoutPay()

    //Views
    private val delivery: Delivery by inject()

    //Events
    private val orderCreateEvent  = OrderCreateEvent.getInstance()
    private val productAddedToOrderEvent = ProductAddedToOrderEvent.getInstance()

    //Aux
    var index : Int = 0

    //Root
    override val root : BorderPane by fxml()

    init{
        orderCreateEvent.addListener(this)
        productAddedToOrderEvent.addListener(this)
    }

    /**
    * Functions GUI
    */

    fun updatedProduct(){

        var quantityIsCorrect = validateQuantity()

        if(quantityIsCorrect){
            var quantity = inputQuantity.text.toInt()
            tableOrderWithoutPay.root?.selectionModel?.selectedItem?.quantity = quantity

            restartTableOrderWithoutPay()
            clearInputQuantity()
        }
    }

    fun generateOrder(){
        var productListSize = tableOrderWithoutPay.length()

        if( 0 < productListSize){

            val order = Order()
            val listProducts = tableOrderWithoutPay.getList()

            for ( product in listProducts){
                order.addProductToOrder( product )
                order._total += product.subTotal
            }

            orderCreateEvent.throwEvent( EventsTypes.ORDER_CREATE, order )

        }else{
            println("Order empty")
        }


    }

    fun validateQuantity(): Boolean {
        val quantity : String = inputQuantity.text
        val regex = Regex(pattern = "^[0-9]+$")
        val quantityIsNumber : Boolean = regex.containsMatchIn(quantity) && quantity != "0"

        inputQuantity.style = if(quantityIsNumber ) "-fx-text-inner-color : #2FA14C;" //Green
                                               else "-fx-text-inner-color : #E23A2D;" //Red
        return quantityIsNumber
    }

    override fun event( typeEvent : String, data : Any ) {
        if( typeEvent == EventsTypes.ORDER_CREATE ){
            clearProductTable()
        }

    }


    /**
    *  Private functions helpers
    */


    private fun clearProductTable(){
        tableOrderWithoutPay.clearList()
    }

    private fun clearInputQuantity(){
        inputQuantity.text = ""
    }

    fun initializeTableOrderWithoutPay(){
        contentTableOrderWithoutPay.add( tableOrderWithoutPay.root )
        tableOrderWithoutPay.clearList()

        for ( productDTO in delivery.productListForOrder){
            tableOrderWithoutPay.addProduct( productDTO )
        }

    }

    private fun restartTableOrderWithoutPay(){
        tableOrderWithoutPay.clearList()

        for ( productDTO in delivery.productListForOrder){
            productDTO.subTotal = productDTO.quantity * productDTO.price

            tableOrderWithoutPay.addProduct( productDTO )
        }
    }


}