package app.models

import java.sql.Date
import java.sql.ResultSet

class Product(data : ResultSet)  {
    val id : Int = data.getInt(1)
    val url : String = data.getString(2)
    var name : String = data.getString(3)
    var price : Double  = data.getDouble(4)
    var category : String = data.getString(5)
    var quantity : Int  = data.getInt(6)
    var expiresAt : Date = data.getDate(7)

}