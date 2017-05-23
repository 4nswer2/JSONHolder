package jp.gr.java_conf.atle42.library.jsonholder

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class JSONHolderTestKt {

	@Test
	@Throws(Exception::class)
	fun testAsRawJson() {
		val holder = JSONHolder.parse(testRawJson)
		assertEquals(answerRawJson, holder["json"].asRawJson())
	}

	@Test
	@Throws(Exception::class)
	fun testAsList() {
		val holder = JSONHolder.parse(testRawJson)
		assertEquals("{\"string\":\"long\",\"numerical\":9223372036854775807}", holder["json"]["json-array"].asList()[0].asRawJson())
		assertEquals("hoge", holder["json"]["string-array"].asList()[0].asString())
		assertEquals(true, holder["json"]["boolean-array"].asList()[0].asBoolean())
		assertEquals(9223372036854775807L, holder["json"]["long-array"].asList()[0].asLong())
		assertEquals(2147483647, holder["json"]["int-array"].asList()[0].asInt().toLong())
		assertEquals(34.6786737, holder["json"]["double-array"].asList()[0].asDouble(), 0.0)
	}

	//--------------------------------------------------
	//  test rawJson
	//--------------------------------------------------
	private val testRawJson =
			"{\n" +
			"  \"json\" : {\n" +
			"    \"string\" : \"test\",\n" +
			"    \"boolean\" : false,\n" +
			"    \"numerical\" : 65535,\n" +
			"    \"json-array\" : [\n" +
			"      { \"string\" : \"long\", \"numerical\" : 9223372036854775807 },\n" +
			"      { \"string\" : \"int\", \"numerical\" : 2147483647 },\n" +
			"      { \"string\" : \"double\", \"numerical\" : 34.6820959 }\n" +
			"    ],\n" +
			"    \"string-array\" : [\n" +
			"      \"hoge\",\n" +
			"      \"fuga\"\n" +
			"    ],\n" +
			"    \"boolean-array\" : [\n" +
			"      true,\n" +
			"      false\n" +
			"    ],\n" +
			"    \"long-array\" : [\n" +
			"      9223372036854775807,\n" +
			"      9223372036854775806\n" +
			"    ],\n" +
			"    \"int-array\" : [\n" +
			"      2147483647,\n" +
			"      2147483646\n" +
			"    ],\n" +
			"    \"double-array\" : [\n" +
			"      34.6786737,\n" +
			"      135.177107\n" +
			"    ]\n" +
			"  }\n" +
			"}"

	private val answerRawJson = "{\"string\":\"test\",\"boolean\":false,\"numerical\":65535,\"json-array\":[{\"string\":\"long\",\"numerical\":9223372036854775807},{\"string\":\"int\",\"numerical\":2147483647},{\"string\":\"double\",\"numerical\":34.6820959}],\"string-array\":[\"hoge\",\"fuga\"],\"boolean-array\":[true,false],\"long-array\":[9223372036854775807,9223372036854775806],\"int-array\":[2147483647,2147483646],\"double-array\":[34.6786737,135.177107]}"
}