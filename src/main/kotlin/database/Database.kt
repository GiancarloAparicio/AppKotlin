package database

import database.abstract.IDataBase

class Database {

    companion object{

        private  var database : IDataBase? = null

        fun getInstance(): IDataBase {
            if( database == null ){
                 database = SqlServer()
            }

            return database as IDataBase
        }
    }
}