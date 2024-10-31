package kokorowa.timeline

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class AndroidHttpRequest: HttpRequest {
    override fun call() {
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
}