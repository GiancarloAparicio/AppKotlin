package app.models

import app.DTO.ProductInOrderTableDTO
import app.database.Database
import app.DAO.ProductDAO
import java.sql.Date
import java.sql.ResultSet

class Order {

    private var dataBase = Database.getInstance()

    val id : Int
    val email : String
    var description : String
    val create_at : Date


    // Computed property, should not be saved in the database
    var _total : Double = 0.0

    init{
        val storeProcedure = "{CALL createOrder(?,?)}"
        val params : Array<Any?> = arrayOf( User.getInstance().email , "Cake Delivery")
        val data: ResultSet? = dataBase.execStoreProcedure( storeProcedure, params )

        if (data != null && data.next()){
            id =  data.getInt(1)
            email = data.getString(2)
            description = data.getString(3)
            create_at = data.getDate(4)
        }
        else
            throw Error("Error: Can not create new Order")

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