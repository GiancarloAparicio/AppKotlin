package app.DAO

import app.database.Database
import app.models.Order
import java.sql.ResultSet
import java.time.LocalDate

class OrderDAO {

    companion object{


        private var dataBase = Database.getInstance()

        fun getAll() : MutableList<Order>  {
            val storeProcedure = "{CALL getAllOrders()}"
            val data: ResultSet = dataBase.execStoreProcedure( storeProcedure )
            var listProducts : MutableList<Order> = mutableListOf();

            while ( data.next() ){
                listProducts.add( Order( data ) )
            }

            return listProducts
        }

        fun deleteOrder( id : Int): Order? {
            var storeProcedure = "{CALL softDeleteOrder(?)}"
            var params : Array<Any?> = arrayOf( id )
            var data : ResultSet = dataBase.execStoreProcedure( storeProcedure, params )

            if( data.next() ){
               return Order(data , true)
            }
            return null
        }

        fun filterBy( date : String ): MutableList<Order> {
            var storeProcedure = "{CALL filterOrdersByDate(?)}"
            var params : Array<Any?> = arrayOf( date )
            var data : ResultSet = dataBase.execStoreProcedure( storeProcedure, params )

            var listProducts : MutableList<Order> = mutableListOf();

            while ( data.next() ){
                listProducts.add( Order( data ) )
            }

            return listProducts
        }
    }
}