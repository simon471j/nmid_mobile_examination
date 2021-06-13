package com.example.wanandroid.ui.tree

import androidx.lifecycle.ViewModel
import com.example.wanandroid.logic.Repository

class TreeViewModel : ViewModel() {
    var treeLiveData = Repository.searchTree()
    fun refreshTree() {
        treeLiveData = Repository.searchTree()
    }
}