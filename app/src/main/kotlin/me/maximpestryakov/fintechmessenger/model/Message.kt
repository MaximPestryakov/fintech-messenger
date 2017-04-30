package me.maximpestryakov.fintechmessenger.model

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table

@Table(database = AppDatabase::class)
data class Message(

        @PrimaryKey(autoincrement = true)
        var id: Int = 0,

        @Column
        var userId: Int = 0,

        @Column
        var dialogId: Int = 0,

        @Column
        var body: String = "",

        @Column
        var date: Long = System.currentTimeMillis()
)
