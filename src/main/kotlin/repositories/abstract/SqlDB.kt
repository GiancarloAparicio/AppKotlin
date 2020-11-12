package repositories.abstract

import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLNonTransientConnectionException

abstract class SqlDB() {

    abstract val host: String
    abstract val port: Int
    abstract val database: String
    abstract val user: String
    abstract val password: String
    private lateinit var connection:Connection


    /**
     * Gets a connection from the database
     * @return {Connection}
     */
    fun getConnection(): Connection{
        return this.connection
    }

    /**
     * Set a connection from the database
     * @return {Connection}
     */
    fun setConnection(connection: Connection){
        this.connection = connection
    }

    /**
     * Gets a list of the data in a table
     * @return {Connection}
     */
    abstract fun select(query:String): ResultSet

    /**
     * Insert data into the database
     * @param {String} query
     * @return {Boolean}
     */
    abstract fun insert(query:String):Boolean

    /**
     * Update data into the database
     * @param {String} query
     * @return {Boolean}
     */
    abstract fun update(query:String):Boolean

    /**
     * Delete data into the database
     * @param {String} query
     * @return {Boolean}
     */
    abstract fun delete(query:String):Boolean

}