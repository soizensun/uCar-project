package com.example.ucar.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ucar.R
import com.example.ucar.ui.TreeList
import com.example.ucar.model.Plot

class PlotAdapter(private val plotArrayList: ArrayList<Plot>) : RecyclerView.Adapter<PlotAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_a_plot, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return plotArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plot : Plot = plotArrayList[position]
        holder.textViewName.text = plot.plotName
        holder.textViewId.text = plot.plotID

        holder.itemView.setOnClickListener{
            var context : Context = holder.itemView.context
            val plotName = holder.textViewName.text.toString()
//            var sqLiteHelperTableATree = SQLiteHelperTableATree(context)
//            Log.i("tt", sqLiteHelperTableATree.searchTree(plotName).toString())
            val intent = Intent(context, TreeList::class.java)
            context.startActivity(intent)
//            context.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)


        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById(R.id.nameTV) as TextView
        val textViewId = itemView.findViewById(R.id.idTV) as TextView


    }
}