package com.example.ucar.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.ucar.model.Plot
import com.example.ucar.R
import com.example.ucar.SqliteConfig.SQLiteHelperTableClone
import com.example.ucar.SqliteConfig.SQLiteHelperTablePlot
import android.widget.ArrayAdapter
import com.example.ucar.ui.InputATree
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList

class HomeFragment : Fragment() {
    private lateinit var createDataBTN : Button
    internal lateinit var spinner : Spinner


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val dbHelperTablePlot = SQLiteHelperTablePlot(root.context)
        val dbHelperTableClone = SQLiteHelperTableClone(root.context)

        createDataBTN = root.findViewById(R.id.createDataBTN) as Button
        spinner = root.findViewById(R.id.cloneSpinner) as Spinner

        val option : ArrayList<String> = ArrayList()
        option.addAll(dbHelperTableClone.getAllClone())
        val adapter =  ArrayAdapter(root.context, android.R.layout.simple_list_item_1, option)
        spinner.adapter = adapter

        createDataBTN.setOnClickListener {
            val plotID = plotIDTextInput.text.toString()
            val plotName = plotNameTextInput.text.toString()
            val ageYear = plotYearTextInput.text.toString()
            val ageMonth = plotMonthTextInput.text.toString()
            val latitude = latitudeTextInput.text.toString()
            var longitude = longitudeTextInput.text.toString()
            var serviveRate = surviveRateTextInput.text.toString()
            var sampleTree = sampleTreeTextInput.text.toString()

            if(plotID == "" || plotName == "" || ageYear == "" || ageMonth == "" || latitude == "" || longitude == "" || serviveRate == "" || sampleTree == "" ){
                Toast.makeText(context, "กรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show()
            }
            else {
                val plot = Plot(
                    plotID,
                    plotName,
                    ageYear.toInt(),
                    ageMonth.toInt(),
                    latitude.toFloat(),
                    longitude.toFloat(),
                    serviveRate.toFloat(),
                    sampleTree.toInt()
                )
                dbHelperTablePlot.insertData(plot)
                startActivity(Intent(root.context, InputATree::class.java))
            }

            val dbHelperTableClone =
                SQLiteHelperTableClone(root.context)
//            dbHelperTableClone.insertData(1, "G2")
//            dbHelperTableClone.insertData(2, "K58")
//            dbHelperTableClone.insertData(3, "K62")
//            dbHelperTableClone.insertData(4, "K7")
//            dbHelperTableClone.insertData(5, "K73")
//            dbHelperTableClone.insertData(6, "test")

//            Toast.makeText(context, dbHelperTableClone.getAllClone(), Toast.LENGTH_LONG).show()

        }

        return root
    }
}