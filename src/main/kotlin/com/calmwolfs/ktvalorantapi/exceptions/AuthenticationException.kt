package com.calmwolfs.ktvalorantapi.exceptions

import java.io.IOException

class AuthenticationException(message: String) : IOException("Authentication Exception: $message")