package view.home.dashboard._delivery

import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import app.DTO.ProductInOrderTable
import app.models.Order
import app.models.Product
import app.DAO.ProductDAO
import app.observer.DomainEvent
import app.observer.EventTypes
import app.observer.interfaces.IObserver
import tornadofx.*
import view.home.dashboard._delivery.components.TableOrderWithoutPay


class Delivery : View(), IObserver {

    override val root : BorderPane by fxml()

    private val comboBoxProduct : ComboBox<String> by fxid()
    private val inputQuantity : TextField by fxid()
    private val contentTableOrderWithoutPay : VBox by fxid()

    private var tableOrderWithoutPay : TableOrderWithoutPay = TableOrderWithoutPay()

    private val eventBus : DomainEvent = DomainEvent.getInstance()

    init{
        initializeComboBox()
        initializeTableOrderWithoutPay()

        eventBus.addListener(this)
    }

    /**
    * Functions GUI
    */

    fun addProductToOrder(){
        val quantityIsCorrect : Boolean = validateQuantity()
        val nameProduct : String? = comboBoxProduct.selectedItem
        val product : Product? = ProductDAO.getProduct( if( nameProduct is String ) nameProduct else "" )

        if( product is Product  &&  quantityIsCorrect ){

            val unitPrice : Double = product.price
            val quantity : Int = inputQuantity.text.toInt()
            val subTotal : Double = unitPrice * quantity

            var orderProduct = ProductInOrderTable( tableOrderWithoutPay.length(), product.name, quantity, unitPrice, subTotal,"Nothing")

            tableOrderWithoutPay.addProduct( orderProduct )

        }else{
            println("Warning") //TODO: Add a warning
        }

        clearInputAndComboBox()
    }

    fun validateQuantity(): Boolean {
        val quantity : String = inputQuantity.text
        val regex : Regex = Regex(pattern = "^[0-9]+$")
        val quantityIsNumber : Boolean = regex.containsMatchIn(quantity) && quantity != "0"

        inputQuantity.style = if(quantityIsNumber ) "-fx-text-inner-color : #2FA14C;" //Green
                                               else "-fx-text-inner-color : #E23A2D;" //Red
        return quantityIsNumber
    }

    fun generateOrder(){

        val order = Order()
        val listProducts = tableOrderWithoutPay.getList()


        for ( item in listProducts){
            order.addProductToOrder( item )
            order._total += item.subTotal!!
        }

        eventBus.throwEvent( EventTypes.ORDER_CREATE,order )

    }

    override fun event(type: String, data: Any) {

        if( type == EventTypes.ORDER_CREATE ){
            clearProductTable()
            clearInputAndComboBox()
        }
    }



    /**
    *  Private functions helpers
    */

    private fun clearInputAndComboBox(){
        //TODO: Agregar funcionalidad para limpiar el comboBox
        inputQuantity.text = ""
    }

    private fun clearProductTable(){
        tableOrderWithoutPay.clearList()
    }

    private fun initializeTableOrderWithoutPay(){
        contentTableOrderWithoutPay.add( tableOrderWithoutPay.root )

        tableOrderWithoutPay.clearList()
    }

    private fun initializeComboBox(){
        val products : MutableList<Product> = ProductDAO.getAll()

        for (product in products){
            comboBoxProduct.items.add(product.name)
        }
    }


}