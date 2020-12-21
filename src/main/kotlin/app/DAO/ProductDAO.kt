package app.DAO

import app.database.Database
import app.models.Product
import view.home.dashboard._home.components.LastProductAdded
import java.sql.ResultSet

class ProductDAO {

    companion object {


        private var dataBase = Database.getInstance()

        fun getAll() : MutableList<Product>  {
            val storeProcedure = "{CALL getAllProducts()}"
            val data: ResultSet? = dataBase.execStoreProcedure( storeProcedure )
            var arrayProducts : MutableList<Product> = ArrayList();

            if (data != null ){
                while ( data.next() ){
                    arrayProducts.add( Product(data) )
                }

                return arrayProducts
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

        //TODO: Crear el Store Procedure getLatestProductsAdded
        fun getLatestProductsAdded() : MutableList<LastProductAdded> {
            val storeProcedure = "{CALL getLatestProductsAdded()}"
            val data: ResultSet? = dataBase.execStoreProcedure( storeProcedure )

            var listLatestProducts : MutableList<LastProductAdded> = mutableListOf();

            if (data != null ) {
                while (data.next()){
                    listLatestProducts.add( LastProductAdded() )
                }

            }

            return listLatestProducts

        }

    }
}