package app.events.interfaces

interface IObserver {

    fun event( typeEvent : String, data: Any )
}