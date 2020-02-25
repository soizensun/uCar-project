package com.example.ucar.ui

import android.Manifest
import android.R.*
import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
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
import androidx.core.app.ActivityCompat
import com.example.ucar.SqliteConfig.SQLiteHelperTableSpacing
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList
import androidx.core.content.ContextCompat.getSystemService as getSystemService1

class HomeFragment : Fragment() {
    private lateinit var createDataBTN: Button
    private lateinit var cloneSpinner: Spinner
    private lateinit var spacingSpinner: Spinner
    private lateinit var plotOwnerRadioGroup: RadioGroup
    private lateinit var treeTypeRadioGroup: RadioGroup
    private lateinit var soilQualityRadioGroup: RadioGroup
    private lateinit var sawdustTypeRadioGroup: RadioGroup
    private val tmpPlot = Plot()
//    private var hasGps = false
//    private var hasNetwork = false
//    private var locationManager : LocationManager? = null
//    private var longitude : Double = 0.0
//    private var latitude : Double = 0.0

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val dbHelperTablePlot = SQLiteHelperTablePlot(root.context)
        val dbHelperTableClone = SQLiteHelperTableClone(root.context)
        val dbHelperTableSpacing = SQLiteHelperTableSpacing(root.context)

        createDataBTN = root.findViewById(R.id.createDataBTN) as Button
        cloneSpinner = root.findViewById(R.id.cloneSpinner) as Spinner
        spacingSpinner = root.findViewById(R.id.spacingSpinner) as Spinner
        plotOwnerRadioGroup = root.findViewById(R.id.plotOwnerRadioGroup) as RadioGroup
        treeTypeRadioGroup = root.findViewById(R.id.treeTypeRadioGroup) as RadioGroup
        soilQualityRadioGroup = root.findViewById(R.id.soilQualityRadioGroup) as RadioGroup
        sawdustTypeRadioGroup = root.findViewById(R.id.sawdustTypeRadioGroup) as RadioGroup
        cloneSpinner = root.findViewById(R.id.cloneSpinner) as Spinner

        // set auto latitude longitude


        // set data in to both of spinner
        val cloneOption: ArrayList<String> = ArrayList()
        val spacingOption: ArrayList<String> = ArrayList()
        cloneOption.addAll(dbHelperTableClone.getAllClone())
        spacingOption.addAll(dbHelperTableSpacing.getAllSpacing())

        cloneSpinner.adapter = ArrayAdapter(root.context, layout.simple_list_item_1, cloneOption)
        cloneSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tmpPlot.clone = parent?.getItemAtPosition(position).toString()
            }
        }

        spacingSpinner.adapter = ArrayAdapter(root.context, layout.simple_list_item_1, spacingOption)
        spacingSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tmpPlot.spacing = parent?.getItemAtPosition(position).toString()
            }
        }

        createDataBTN.setOnClickListener {
            tmpPlot.plotID = plotIDTextInput.text.toString()
            tmpPlot.ownerShip = (root.findViewById<RadioButton>(plotOwnerRadioGroup.checkedRadioButtonId)).text.toString()
            tmpPlot.plotName = plotNameTextInput.text.toString()
            tmpPlot.plantType = (root.findViewById<RadioButton>(treeTypeRadioGroup.checkedRadioButtonId)).text.toString()
            tmpPlot.ageYear = plotYearTextInput.text.toString().toInt()
            tmpPlot.ageMonth = plotMonthTextInput.text.toString().toInt()
            tmpPlot.latitude = latitudeTextInput.text.toString().toFloat()
            tmpPlot.longitude = longitudeTextInput.text.toString().toFloat()
            tmpPlot.survivalRate = surviveRateTextInput.text.toString().toFloat()
            tmpPlot.sampleTree = sampleTreeTextInput.text.toString().toInt()
            tmpPlot.soilQuality = (root.findViewById<RadioButton>(soilQualityRadioGroup.checkedRadioButtonId)).text.toString()
            tmpPlot.sawType = (root.findViewById<RadioButton>(sawdustTypeRadioGroup.checkedRadioButtonId)).text.toString()


            if (tmpPlot.plotID == "" || tmpPlot.ownerShip == null || tmpPlot.plotName == "" || tmpPlot.plantType == "" ||
                tmpPlot.ageYear == null || tmpPlot.ageMonth == null || tmpPlot.latitude == null || tmpPlot.longitude == null ||
                tmpPlot.survivalRate == null || tmpPlot.sampleTree == null || tmpPlot.soilQuality == "" || tmpPlot.sawType == ""
            ) {
                if(tmpPlot.ageMonth!! > 12) Toast.makeText(context, "อายุแปลง ( เดือน ) ไม่ถูกต้อง", Toast.LENGTH_LONG).show()
                Toast.makeText(context, "กรอกตรวจสอบข้อมูลอีกครั้ง", Toast.LENGTH_LONG).show()
            }
            else {
                dbHelperTablePlot.insertData(tmpPlot)
                val intent = Intent(root.context, InputATree::class.java)
                intent.putExtra("plotId", tmpPlot.plotID)
                intent.putExtra("count", "1")
                intent.putExtra("plotName", tmpPlot.plotName)
                startActivity(intent)
            }
        }

//        insert data in the first time
//
//        dbHelperTableClone.insertData(1, "G2")
//        dbHelperTableClone.insertData(2, "K58")
//        dbHelperTableClone.insertData(3, "K62")
//        dbHelperTableClone.insertData(4, "K7")
//        dbHelperTableClone.insertData(5, "K73")
//        dbHelperTableClone.insertData(6, "test")
//        dbHelperTableSpacing.insertData(1,"1.8x3.0")
//        dbHelperTableSpacing.insertData(2,"1.5x2.5")
//        dbHelperTableSpacing.insertData(3,"1.4x3.0")
//        dbHelperTableSpacing.insertData(4,"2.0x2.0")
//        dbHelperTableSpacing.insertData(5,"1.9x3.0")
//        dbHelperTableSpacing.insertData(6,"2.0X3.0")
//        dbHelperTableSpacing.insertData(7,"2.0X2.8")
//        dbHelperTableSpacing.insertData(8,"1.0x3.0")
        return root
    }
}