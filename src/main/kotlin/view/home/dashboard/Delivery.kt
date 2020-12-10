package view.home.dashboard

import database.Database
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import models.Order
import models.Product
import models.User
import repositories.ProductRepository
import tornadofx.*
import view.home.components.TableOrderWithoutPay
import java.sql.ResultSet


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
    }

    fun addProductToOrder(){
        val quantityIsCorrect : Boolean = validateQuantity()
        val product : Product? = ProductRepository.getProduct( comboBoxProduct.selectedItem )

        if( product != null  &&  quantityIsCorrect ){
            val unitPrice : Double = product.price
            val quantity : Int = inputQuantity.text.toInt()
            val subTotal : Double = unitPrice * quantity

            var orderProduct : Order = Order( tableOrderWithoutPay.size, product.name, quantity, unitPrice, subTotal,"Nothing")

            tableOrderWithoutPay.addProduct( orderProduct )

        }else{
            println("Warning") //TODO: Add a warning
        }
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

        val storeProcedure = "{CALL createOrder(?,?)}"
        val params = arrayOf( User.getInstance().id , "Cake Delivery" )
        val data: ResultSet? = dataBase.execStoreProcedure( storeProcedure, params )

        if (data != null )
            while ( data.next() ){
                println("Order success")
            }

        //TODO: Elegir una opcion
        // Publicar un evento con una orden generada (Op1)
        // Limpiar todo despues de crear una orden (Op 2 no recomendada)
    }

    private fun initializeComboBox(){
        val products : MutableList<Product> = ProductRepository.getAll()

        for (product in products){
            comboBoxProduct.items.add(product.name)
        }
    }

    private fun initializeTableOrderWithoutPay(){
        contentTable.add( tableOrderWithoutPay.root )
    }



}