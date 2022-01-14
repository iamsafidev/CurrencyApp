package com.iamsafi.digitifyTask.extensions

import android.content.res.Resources
import com.iamsafi.digitifyTask.BuildConfig
import com.iamsafi.digitifyTask.R
import java.util.*

fun Resources.getFlagResource(flagName: String): Int {
    val id = getIdentifier(
        "_${flagName.lowercase(Locale.ROOT)}",
        "drawable",
        BuildConfig.APPLICATION_ID
    )
    if (id == 0) {
        return R.drawable._no_flag
    }
    return id
}