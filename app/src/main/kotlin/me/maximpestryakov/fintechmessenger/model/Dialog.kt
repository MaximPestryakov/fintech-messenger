package me.maximpestryakov.fintechmessenger.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Dialog(

        @PrimaryKey
        open var id: Int = 0,

        open var title: String = "",

        open var description: String = "",

        open var date: Long = System.currentTimeMillis(),

        open var lastMessage: String = "",

        open var lastDate: Long = System.currentTimeMillis()
) : RealmObject()
