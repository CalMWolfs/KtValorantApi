package exceptions

import java.io.IOException

class InternalServerException(code: Int, message: String) : IOException("Internal Server Error: code: $code, message: $message")