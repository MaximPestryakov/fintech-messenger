package me.maximpestryakov.fintechmessenger.model

import io.realm.DynamicRealm
import io.realm.RealmMigration
import io.realm.Sort


object Migration : RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) = with(realm) {
        if (oldVersion == 0L) {
            schema.get("Dialog").apply {
                addField("lastMessage", String::class.java)
                transform { dialog ->
                    val dialogId = dialog.getInt("id")
                    val lastMessage = realm.where("Message")
                            .equalTo("dialogId", dialogId)
                            .findAllSorted("date", Sort.DESCENDING)
                            .getOrNull(0)
                            ?.getString("body") ?: ""

                    dialog.set("lastMessage", lastMessage)
                }
            }
            oldVersion.inc()
        }

        if (oldVersion == 1L) {
            schema.get("Dialog").apply {
                addField("lastDate", Long::class.java)
                transform { dialog ->
                    val dialogId = dialog.getInt("id")
                    val lastDate = realm.where("Message")
                            .equalTo("dialogId", dialogId)
                            .findAllSorted("date", Sort.DESCENDING)
                            .getOrNull(0)
                            ?.getLong("date") ?: dialog.getLong("date")

                    dialog.set("lastDate", lastDate)
                }
            }
            oldVersion.inc()
        }
    }
}
