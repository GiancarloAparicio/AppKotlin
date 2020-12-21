package app.DAO

import app.database.Database
import app.models.Product
import java.sql.ResultSet

class ProductDAO {

    companion object {


        private var dataBase = Database.getInstance()

        fun getAll() : MutableList<Product>  {
            val storeProcedure = "{CALL getAllProducts()}"
            val data: ResultSet? = dataBase.execStoreProcedure( storeProcedure )
            var listProducts : MutableList<Product> = ArrayList();

            if (data != null ){
                while ( data.next() ){
                    listProducts.add( Product(data) )
                }

                return listProducts
            }else{
                throw Error("Error: Can not execute store procedure")
            }

        }

        fun getProduct( productName : String ): Product? {

            val storeProcedure = "{CALL getProduct(?)}"
            val params : Array<Any?> = arrayOf(productName)
            val data: ResultSet? = dataBase.execStoreProcedure( storeProcedure, params )

            if (data != null && data.next() ) {
                return Product(data)
            }
            return null
            
        }

        fun getLatestProductsAdded() : MutableList<Product> {
            val storeProcedure = "{CALL getLatestProductsAdded()}"
            val data: ResultSet? = dataBase.execStoreProcedure( storeProcedure )

            var listLatestProducts : MutableList<Product> = mutableListOf();

            if (data != null ) {
                while (data.next()){
                    listLatestProducts.add( Product( data ) )
                }
            }

            return listLatestProducts
        }

    }
}