package com.perisic.luka.inspiringpersons.util

import android.annotation.SuppressLint
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
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

inline fun Fragment.startSupportActionMode(
    @MenuRes menuId: Int,
    crossinline onActionItemClicked: (
        mode: ActionMode?,
        item: MenuItem?
    ) -> Boolean = { mode, _ ->
        mode?.finish()
        false
    }
) {
    (requireActivity() as AppCompatActivity).startSupportActionMode(object :
        ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            return onActionItemClicked.invoke(mode, item)
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(menuId, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {

        }
    })
}