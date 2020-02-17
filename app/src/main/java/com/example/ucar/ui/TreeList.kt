package com.example.ucar.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ucar.Adapter.TreeAdapter
import com.example.ucar.R
import com.example.ucar.SqliteConfig.SQLiteHelperTableATree
import kotlinx.android.synthetic.main.activity_tree_list.*
import com.example.ucar.model.ATree as ATree

class TreeList : AppCompatActivity() {
    private lateinit var actionBar : ActionBar

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra("plotName")

        loadList()
        swiperefreshlayout.setOnRefreshListener {
            loadList()
        }
    }

    @SuppressLint("WrongConstant")
    fun loadList(){
        swiperefreshlayout.isRefreshing = true
        var dbHelperTableATree = SQLiteHelperTableATree(this)
        val plotID : String = intent.getStringExtra("plotID")

        val recycleView = findViewById(R.id.treeLog) as RecyclerView
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recycleView.adapter = TreeAdapter(dbHelperTableATree.searchTree(plotID), plotID)
        swiperefreshlayout.isRefreshing = false
    }
}
