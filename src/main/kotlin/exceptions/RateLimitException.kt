package exceptions

import java.io.IOException

class RateLimitException(
    rateLimit: String,
    rateLimitReset: String
) : IOException("Rate limit reached, resets back to $rateLimit in $rateLimitReset seconds")