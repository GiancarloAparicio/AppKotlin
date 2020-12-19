package view.home.dashboard

import app.database.Database
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import app.DTO.ProductInOrderTable
import app.models.Order
import app.models.Product
import app.DAO.ProductDAO
import tornadofx.*
import view.home.components.TableOrderWithoutPay


class Delivery : View() {

    override val root : BorderPane by fxml()

    private val comboBoxProduct : ComboBox<String> by fxid()
    private val inputQuantity : TextField by fxid()
    private val contentTable : VBox by fxid()

    private var tableOrderWithoutPay : TableOrderWithoutPay = TableOrderWithoutPay()

    private var dataBase = Database.getInstance()

    init{
        initializeComboBox()
        initializeTableOrderWithoutPay()
        tableOrderWithoutPay.clearList()
    }


    /*
    * Functions GUI
    * */

    fun addProductToOrder(){
        val quantityIsCorrect : Boolean = validateQuantity()
        val product : Product? = ProductDAO.getProduct( comboBoxProduct.selectedItem )

        if( product != null  &&  quantityIsCorrect ){
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

        val order : Order = Order()

        val listProducts = tableOrderWithoutPay.getList()

        for ( item in listProducts){
            order.addProductToOrder( item )
        }

        clearProductTable()

        //TODO: Elegir una opcion
        // Publicar un evento con una orden generada (Op1)
        // Limpiar todo despues de crear una orden (Op 2 no recomendada)
    }


    /*
    *  Private functions helpers
    * */

    private fun clearInputAndComboBox(){

    }

    private fun clearProductTable(){
        tableOrderWithoutPay.clearList()

        println("Clear table")
    }

    private fun initializeTableOrderWithoutPay(){
        contentTable.add( tableOrderWithoutPay.root )
    }

    private fun initializeComboBox(){
        val products : MutableList<Product> = ProductDAO.getAll()

        for (product in products){
            comboBoxProduct.items.add(product.name)
        }
    }

}