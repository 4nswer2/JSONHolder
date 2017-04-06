package jp.gr.java_conf.atle42.library.jsonholder

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by murata_to on 2017/04/05.
 */
class JSONHolder private constructor(private val current: Any?) {

	//--------------------------------------------------
	//  create
	//--------------------------------------------------
	companion object Creator {
		@JvmStatic fun create(json: JSONObject): JSONHolder {
			return JSONHolder(json)
		}

		@JvmStatic fun create(json: JSONArray): JSONHolder {
			return JSONHolder(json)
		}

		@JvmStatic fun parse(rawJson: String): JSONHolder {
			var any: Any?
			try {
				any = JSONObject(rawJson)
			} catch (e: JSONException) {
				any = JSONArray(rawJson)
			}
			return JSONHolder(any)
		}
	}

	//--------------------------------------------------
	//  public
	//--------------------------------------------------
	operator fun get(vararg path: Any): JSONHolder {
		return path.fold(this) { holder, node ->
			when (node) {
				is String -> holder[node]
				is Int    -> holder[node]
				else      -> holder
			}
		}
	}

	operator fun get(key: String): JSONHolder {
		return JSONHolder((current as? JSONObject)?.opt(key))
	}

	operator fun get(index: Int): JSONHolder {
		return JSONHolder((current as? JSONArray)?.opt(index))
	}

	fun has(vararg path: Any): Boolean {
		return get(path).current != null
	}

	@JvmOverloads fun asList   (v: List<JSONHolder> = listOf()): List<JSONHolder> { return (current as? JSONArray)?.toList() ?: v }
	@JvmOverloads fun asRawJson(v: String = "")                : String           { return (current as? JSONObject)?.toString() ?: v }
	@JvmOverloads fun asString (v: String = "")                : String           { return  current as? String ?: v }
	@JvmOverloads fun asBoolean(v: Boolean = false)            : Boolean          { return  current as? Boolean ?: v }
	@JvmOverloads fun asLong   (v: Long = 0)                   : Long             { return (current as? Number)?.toLong() ?: v }
	@JvmOverloads fun asInt    (v: Int = 0)                    : Int              { return (current as? Number)?.toInt() ?: v }
	@JvmOverloads fun asDouble (v: Double = 0.toDouble())      : Double           { return (current as? Number)?.toDouble() ?: v }

	//--------------------------------------------------
	//  private
	//--------------------------------------------------
	private fun JSONArray.toList(): List<JSONHolder> {
		return (0..(length() - 1)).fold(listOf()) { list, index ->
			list + listOf(JSONHolder(get(index)))
		}
	}
}