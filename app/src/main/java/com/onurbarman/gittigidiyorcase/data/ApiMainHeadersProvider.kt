package com.onurbarman.gittigidiyorcase.data

class ApiMainHeadersProvider {

    /**
     * Returns both the default headers and the headers that are mandatory for authenticated users.
     */
    fun getAuthenticatedHeaders(accessToken: String): AuthenticatedHeaders =
        AuthenticatedHeaders().apply {
            put(TOKEN, accessToken)
        }

    companion object {
        private const val TOKEN = "Token"
    }
}

open class ApiMainHeaders : HashMap<String, String>()
class AuthenticatedHeaders : ApiMainHeaders()