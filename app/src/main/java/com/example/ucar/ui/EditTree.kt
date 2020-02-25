package com.example.ucar.ui

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ucar.R
import com.example.ucar.SqliteConfig.SQLiteHelperTableATree
import kotlinx.android.synthetic.main.activity_edit_tree.*

class EditTree : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_tree)
        val actionBar = supportActionBar
        actionBar?.hide()

        val plotID = intent.getStringExtra("plotID")
        val orderName = intent.getStringExtra("orderName")
        val dbh = intent.getStringExtra("dbh")
        val girth = intent.getStringExtra("girth")
        val totalLength = intent.getStringExtra("totalLength")
        val merchLength = intent.getStringExtra("merchLength")
        val sawdustWeight = intent.getStringExtra("sawdustWeight")
        val hbhN = intent.getStringExtra("hbhN")
        val hbhS = intent.getStringExtra("hbhS")
        val hbhE = intent.getStringExtra("hbhE")
        val hbhW = intent.getStringExtra("hbhW")

        countTVEdit.text = "แก้ไขต้นที่ $orderName"
        DBHTextInputEdit.setText(dbh)
        GrithTextInputEdit.setText(girth)
        treeHeightTextInputEdit.setText(totalLength)
        activeTreeHeightTextInputEdit.setText(merchLength)
        sawdustTextInputEdit.setText(sawdustWeight)
        hardNorthTextInputEdit.setText(hbhN)
        hardSouthTextInputEdit.setText(hbhS)
        hardEastTextInputEdit.setText(hbhE)
        hardWestTextInputEdit.setText(hbhW)

        saveBTN.setOnClickListener {
            AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
//                .setTitle("คุณแน่ใจที่จะแก้ไขใช่ไหม")
                .setMessage("คุณจะแก้ไข ต้นไม้ต้นที่ $orderName ใช่หรือไม่")
                .setPositiveButton("ใช่", DialogInterface.OnClickListener { dialog, i ->
                    val sqLiteHelperTableATree = SQLiteHelperTableATree(this)
                    val check = sqLiteHelperTableATree.updateData(
                        plotID,
                        orderName,
                        DBHTextInputEdit.text.toString(),
                        GrithTextInputEdit.text.toString(),
                        treeHeightTextInputEdit.text.toString(),
                        activeTreeHeightTextInputEdit.text.toString(),
                        sawdustTextInputEdit.text.toString(),
                        hardNorthTextInputEdit.text.toString(),
                        hardSouthTextInputEdit.text.toString(),
                        hardEastTextInputEdit.text.toString(),
                        hardWestTextInputEdit.text.toString(),
                        ""
                    )
                    if(check){
                        Toast.makeText(applicationContext, "update complete", Toast.LENGTH_LONG).show()
                        finish()
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
        }
    }
    override fun onBackPressed() {
//        super.onBackPressed()
        Toast.makeText(this,"There is no back action",Toast.LENGTH_LONG).show()
        return
    }
}
