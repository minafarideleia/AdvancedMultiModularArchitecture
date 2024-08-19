package com.minafarid.data.source

interface DataSource {
  companion object {

    const val SUCCESS = 200
    const val SEE_OTHERS = 303
    const val CREATED = 201
    const val BAD_REQUEST = 400
    const val UNAUTHORISED = 401
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val CONFLICT = 409

    const val UNKNOWN = -1
    const val NO_INTERNET = -2
    const val TIMEOUT = -3
    const val SSL_PINNING = -4
  }
}
