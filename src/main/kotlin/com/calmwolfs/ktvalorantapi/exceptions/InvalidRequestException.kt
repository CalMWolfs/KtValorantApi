package com.calmwolfs.ktvalorantapi.exceptions

import java.io.IOException

class InvalidRequestException(code: Int, message: String) :
    IOException("Invalid Request, code: $code, message: $message")