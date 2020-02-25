package com.example.ucar.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ucar.MainActivity
import com.example.ucar.R
import com.example.ucar.SqliteConfig.SQLiteHelperTableClone
import com.example.ucar.SqliteConfig.SQLiteHelperTablePlot
import com.example.ucar.SqliteConfig.SQLiteHelperTableSpacing
import com.example.ucar.model.Plot
import kotlinx.android.synthetic.main.activity_edit_plot.*

class EditPlot : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_plot)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tmpPlot = Plot()
        val id = intent.getIntExtra("id", 0)
        val plot = SQLiteHelperTablePlot(this).searchByID(id)
        val plotName = plot.plotName
        supportActionBar?.title = "ข้อมูลแปลง $plotName"

        // set default value in plot and spacing Spinner

        val cloneOption: ArrayList<String> = ArrayList()
        val spacingOption: ArrayList<String> = ArrayList()
        val dbCloneOption: ArrayList<String> = SQLiteHelperTableClone(this).getAllClone()
        val dbSpacingOption: ArrayList<String> = SQLiteHelperTableSpacing(this).getAllSpacing()
        dbCloneOption.add(0, plot.clone.toString())
        cloneOption.addAll(dbCloneOption)
        dbSpacingOption.add(0, plot.spacing.toString())
        spacingOption.addAll(dbSpacingOption)

        cloneSpinner.adapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, cloneOption)
        cloneSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tmpPlot.clone = parent?.getItemAtPosition(position).toString()
            }
        }
        spacingSpinner.adapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, spacingOption)
        spacingSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tmpPlot.spacing = parent?.getItemAtPosition(position).toString()
            }
        }

        // set default value in Radio
        when(plot.ownerShip){
            "บริษัท" -> plotOwner1RB.isChecked = true
            "เกษตรกร" -> plotOwner2RB.isChecked = true
            else -> {
                plotOwner1RB.isChecked = false
                plotOwner2RB.isChecked = false
            }
        }
        when(plot.plantType){
            "ไม้ปลูก" -> treeType1RB.isChecked = true
            "ไม้หน่อ" -> treeType2RB.isChecked = true
            else -> {
                treeType1RB.isChecked = false
                treeType2RB.isChecked = false
            }
        }
        when(plot.soilQuality){
            "ดี" -> soilQuality1RB.isChecked = true
            "ปานกลาง" -> soilQuality2RB.isChecked = true
            "เลว" -> soilQuality3RB.isChecked = true
            "ไม่ระบุ" -> soilQuality4RB.isChecked = true
            else -> {
                soilQuality1RB.isChecked = false
                soilQuality2RB.isChecked = false
                soilQuality3RB.isChecked = false
                soilQuality4RB.isChecked = false
            }
        }
        when(plot.sawType){
            "เลื่อยยนตร์" -> sawdustType1RB.isChecked = true
            "เลื่อยมือ" -> sawdustType2RB.isChecked = true
            "ไม่ระบุ" -> sawdustType3RB.isChecked = true
            else -> {
                sawdustType1RB.isChecked = false
                sawdustType2RB.isChecked = false
                sawdustType3RB.isChecked = false
            }
        }
        tmpPlot.id = id
        plotIDTextInput.setText(plot.plotID)
        plotNameTextInput.setText(plot.plotName)
        plotYearTextInput.setText(plot.ageYear.toString())
        plotMonthTextInput.setText(plot.ageMonth.toString())
        latitudeTextInput.setText(plot.latitude.toString())
        longitudeTextInput.setText(plot.longitude.toString())
        surviveRateTextInput.setText(plot.survivalRate.toString())
        sampleTreeTextInput.setText(plot.sampleTree.toString())

        editPlotDataBTN.setOnClickListener {

            tmpPlot.plotID = plotIDTextInput.text.toString()
            tmpPlot.ownerShip = (findViewById<RadioButton>(plotOwnerRadioGroup.checkedRadioButtonId)).text.toString()
            tmpPlot.plotName = plotNameTextInput.text.toString()
            tmpPlot.plantType = (findViewById<RadioButton>(treeTypeRadioGroup.checkedRadioButtonId)).text.toString()
            tmpPlot.ageYear = plotYearTextInput.text.toString().toInt()
            tmpPlot.ageMonth = plotMonthTextInput.text.toString().toInt()
            tmpPlot.latitude = latitudeTextInput.text.toString().toFloat()
            tmpPlot.longitude = longitudeTextInput.text.toString().toFloat()
            tmpPlot.survivalRate = surviveRateTextInput.text.toString().toFloat()
            tmpPlot.sampleTree = sampleTreeTextInput.text.toString().toInt()
            tmpPlot.soilQuality = (findViewById<RadioButton>(soilQualityRadioGroup.checkedRadioButtonId)).text.toString()
            tmpPlot.sawType = (findViewById<RadioButton>(sawdustTypeRadioGroup.checkedRadioButtonId)).text.toString()

            if (tmpPlot.plotID == "" || tmpPlot.ownerShip == null || tmpPlot.plotName == "" || tmpPlot.plantType == "" ||
                tmpPlot.ageYear == null || tmpPlot.ageMonth == null || tmpPlot.latitude == null || tmpPlot.longitude == null ||
                tmpPlot.survivalRate == null || tmpPlot.sampleTree == null || tmpPlot.soilQuality == "" || tmpPlot.sawType == ""
            ) {
                if(tmpPlot.ageMonth!! > 12) Toast.makeText(this, "อายุแปลง ( เดือน ) ไม่ถูกต้อง", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "กรอกตรวจสอบข้อมูลอีกครั้ง", Toast.LENGTH_LONG).show()
            }
            else {
                AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
//                .setTitle("คุณแน่ใจที่จะแก้ไขใช่ไหม")
                    .setMessage("คุณจะแก้ไขรายละเอียดแปลงนี้ใช่หรือไม่")
                    .setPositiveButton("ใช่", DialogInterface.OnClickListener { dialog, i ->
                        Log.i("test", tmpPlot.plotID)
                        val check = SQLiteHelperTablePlot(this).updatePlot(
                            tmpPlot.id!!,
                            tmpPlot.plotID!!,
                            tmpPlot.ownerShip!!,
                            tmpPlot.plotName!!,
                            tmpPlot.clone!!,
                            tmpPlot.plantType!!,
                            tmpPlot.ageYear!!,
                            tmpPlot.ageMonth!!,
                            tmpPlot.spacing!!,
                            tmpPlot.latitude!!,
                            tmpPlot.longitude!!,
                            tmpPlot.survivalRate!!,
                            tmpPlot.sampleTree!!,
                            tmpPlot.soilQuality!!,
                            tmpPlot.sawType!!,
                            ""
                        )
                        if(check){
                            Toast.makeText(applicationContext, "update complete", Toast.LENGTH_LONG).show()
//                            finish()
                            startActivity(Intent(this, LogFragment::class.java))
                        }
                        else{
                            Toast.makeText(applicationContext, "update fail", Toast.LENGTH_LONG).show()
                        }

                    })
                    .setNegativeButton("ไม่ใช่", DialogInterface.OnClickListener { dialogInterface, i ->
                        Toast.makeText(applicationContext, "Nothing Happened", Toast.LENGTH_LONG).show()
                        finish()
                    })
                    .show()

                Log.i("weweqq", tmpPlot.toString())
            }
        }
    }
    override fun onBackPressed() {
//        finish()
//        super.onBackPressed()
//        Toast.makeText(this,"There is no back action", Toast.LENGTH_LONG).show()
        return
    }
}
