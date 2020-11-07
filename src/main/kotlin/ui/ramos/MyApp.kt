import tornadofx.*
import ui.ramos.views.Login


class MyApp: App(Login::class) {
    fun main(args: Array<String>){
        launch<MyApp>(args)
    }
}