package view

import tornadofx.View
import tornadofx.borderpane
import view.login.LoginView

class MainView : View() {

    override val root = borderpane {}
    private val loginView: LoginView by inject()

    init{
        root.center = loginView.root
    }

}