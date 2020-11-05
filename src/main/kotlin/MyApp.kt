import tornadofx.*
import views.Login

class MyApp: App(Login::class) {
    fun main(args: Array<String>){
        launch<MyApp>(args)
    }
}