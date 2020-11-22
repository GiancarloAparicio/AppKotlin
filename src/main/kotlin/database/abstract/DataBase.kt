package database.abstract

import java.sql.Connection


abstract class DataBase() {

    abstract val host: String
    abstract val port: Int
    abstract val database: String
    abstract val user: String
    abstract val password: String


    /**
     * Gets a connection to the database
     * @return {Connection}
     */
    abstract fun getConnection(): Connection

    /**
     * Set a connection to the database
     * @return {Connection}
     */
    abstract fun setConnection(connection: Connection)

    /**
     * Start a connection to the database
     * @return {Connection}
     */
    abstract fun connectToDatabase()

}