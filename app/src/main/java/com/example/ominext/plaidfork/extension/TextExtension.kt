package com.example.ominext.plaidfork.extension

import android.widget.TextView
import java.text.NumberFormat

/**
 * Created by Admin on 14-Jun-17.
 */

fun TextView.setTextWithFormatNumber(stringRes: Int, number: Int?) {
    val numberFormat = NumberFormat.getNumberInstance().format(number ?: 0)
    text = resources.getQuantityString(stringRes, number ?: 0, numberFormat)
}

fun doSomeThing() {

}

interface MyInterface {
    val prop: Int // abstract
    val post: String
        get() = "Any post"

    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        print(prop)
    }
}

class Child : MyInterface {
    override val prop: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override var post: String = ""
        get() = super.post
        set(value) {
            field = "ag"
        }
}