package me.maximpestryakov.fintechmessenger.model

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table

@Table(database = AppDatabase::class)
data class Dialog(

        @PrimaryKey(autoincrement = true)
        var id: Int = 0,

        @Column
        var title: String = "",

        @Column
        var description: String = "",

        @Column
        var date: Long = 0
)
