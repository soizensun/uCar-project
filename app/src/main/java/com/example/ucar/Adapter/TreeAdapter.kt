package com.example.ucar.Adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.ucar.ui.EditTree
import com.example.ucar.R
import com.example.ucar.SqliteConfig.SQLiteHelperTableATree
import com.example.ucar.model.ATree

class TreeAdapter (private val treeArraylist : ArrayList<ATree>, private val plotID : String) : RecyclerView.Adapter<TreeAdapter.TreeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_a_tree, parent, false)
        return TreeViewHolder(v)
    }

    override fun getItemCount(): Int {
        return treeArraylist.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TreeViewHolder, position: Int) {
        var context : Context = holder.itemView.context
        val sqLiteHelperTableATree = SQLiteHelperTableATree(context)
        val aTree : ATree = treeArraylist[position]

        holder.orderTreeTV.text = aTree.orderTree.toString()
        holder.dbhTV.text = aTree.dbh.toString()
        holder.grithTV.text = aTree.grith.toString()
        holder.totalLengthTV.text = aTree.totalLenght.toString()
        holder.merchLengthTV.text = aTree.merchLenght.toString()
        holder.sawdustWeightTV.text = aTree.sawdustWeight.toString()
        holder.hbhN.text = aTree.hbhNorth.toString()
        holder.hbhS.text = aTree.hbhsouth.toString()
        holder.hbhE.text = aTree.hbhEast.toString()
        holder.hbhW.text = aTree.hbhWest.toString()
        if(aTree.deleteAt != null){
            holder.cardHeader.setBackgroundColor(0xFFE74C3C.toInt())
            holder.deleteBTN.setBackgroundColor(0xFFE74C3C.toInt())
            holder.textOrder.setTextColor(0xFFFFFFFF.toInt())
            holder.orderTreeTV.setTextColor(0xFFFFFFFF.toInt())
            holder.deleteBTN.isVisible = false
            holder.cardBody.setBackgroundColor(0xFFEC7063.toInt())
        }

        var orderName = holder.orderTreeTV.text as String
        var dbh = holder.dbhTV.text as String
        var girth = holder.grithTV.text as String
        var totalLength = holder.totalLengthTV.text as String
        var merchLength = holder.merchLengthTV.text as String
        var sawdustWeight = holder.sawdustWeightTV.text as String
        var hbhN = holder.hbhN.text as String
        var hbhS = holder.hbhS.text as String
        var hbhE = holder.hbhE.text as String
        var hbhW = holder.hbhW.text as String

        holder.deleteBTN.setOnClickListener {
            AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("คุณจะลบต้นนี้ใช่หรือไม่")
                .setMessage("เมื่อทำการลบจะไม่สามารถกู้คืนได้")
                .setPositiveButton("ลบ", DialogInterface.OnClickListener { dialog, i ->
                    var check = sqLiteHelperTableATree.updateData(
                        plotID, orderName, dbh, girth, totalLength, merchLength, sawdustWeight, hbhN, hbhS, hbhE, hbhW, "already delete"
                    )
                    if(check){
                        Toast.makeText(context, "delete complete", Toast.LENGTH_LONG).show()
//                        notifyDataSetChanged()
                    }
                    else{
                        Toast.makeText(context, "delete fail", Toast.LENGTH_LONG).show()
                    }
                })
                .setNegativeButton("ยกเลิก", DialogInterface.OnClickListener { dialogInterface, i ->

                })
                .show()
        }

        holder.itemView.setOnClickListener {
            if(aTree.deleteAt != null){
                Toast.makeText(context, "ต้นนี้ได้ลบไปแล้ว", Toast.LENGTH_LONG).show()
            }
            else {
                val intent = Intent(context, EditTree::class.java)
                intent.putExtra("plotID", plotID)
                intent.putExtra("orderName", orderName)
                intent.putExtra("dbh", dbh)
                intent.putExtra("girth", girth)
                intent.putExtra("totalLength", totalLength)
                intent.putExtra("merchLength", merchLength)
                intent.putExtra("sawdustWeight", sawdustWeight)
                intent.putExtra("hbhN", hbhN)
                intent.putExtra("hbhS", hbhS)
                intent.putExtra("hbhE", hbhE)
                intent.putExtra("hbhW", hbhW)

                context.startActivity(intent)
            }

        }
    }

    class TreeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val cardHeader = itemView.findViewById(R.id.cardHeaderLayout) as RelativeLayout
        val cardBody = itemView.findViewById(R.id.cardBodyLayout) as RelativeLayout
        val textOrder = itemView.findViewById(R.id.textOrder) as TextView
        val orderTreeTV = itemView.findViewById(R.id.orderTreeTV) as TextView
        val deleteBTN = itemView.findViewById(R.id.deleteBTN) as ImageButton
        val dbhTV = itemView.findViewById(R.id.dbhTV) as TextView
        val grithTV = itemView.findViewById(R.id.grithTV) as TextView
        val totalLengthTV = itemView.findViewById(R.id.totalLengthTV) as TextView
        val merchLengthTV = itemView.findViewById(R.id.merchLengthTV) as TextView
        val sawdustWeightTV = itemView.findViewById(R.id.sawdustWeightTV) as TextView
        val hbhN = itemView.findViewById(R.id.hbhN) as TextView
        val hbhS = itemView.findViewById(R.id.hbhS) as TextView
        val hbhE = itemView.findViewById(R.id.hbhE) as TextView
        val hbhW = itemView.findViewById(R.id.hbhW) as TextView

    }


}