package repositories

import database.SqlServer
import models.Product
import java.sql.ResultSet

class ProductRepository {

    companion object {
        private var dataBase = SqlServer()

        fun getAll() : MutableList<Product>  {

            val storeProcedure = "{CALL getAllProducts()}"
            val data: ResultSet? = dataBase.execStoreProcedure( storeProcedure )

            var arrayProducts : MutableList<Product> = ArrayList();

            if (data != null ) {

                while ( data.next() ){
                    arrayProducts.add( Product(data) )
                }
            }

            return arrayProducts
        }
    }
}