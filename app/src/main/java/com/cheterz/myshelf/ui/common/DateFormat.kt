package com.cheterz.myshelf.ui.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun formatDate(timeStamp: Long?): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    if (timeStamp == null) return "-"
    return dateFormat.format(Date(timeStamp))
}