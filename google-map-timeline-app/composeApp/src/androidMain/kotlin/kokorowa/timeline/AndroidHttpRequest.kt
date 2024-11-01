package kokorowa.timeline

import android.util.Log
import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.CompletableFuture


class AndroidHttpRequest: HttpRequest {

    override fun sendTimeLine() {
        val client = OkHttpClient();

        val formBody = FormBody.Builder()
            .add("message", "Your message")
            .build()
        val request = Request.Builder()
            .url("https://www.example.com/index.php")
            .post(formBody)
            .build()

        try {
            val response = client.newCall(request).execute()

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun validateLogin(login: LoginInfo) : LoginResult {

        val client = OkHttpClient()

        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(LoginResult::class.java)
        val jsonAdapter2 = moshi.adapter(LoginInfo::class.java)

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = jsonAdapter2.toJson(login).toRequestBody(mediaType)
        val request = Request.Builder()
            .url("https://api.github.com/users")
            .post(body)
            .build()

        val result = CompletableFuture<LoginResult>()
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error", "Network Error")
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body!!.string()
                val loginResult = jsonAdapter.fromJson(json)

                result.complete(loginResult)
            }
        })

        return result.get()
    }
}