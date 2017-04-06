package jp.gr.java_conf.atle42.jsonholdersample

import android.view.Gravity
import android.widget.TextView
import org.jetbrains.anko.*

class MainActivityUi: AnkoComponent<MainActivity> {
	var ipText: TextView? = null
		private set

	override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
		frameLayout {
			lparams {
				width  = matchParent
				height = matchParent
			}
			ipText = textView {
				gravity = Gravity.CENTER
			}
		}.applyRecursively { view -> when(view) {
			is TextView -> view.textSize = 24f
		}}
	}
}