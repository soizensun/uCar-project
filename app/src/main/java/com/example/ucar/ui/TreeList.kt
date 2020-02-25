package com.example.ucar.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ucar.Adapter.TreeAdapter
import com.example.ucar.R
import com.example.ucar.SqliteConfig.SQLiteHelperTableATree
import com.example.ucar.SqliteConfig.SQLiteHelperTablePlot
import kotlinx.android.synthetic.main.activity_tree_list.*
import com.example.ucar.model.ATree as ATree

class TreeList : AppCompatActivity() {
    private lateinit var actionBar : ActionBar
//    private var plotName : String? = null
//    private var plotID : String? = null
    var id : Int = 0

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        plotName = intent.getStringExtra("plotName")
//        plotID = intent.getStringExtra("plotID")
        id = intent.getIntExtra("ID", 0)
        supportActionBar?.title = SQLiteHelperTablePlot(this).searchByID(id).plotName

        loadList()
        swiperefreshlayout.setOnRefreshListener {
            loadList()
        }

        addTreeBTN.setOnClickListener {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_tree_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.plotMenu -> {
                val intent = Intent(this, EditPlot::class.java)
//                intent.putExtra("plotID", plotID)
//                intent.putExtra("plotName", plotName)
                intent.putExtra("id", id)
                startActivity(intent)
            }
            R.id.refreshBTN -> {
                loadList()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadList(){
        swiperefreshlayout.isRefreshing = true
        val plotID = intent.getStringExtra("plotID")
        id = intent.getIntExtra("ID", 0)

        val recycleView = findViewById<RecyclerView>(R.id.treeLog)
        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycleView.adapter = TreeAdapter(SQLiteHelperTableATree(this).searchTree(id), plotID)
        swiperefreshlayout.isRefreshing = false
    }
}
