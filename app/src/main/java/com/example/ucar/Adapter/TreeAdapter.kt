package com.example.ucar.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ucar.ui.EditTree
import com.example.ucar.R
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

        holder.itemView.setOnClickListener {
            var context : Context = holder.itemView.context
            val intent = Intent(context, EditTree::class.java)

            intent.putExtra("plotID", plotID)
            intent.putExtra("orderName", holder.orderTreeTV.text as String)
            intent.putExtra("dbh", holder.dbhTV.text as String)
            intent.putExtra("grith", holder.grithTV.text as String)
            intent.putExtra("totalLength", holder.totalLengthTV.text as String)
            intent.putExtra("merchLength", holder.merchLengthTV.text as String)
            intent.putExtra("sawdustWeight", holder.sawdustWeightTV.text as String)
            intent.putExtra("hbhN", holder.hbhN.text as String)
            intent.putExtra("hbhS", holder.hbhS.text as String)
            intent.putExtra("hbhE", holder.hbhE.text as String)
            intent.putExtra("hbhW", holder.hbhW.text as String)

            context.startActivity(intent)
        }
    }


    class TreeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val orderTreeTV = itemView.findViewById(R.id.orderTreeTV) as TextView
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