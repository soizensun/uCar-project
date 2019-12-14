package com.example.ucar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.ucar.Plot
import com.example.ucar.R
import com.example.ucar.SQLiteHelper

import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val sqliteHelper = SQLiteHelper(root.context)

        val createDataBTN : Button = root.findViewById(R.id.createDataBTN)

        createDataBTN.setOnClickListener {
            val sqLiteHelper = SQLiteHelper(root.context)

            val plotID = plotIDTextInput.text.toString()
            val plotName = plotNameTextInput.text.toString()
            val ageYear = plotYearTextInput.text.toString()
            val ageMonth = plotMonthTextInput.text.toString()
            val latitude = latitudeTextInput.text.toString()
            var longitude = longitudeTextInput.text.toString()
            var serviveRate = surviveRateTextInput.text.toString()
            var sampleTree = sampleTreeTextInput.text.toString()

            val plot = Plot(plotID,
                plotName,
                ageYear.toInt(),
                ageMonth.toInt(),
                latitude.toInt(),
                longitude.toInt(),
                serviveRate.toInt(),
                sampleTree.toInt())
            sqliteHelper.insertData(plot)
        }

        return root
    }
}