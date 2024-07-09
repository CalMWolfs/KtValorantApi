package com.calmwolfs.ktvalorantapi.exceptions

import java.io.IOException

class ResourceNotFoundException(code: Int, message: String) :
    IOException("Resource not found, code: $code, message: $message")