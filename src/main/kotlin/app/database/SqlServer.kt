package app.database

import app.database.abstract.IDataBase
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.*
import java.time.LocalDate


class SqlServer : IDataBase {
    override val host : String = "localhost"
    override val port : Int = 9999
    override val database : String = "cake_delivery"
    override val user : String = "SA"
    override val password : String = "123456789segura"

    private lateinit var connection : Connection

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
            this.setConnection( DriverManager.getConnection( url, user, password ) )

            println("Connect app.database: success" )
        }catch(e:Error){
            println("Error app.database: ${e.message}")
        }
    }

    override fun execStoreProcedure(storeProcedure: String, params: Array<Any?>) : ResultSet {
        try {
            val procedure: CallableStatement = this.getConnection().prepareCall( storeProcedure )

            for ( (index,param) in params.withIndex() ){
                when( param ){
                    is String -> procedure.setString(index+1 , param )
                    is Int    -> procedure.setInt(index+1 , param )
                    is Double -> procedure.setDouble(index+1 , param )
                    is Date   -> procedure.setDate(index+1 , param )
                    is Boolean -> procedure.setBoolean(index+1 , param )
                }
            }
                return procedure.executeQuery()
            }catch (e:Error){
                throw Error("Error: UNIQUE ")
            }
    }

    override fun execStoreProcedure(storeProcedure: String): ResultSet {
            val procedure: CallableStatement = this.getConnection().prepareCall(storeProcedure)
            return procedure.executeQuery()
    }

}