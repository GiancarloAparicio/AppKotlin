package app.database.factories.abstract

import app.database.Database
import com.github.javafaker.Faker

abstract class Factory {
    val faker = Faker()
    val dataBase = Database.getInstance()

    abstract fun create( quantity : Int ) : Any?
}