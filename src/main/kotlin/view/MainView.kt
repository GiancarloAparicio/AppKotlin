package view

import tornadofx.View
import tornadofx.borderpane

class MainView : View() {

    override val root = borderpane {}

    private val loginView:LoginView by inject()
    private val homeView:HomeView by inject()

    init{
        root.center = loginView.root
    }

}