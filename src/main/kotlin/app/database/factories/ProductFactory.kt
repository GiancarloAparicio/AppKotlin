package app.database.factories

import app.database.factories.abstract.Factory
import app.models.Product
import java.sql.ResultSet

class ProductFactory : Factory() {

    override fun create(quantity : Int ): Any {

        var listProducts = mutableListOf<Product>()

       try {
           for(item in 1..quantity){

               var productName = faker.food().dish()
               var price : Double = faker.commerce().price().toDouble()
               var category : Int= faker.number().numberBetween( 1 ,5 )
               var lot : Int = faker.number().numberBetween( 1 ,4 )


               var storeProcedure = "{CALL createProduct(?,?,?,?)}"
               val params : Array<Any?> = arrayOf( productName, price, category, lot )
               val data: ResultSet = dataBase.execStoreProcedure( storeProcedure, params )

               if ( data.next() ) {
                   listProducts.add( Product(data) )
               }
           }
       }catch (e:Error){
           println("Error: Constraint UNIQUE")
       }

        return listProducts
    }
}