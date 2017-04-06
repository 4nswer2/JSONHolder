package jp.gr.java_conf.atle42.jsonholdersample

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jp.gr.java_conf.atle42.library.jsonholder.JSONHolder
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {
	private val ui: MainActivityUi = MainActivityUi()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		ui.setContentView(this)

		object: AsyncTask<Void, Void, String>() {
			override fun doInBackground(vararg p0: Void?): String {
				val request = Request.Builder().url("http://ip.jsontest.com/").get().build()
				val client  = OkHttpClient.Builder().build()
				val response = client.newCall(request).execute()

				if (response.isSuccessful) {
					return response.body().string()
				} else {
					return ""
				}
			}

			override fun onPostExecute(result: String?) {
				super.onPostExecute(result)

				val holder = JSONHolder.parse(result ?: "")
				ui.ipText?.text = holder["ip"].asString()
			}
		}.execute()
	}
}
