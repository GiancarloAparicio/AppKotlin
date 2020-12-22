package app.database.abstract

import java.sql.Connection
import java.sql.ResultSet


 interface IDataBase {

     val host: String
     val port: Int
     val database: String
     val user: String
     val password: String


    /**
     * Gets a connection to the app.database
     * @return {Connection}
     */
     fun getConnection(): Connection

    /**
     * Set a connection to the app.database
     * @return {Connection}
     */
     fun setConnection(connection: Connection)

    /**
     * Start a connection to the app.database
     * @return {Connection}
     */
     fun connectToDatabase()


     fun execStoreProcedure(storeProcedure: String, params: Array<Any?>) : ResultSet

     fun execStoreProcedure(storeProcedure: String) : ResultSet

}