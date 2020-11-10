import tornadofx.*
import view.MainView


class MyApp: App(MainView::class) {

    fun main(args: Array<String>){
        launch<MyApp>(args)
    }
}