package com.example.catfact.data.api

import retrofit2.Response

object NetworkManager {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResponse<T> {
        return try {
            val result = apiCall()
            if (result.isSuccessful) {
                NetworkResponse.Success(result.body()!!)
            } else {
                NetworkResponse.Error(
                    "Error: "+result.code(),
                    null,
                    result.code()
                )
            }
        } catch (throwable: Throwable) {
            NetworkResponse.Error("Error", null, 500)
        }
    }

    /* private fun getErrorMessage(errorBody: String?): String {
         val gson = Gson()
         return try {
             val errorResponse = gson.fromJson(errorBody, ErrorBody::class.java)
             errorResponse.message + ""
         } catch (e: Exception) {
             e.printStackTrace()
             e.localizedMessage as String
         }
     }

     private fun showError(error: Throwable): String? {
         val application = ExpressBuddyApplication.getInstance()!!;
         val message: String?
         when (error) {
             is NetworkErrorException -> {
                 message = application.getString(R.string.internet_warning)
             }
             *//* is ParseException -> {
                 message = application.getString(R.string.parsing_error)
             }
             is JsonSyntaxException -> {
                 message = application.getString(R.string.parsing_error)
             }*//*
            is TimeoutException -> {
                message = application.getString(R.string.socket_time_out)
            }

            is SocketTimeoutException -> {
                message = application.getString(R.string.socket_time_out)
            }

            is UnknownHostException -> {
                message = application.getString(R.string.internet_warning)
            }

            is ConnectException -> {
                message = application.getString(R.string.internet_warning)
            }

            is SocketException -> {
                message = application.getString(R.string.internet_warning)
            }

            is java.lang.Exception -> {
                message = error.message
            }

            else -> {
                message = error.message

            }
        }
        return message
    }*/
}