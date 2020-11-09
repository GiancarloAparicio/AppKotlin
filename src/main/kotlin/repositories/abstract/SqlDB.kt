package repositories.abstract

abstract class SqlDB() {

    abstract val host: String
    abstract val port: Int
    abstract val database: String
    abstract val user: String
    abstract val password: String


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