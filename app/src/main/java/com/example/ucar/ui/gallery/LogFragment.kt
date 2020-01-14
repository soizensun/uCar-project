package com.example.ucar.ui.gallery

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Half.toFloat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ucar.Adapter.PlotAdapter
import com.example.ucar.R
import com.example.ucar.SqliteConfig.SQLiteHelperTablePlot
import com.example.ucar.model.Plot
import java.util.ArrayList

class LogFragment : Fragment() {


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

        val recyclerView =  root.findViewById(R.id.recyclerviewLog) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(root.context, LinearLayout.VERTICAL, false)

        var plots = ArrayList<Plot>()
        val dbHelperTablePlot = SQLiteHelperTablePlot(root.context)
        plots = dbHelperTablePlot.getAllPlot()

//        plots.add(Plot("1234", "lung chai", 23, 43,  toFloat(12), toFloat(12), toFloat(12), 45))
//        plots.add(Plot("12asdf34", "lungasdf chai", 23, 43, toFloat(12), toFloat(12), toFloat(12), 45))
//        plots.add(Plot("123ddd4", "lunasdfgw erchai", 23, 43, toFloat(12), toFloat(12), toFloat(12), 45))


        val adapter = PlotAdapter(plots)
        recyclerView.adapter = adapter
        return root
    }
}