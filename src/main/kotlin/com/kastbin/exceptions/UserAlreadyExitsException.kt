package com.kastbin.exceptions

class UserAlreadyExitsException : Exception {

    constructor(message: String?) : super(message)

    constructor(message: String?, cause: Throwable?) : super(message, cause)
}