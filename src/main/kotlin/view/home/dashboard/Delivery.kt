package view.home.dashboard

import application.database.Database
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import application.DTO.ProductInOrderTable
import application.models.Product
import application.models.User
import application.repositories.ProductRepository
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


    /*
    * Functions GUI
    * */

    fun addProductToOrder(){
        val quantityIsCorrect : Boolean = validateQuantity()
        val product : Product? = ProductRepository.getProduct( comboBoxProduct.selectedItem )

        if( product != null  &&  quantityIsCorrect ){
            val unitPrice : Double = product.price
            val quantity : Int = inputQuantity.text.toInt()
            val subTotal : Double = unitPrice * quantity

            var orderProduct = ProductInOrderTable( tableOrderWithoutPay.length(), product.name, quantity, unitPrice, subTotal,"Nothing")

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

        val orderID :Int = createNewOrderAndReturnID()
        val listProducts : MutableList<ProductInOrderTable> = tableOrderWithoutPay.getList()

        for ( item in listProducts){
            addProductToOrderGenerate( item, orderID)
        }


        //TODO: Elegir una opcion
        // Publicar un evento con una orden generada (Op1)
        // Limpiar todo despues de crear una orden (Op 2 no recomendada)
    }


    /*
    *  Private functions helpers
    * */

    private fun createNewOrderAndReturnID() : Int {
        val storeProcedure = "{CALL createOrder(?,?)}"
        val params : Array<Any?> = arrayOf( User.getInstance().id , "Cake Delivery" )
        val data: ResultSet? = dataBase.execStoreProcedure( storeProcedure, params )

        if ( data != null && data.next() )
            return  data.getInt(1)

        else
            throw Error("Error: Can not create new Order")

    }



    private fun addProductToOrderGenerate(item : ProductInOrderTable, orderID : Int ){

        val product : Product? = ProductRepository.getProduct( item.product )

        if( item.quantity is Int && product is Product ){
            val storeProcedure = "{CALL createOrderDetails(?,?,?)}"
            val params : Array<Any?> = arrayOf( item.quantity , orderID ,product.id  )
            val data: ResultSet? = dataBase.execStoreProcedure( storeProcedure, params )
        }

    }

    private fun initializeTableOrderWithoutPay(){
        contentTable.add( tableOrderWithoutPay.root )
    }

    private fun initializeComboBox(){
        val products : MutableList<Product> = ProductRepository.getAll()

        for (product in products){
            comboBoxProduct.items.add(product.name)
        }
    }

}