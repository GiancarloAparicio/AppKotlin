package view

//  FXML
import javafx.scene.layout.BorderPane
import tornadofx.View

class LoginView: View(){
    override val root : BorderPane by fxml()
}


/*
import tornadofx.View

import tornadofx.button
import tornadofx.label
import tornadofx.vbox

class LoginView: View(){
    override val root = vbox {
        button("Login")
        label("Hello Work")
    }
}
*/