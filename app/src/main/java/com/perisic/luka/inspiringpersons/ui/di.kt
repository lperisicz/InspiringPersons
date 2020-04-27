package com.perisic.luka.inspiringpersons.ui

import com.perisic.luka.inspiringpersons.ui.action.CreatePersonViewModel
import com.perisic.luka.inspiringpersons.ui.list.PersonListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel {
        PersonListViewModel(get())
    }
    viewModel {
        CreatePersonViewModel(get())
    }
}