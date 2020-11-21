package repositories

import repositories.abstract.SqlDB
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class SqlServer() : SqlDB() {
    override val host:String="localhost"
    override val port:Int=1433
    override val database: String="cake_delivery" //CakeDelivery
    override val user:String="SA"
    override val password:String="123456789segura"


    init{
        try {
            val url="jdbc:sqlserver://${host}:${port};databaseName=${database}"
            DriverManager.registerDriver(SQLServerDriver())
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")

            this.setConnection(DriverManager.getConnection(url,user,password))
        }catch(e:Error){
            println("Error: ${e.message}")
        }

    }

    override fun select(query: String): ResultSet {
        val consult  : Statement =this.getConnection().createStatement()
        return  consult.executeQuery(query)
    }

    override fun insert(query: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(query: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(query: String): Boolean {
        TODO("Not yet implemented")
    }
}