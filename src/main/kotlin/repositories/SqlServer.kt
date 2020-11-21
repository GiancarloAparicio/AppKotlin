package repositories

import repositories.abstract.DataBase
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.CallableStatement
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet


class SqlServer() : DataBase() {
    override val host:String="localhost"
    override val port:Int=1433
    override val database: String="cake_delivery"
    override val user:String="SA"
    override val password:String="123456789segura"

    private lateinit var connection:Connection

    init{
        this.connectToDatabase()
    }

    override fun setConnection(connection: Connection) {
        this.connection=connection
    }

    override fun getConnection(): Connection {
        return this.connection
    }

    override fun connectToDatabase() {
        try {
            val url="jdbc:sqlserver://${host}:${port};databaseName=${database}"
            DriverManager.registerDriver(SQLServerDriver())
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
            this.setConnection(DriverManager.getConnection(url,user,password))

            println("Connect database: success" )
        }catch(e:Error){
            println("Error database: ${e.message}")
        }
    }

    fun execStoreProcedure(storeProcedure: String, params: Array<String?>) : ResultSet? {
        return try {
            val procedure: CallableStatement = this.getConnection().prepareCall(storeProcedure)
            for ((index,param) in params.withIndex()){
                procedure.setString(index+1,param)
            }
            val response: ResultSet? = procedure.executeQuery()

            response
        }catch (e:Error){
            println("Error storeProcedure: ${e.message}")
            null
        }
    }

}