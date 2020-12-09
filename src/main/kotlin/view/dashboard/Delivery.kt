package view.dashboard

import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import models.Order
import models.Product
import repositories.ProductRepository
import tornadofx.View
import tornadofx.indexInParent
import tornadofx.selectedItem

class Delivery : View() {

    override val root : BorderPane by fxml()

    private val comboBoxProduct : ComboBox<String> by fxid()
    private val inputQuantity : TextField by fxid()

    private val tableViewProductsToOrder : TableView<Order> by fxid()
    private val columnID : TableColumn<Order,Int> by fxid()
    private val columnProduct : TableColumn<Order,String> by fxid()
    private val columnQuantity : TableColumn<Order,Int> by fxid()
    private val columnPrice : TableColumn<Order,Double> by fxid()
    private val columnSubTotal : TableColumn<Order,Double> by fxid()
    private val columnActions : TableColumn<Order,String> by fxid()

    private val products : MutableList<Product> =ProductRepository.getAll()


    init{
        initializeComboBox()

    }

    fun setQuantity(){
        val quantity : String = inputQuantity.text
        val regex : Regex = Regex(pattern = "^[0-9]+$")
        val quantityIsNumber : Boolean = regex.containsMatchIn(quantity)

        inputQuantity.style = if(quantityIsNumber) "-fx-text-inner-color : #2FA14C;" //Green
                                              else "-fx-text-inner-color : #E23A2D;" //Red
    }

    fun addProductToOrder(){

        val product : String? = comboBoxProduct.selectedItem
        val quantity : Int =  if( inputQuantity.text == "" ) 0 else inputQuantity.text.toInt()
        val price : Double = products[comboBoxProduct.indexInParent].price
        val subTotal : Double = price * quantity

        if( product != null &&  0 < quantity ){

            println("Quantity: $quantity")
            println("Product: $product")
            println("Price: $price")
            println("SubTotal: $subTotal")
        }

    }

    private fun initializeComboBox(){
        for (product in products){
            comboBoxProduct.items.add(product.name)
        }
    }

    fun initializeTableView(){
        tableViewProductsToOrder.items.addAll()
    }
}