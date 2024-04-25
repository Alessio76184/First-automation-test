package models

data class ErrorMessage(
    val type : String,
    val developerMessage : String,
    val errorCode : Int,
    val error : String,
    val status : Int
)
