package com.example.ucar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ScrollView
import android.widget.Toast
import com.example.ucar.MainActivity
import com.example.ucar.R
import com.example.ucar.SqliteConfig.SQLiteHelperTableATree
import com.example.ucar.model.ATree
import com.example.ucar.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_input_atree.*

class InputATree : AppCompatActivity() {
    private lateinit var confirmDataBTN : Button
    private lateinit var nextTreeBTN : Button

    private val tmpATree = ATree()
    private val sqLiteHelperTableATree = SQLiteHelperTableATree(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_atree)

        confirmDataBTN = findViewById(R.id.confirmDataBTN)
        nextTreeBTN = findViewById(R.id.nextTreeBTN)

        val actionBar = supportActionBar
        actionBar?.hide()

        var count : Int = intent.getStringExtra("count").toInt()
        var plotId : String = intent.getStringExtra("plotId")
        var plotName : String = intent.getStringExtra("plotName")

        countTV.text = "ต้นที่ " + count.toString()

//        tmpATree.plotID = plotId

//        tmpATree.dbh = DBHTextInput.text.toString().toFloat()
//        tmpATree.grith = GrithTextInput.text.toString().toFloat()
//        tmpATree.totalLenght = treeHeightTextInput.text.toString().toFloat()
//        tmpATree.merchLenght = activeTreeHeightTextInput.text.toString().toFloat()
//        tmpATree.sawdustWeight = sawdustTextInput.text.toString().toFloat()
//        tmpATree.hbhNorth = hardNorthTextInput.text.toString().toFloat()
//        tmpATree.hbhsouth = hardSouthTextInput.text.toString().toFloat()
//        tmpATree.hbhEast = hardEastTextInput.text.toString().toFloat()
//        tmpATree.hbhWest = hardWestTextInput.text.toString().toFloat()



        confirmDataBTN.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        nextTreeBTN.setOnClickListener {

            tmpATree.plotID = plotId
                    tmpATree.orderTree = count
            tmpATree.dbh = DBHTextInput.text.toString().toFloat()
            tmpATree.grith = GrithTextInput.text.toString().toFloat()
            tmpATree.totalLenght = treeHeightTextInput.text.toString().toFloat()
            tmpATree.merchLenght = activeTreeHeightTextInput.text.toString().toFloat()
            tmpATree.sawdustWeight = sawdustTextInput.text.toString().toFloat()
            tmpATree.hbhNorth = hardNorthTextInput.text.toString().toFloat()
            tmpATree.hbhsouth = hardSouthTextInput.text.toString().toFloat()
            tmpATree.hbhEast = hardEastTextInput.text.toString().toFloat()
            tmpATree.hbhWest = hardWestTextInput.text.toString().toFloat()

            Log.i("test", tmpATree.toString())

            sqLiteHelperTableATree.insertData(tmpATree)

            DBHTextInput.setText("")
            GrithTextInput.setText("")
            treeHeightTextInput.setText("")
            activeTreeHeightTextInput.setText("")
            sawdustTextInput.setText("")
            hardNorthTextInput.setText("")
            hardSouthTextInput.setText("")
            hardEastTextInput.setText("")
            hardWestTextInput.setText("")
            scrollViewATreeActivity.fullScroll(ScrollView.FOCUS_UP)

            count += 1
            countTV.text = "ต้นที่ " + count.toString()
        }
    }



    // make this activity does not press back BTN
    override fun onBackPressed() {
//        super.onBackPressed()
        Toast.makeText(this,"There is no back action",Toast.LENGTH_LONG).show()
        return
    }


}
