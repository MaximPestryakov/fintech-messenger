package me.maximpestryakov.fintechmessenger.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Message(

        @PrimaryKey
        open var id: Int = 0,

        open var userId: Int = 0,

        open var dialogId: Int = 0,

        open var body: String = "",

        open var date: Long = System.currentTimeMillis()
) : RealmObject()
