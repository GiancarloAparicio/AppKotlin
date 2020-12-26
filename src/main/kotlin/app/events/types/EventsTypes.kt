package app.events.types

class EventsTypes {

    companion object{
        val ORDER_CREATE : String = "ORDER_CREATE_EVENT"
        val PRODUCT_ADDED_TO_ORDER : String = "PRODUCT_ADDED_TO_ORDER_EVENT"
        val PRODUCT_REMOVED_TO_ORDER : String = "PRODUCT_REMOVED_TO_ORDER_EVENT"
        val USER_LOGIN : String = "USER_LOGIN_EVENT"
    }
}