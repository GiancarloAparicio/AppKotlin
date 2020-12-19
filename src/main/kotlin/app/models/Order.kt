package app.models

import app.DTO.ProductInOrderTable
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

    fun addProductToOrder(item : ProductInOrderTable) : Boolean{
        val product : Product? = ProductDAO.getProduct( item.product )

        if( item.quantity is Int && product is Product ){
            val storeProcedure = "{CALL createOrderDetails(?,?,?)}"
            val params : Array<Any?> = arrayOf( product.id, item.quantity, id  )
            dataBase.execStoreProcedure( storeProcedure, params )

            return true
        }

        return false

    }


}