package com.example.ucar.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ucar.R
import com.example.ucar.SqliteConfig.SQLiteHelperTableATree
import com.example.ucar.ui.TreeList
import com.example.ucar.model.Plot

class PlotAdapter(private val plotArrayList: ArrayList<Plot>) : RecyclerView.Adapter<PlotAdapter.PlotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlotViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_a_plot, parent, false)
        return PlotViewHolder(v)
    }

    override fun getItemCount(): Int {
        return plotArrayList.size
    }

    override fun onBindViewHolder(holder: PlotViewHolder, position: Int) {
        val plot : Plot = plotArrayList[position]
        holder.textViewName.text = plot.plotName
        holder.textViewId.text = plot.plotID

        holder.buttonUpload.setOnClickListener {
            Log.i("ee", "fsd")
        }

        holder.itemView.setOnClickListener{
            var context : Context = holder.itemView.context
            val plotID = holder.textViewId.text.toString()
            val plotName = holder.textViewName.text.toString()

            var sqLiteHelperTableATree = SQLiteHelperTableATree(context)

            val intent = Intent(context, TreeList::class.java)
            intent.putExtra("plotID", plotID)
            intent.putExtra("plotName", plotName)
            context.startActivity(intent)
        }
    }

    class PlotViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById(R.id.nameTV) as TextView
        val textViewId = itemView.findViewById(R.id.idTV) as TextView
        val buttonUpload = itemView.findViewById(R.id.uploadBTN) as ImageButton
    }

}