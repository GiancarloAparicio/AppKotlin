import tornadofx.*
import view.MainView


class MyApp: App(MainView::class) {

    /* TODO: Agrega estilos a toda la aplicacion, para aplicarlo a un
             componenete agregarlo al constructor de la clase del componente

            importStylesheet("/styles/table-view.css")
     */
    init{
        importStylesheet("/styles/table-view.css")
    }


    fun main(args: Array<String>){
        launch<MyApp>(args)
    }
}