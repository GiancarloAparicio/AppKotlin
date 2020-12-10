package repositories

import database.Database
import database.SqlServer
import models.Product
import java.sql.ResultSet

class ProductRepository {

    companion object {


        private var dataBase = Database.getInstance()

        fun getAll() : MutableList<Product>  {
            val storeProcedure = "{CALL getAllProducts()}"
            val data: ResultSet? = dataBase.execStoreProcedure( storeProcedure )
            var arrayProducts : MutableList<Product> = ArrayList();

            if (data != null )
                while ( data.next() ){
                    arrayProducts.add( Product(data) )
                }

            return arrayProducts
        }

        fun getProduct( product : String? ): Product? {
            if(product != null){
                val storeProcedure = "{CALL getProduct(?)}"
                val data: ResultSet? = dataBase.execStoreProcedure( storeProcedure, arrayOf(product) )

                if (data != null && data.next() ) {
                    return Product(data)
                }else{
                    return null
                }
            }else{
                return null
            }

        }


    }
}