package com.octal.todosalud.data.network.response

import okhttp3.ResponseBody
import java.io.IOException

class HttpException(
    statusCode: Int,
    rawBody: ResponseBody?
) : IOException("HTTP error: $statusCode body: $rawBody")
