package me.maximpestryakov.fintechmessenger.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Dialog(

        @PrimaryKey
        open var id: Int = 0,

        open var title: String = "",

        open var description: String = "",

        open var date: Long = 0,

        open var lastMessage: String = ""
) : RealmObject()
