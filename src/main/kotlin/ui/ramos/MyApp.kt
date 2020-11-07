package ui.ramos

import tornadofx.*
import ui.ramos.views.LoginView


class MyApp: App(LoginView::class) {
    fun main(args: Array<String>){
        launch<MyApp>(args)
    }
}