package com.example.ucar.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ucar.R
import com.example.ucar.SqliteConfig.SQLiteHelperTablePlot
import com.example.ucar.ui.TreeList
import com.example.ucar.model.Plot

class PlotAdapter(private val plotArrayList: ArrayList<Plot>) : RecyclerView.Adapter<PlotAdapter.PlotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlotViewHolder {
//        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_a_plot, parent, false)
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_a_plot2, parent, false)
        return PlotViewHolder(v)
    }

    override fun getItemCount(): Int {
        return plotArrayList.size
    }

    override fun onBindViewHolder(holder: PlotViewHolder , position: Int) {
        val plot : Plot = plotArrayList[position]
        var context : Context = holder.itemView.context
        holder.textViewName.text = plot.plotName
        holder.textViewId.text = plot.plotID
        holder.menuBTN.setOnClickListener {
            val popupMenu = PopupMenu(context, holder.menuBTN)
            popupMenu.inflate(R.menu.plot_menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId){
                    R.id.deleteBTN -> {
                        AlertDialog.Builder(context)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("คุณจะลบแปลงนี้ใช่หรือไม่")
                            .setMessage("เมื่อทำการลบจะไม่สามารถกู้คืนได้")
                            .setPositiveButton("ลบ", DialogInterface.OnClickListener { dialog, i ->
                                SQLiteHelperTablePlot(context).deleteRecord(plot.id)
                                plotArrayList.removeAt(position)
                                notifyItemRemoved(position)
                                notifyItemRangeChanged(position, plotArrayList.size)
                            })
                            .setNegativeButton("ยกเลิก", DialogInterface.OnClickListener { dialogInterface, i ->

                            })
                            .show()
                    }
                    R.id.uploadBTN -> {Log.i("rr", "update")}
                }
                true
            }
            popupMenu.show()
            true
        }

        holder.itemView.setOnClickListener{
            val id = plot.id
            val plotID = holder.textViewId.text.toString()
            val plotName = holder.textViewName.text.toString()

            val intent = Intent(context, TreeList::class.java)
            intent.putExtra("plotID", plotID)
            intent.putExtra("ID", id)
//            intent.putExtra("plotName", plotName)
            context.startActivity(intent)
        }
    }

    class PlotViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById(R.id.nameTV) as TextView
        val textViewId = itemView.findViewById(R.id.idTV) as TextView
//        val buttonUpload = itemView.findViewById(R.id.uploadBTN) as ImageButton
        val menuBTN = itemView.findViewById(R.id.plotOption) as TextView
    }

}