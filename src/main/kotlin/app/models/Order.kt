package app.models

import app.DTO.ProductInOrderTableDTO
import app.database.Database
import app.DAO.ProductDAO
import java.sql.Date
import java.sql.ResultSet

class Order( data : ResultSet, isNewOrder : Boolean = false) {

    private var dataBase = Database.getInstance()

    //Properties
    val id : Int
    val email : String
    val create_at : Date
    var description : String = "Description"

    // Computed property, should not be saved in the database
    var _total : Double = 0.0

    init {
        if( isNewOrder ){
            this.id = data.getInt(1)
            this.email  = data.getString(2)
            this.description  = data.getString(3)
            this.create_at  = data.getDate(4)

        }else{
            this.id = data.getInt(1)
            this.email  = data.getString(2)
            this.create_at  = data.getDate(4)
            this.description  = data.getString(6)

            // Computed property, should not be saved in the database
            this._total  = data.getDouble(3)
        }
    }


    companion object{
        fun createNewInstance(): Order {
            val storeProcedure = "{CALL createOrder(?,?)}"
            val params : Array<Any?> = arrayOf( User.getInstance().email , "Cake Delivery")
            val data: ResultSet = Database.getInstance().execStoreProcedure( storeProcedure, params )

            var order : Order? = null
            if ( data.next()){
                order = Order( data, true )
            }

            return order as Order
        }
    }

    fun addProductToOrder(item : ProductInOrderTableDTO) : Boolean{
        val nameProduct : String? = item.product
        val product : Product? = ProductDAO.getProduct( if( nameProduct is String ) nameProduct else "" )

        if( item.quantity is Int && product is Product ){
            val storeProcedure = "{CALL createOrderDetails(?,?,?)}"
            val params : Array<Any?> = arrayOf( product.id, item.quantity, id  )
            dataBase.execStoreProcedure( storeProcedure, params )

            return true
        }

        return false

    }



}