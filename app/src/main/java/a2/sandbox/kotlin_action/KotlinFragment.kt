package a2.sandbox.kotlin_action

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class KotlinFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val onCreateView = super.onCreateView(inflater, container, savedInstanceState)
        return onCreateView
    }
}