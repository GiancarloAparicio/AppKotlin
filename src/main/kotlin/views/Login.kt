package views

import tornadofx.View
import tornadofx.button
import tornadofx.label
import tornadofx.vbox

class Login: View(){
    override val root = vbox {
        button("Login")
        label("Hello Work")
    }
}