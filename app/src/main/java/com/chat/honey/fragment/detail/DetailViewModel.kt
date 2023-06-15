package com.chat.honey.fragment.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Create by Baimsg on 2023/6/15
 *
 **/
@HiltViewModel
class DetailViewModel @Inject constructor(stateHandle: SavedStateHandle) : ViewModel() {
    val id = stateHandle.get<Long>("id")
}