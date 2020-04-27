package com.perisic.luka.inspiringpersons.util

import android.annotation.SuppressLint
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ConstantLocale")
val simpleFormatter = SimpleDateFormat("dd. MMM yyyy.", Locale.getDefault())

fun formatTimeLong(time: Long?, formatter: SimpleDateFormat = simpleFormatter): String {
    return time?.let {
        formatter.format(Date(it))
    } ?: ""
}

fun buildDatePicker(
    selection: Long?,
    callback: (Long?) -> Unit
): MaterialDatePicker<Long> {
    val constraintsBuilder = CalendarConstraints.Builder()
    val pickerDialogBuilder = MaterialDatePicker.Builder.datePicker()
    selection?.let {
        constraintsBuilder.setOpenAt(it)
        pickerDialogBuilder
            .setSelection(it)
            .setCalendarConstraints(constraintsBuilder.build())
    }
    val pickerDialog = pickerDialogBuilder.build()
    pickerDialog.addOnPositiveButtonClickListener {
        callback(pickerDialog.selection)
    }
    return pickerDialog
}