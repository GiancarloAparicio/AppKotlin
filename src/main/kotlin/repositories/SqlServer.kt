package repositories

import repositories.abstract.SqlDB
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection
import java.sql.DriverManager

class SqlServer() : SqlDB() {
    override val host:String="localhost"
    override val port:Int=1433
    override val database: String="master" //CakeDelivery
    override val user:String="SA"
    override val password:String="123456789segura"

    init{
        val url="jdbc:sqlserver://${host}:${port};databaseName=${database}"
        val connection: Connection
        try {
            DriverManager.registerDriver(SQLServerDriver())
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
            connection = DriverManager.getConnection(url,user,password)
            println("Success")

        }catch(e:Error){
            println("Error: ${e.message}")
        }

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