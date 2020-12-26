package app.DAO

import app.DTO.OrderInLatestOrdersTableDTO
import app.database.Database
import app.models.Order
import java.sql.ResultSet

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
    }
}