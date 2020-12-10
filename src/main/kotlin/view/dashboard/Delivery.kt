package view.dashboard

import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import models.Order
import models.Product
import repositories.ProductRepository
import tornadofx.*


class Delivery : View() {

    override val root : BorderPane by fxml()

    private val comboBoxProduct : ComboBox<String> by fxid()
    private val inputQuantity : TextField by fxid()

    private val contentTable : VBox by fxid()

    private var orderWithoutPay = mutableListOf(
        Order(1,"Cake1",5, 150.0,500.0,"Nothing")
    ).asObservable()

    private var tableOrderWithoutPay : TableView<Order> = initializeTableView()


    init{
        initializeComboBox()
        contentTable.add( tableOrderWithoutPay )
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

        if( product != null &&  0 < quantity ){
            val unitPrice : Double = ProductRepository.getAll()[ comboBoxProduct.indexInParent ].price
            val subTotal : Double = unitPrice * quantity

            orderWithoutPay.add(Order( orderWithoutPay.size, product, quantity, unitPrice, subTotal,"Nothing") )

        }else{
            println("Warning") //TODO: Add a warning
        }

    }

    private fun initializeComboBox(){
        val products : MutableList<Product> = ProductRepository.getAll()

        for (product in products){
            comboBoxProduct.items.add(product.name)
        }
    }

    private fun initializeTableView(): TableView<Order> {
        return tableview(orderWithoutPay){
            readonlyColumn("ID",Order::id)
            readonlyColumn("Name", Order::product)
            readonlyColumn("Quantity", Order::quantity)
            readonlyColumn("Price",Order::price)
            readonlyColumn("Sub-Total", Order::subTotal)
            readonlyColumn("Actions",Order::actions)
        }
    }

}