import tornadofx.*
import view.LoginView


class MyApp: App(LoginView::class) {
    fun main(args: Array<String>){
        launch<MyApp>(args)
    }
}