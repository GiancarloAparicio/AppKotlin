package models

import java.sql.Date
import java.sql.ResultSet

class Product(data: ResultSet)  {
    val id : Int = data.getInt(1)
    var name : String = data.getString(2)
    var price : Double  = data.getDouble(3)
    var category : String = data.getString(4)
    var quantity : Int  = data.getInt(5)
    var expiresAt : Date = data.getDate(6)

}