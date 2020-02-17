package com.example.ucar.ui

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ucar.R
import com.example.ucar.SqliteConfig.SQLiteHelperTableATree
import kotlinx.android.synthetic.main.activity_edit_tree.*

class EditTree : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_tree)
        val actionBar = supportActionBar
        actionBar?.hide()

        var plotID = intent.getStringExtra("plotID")
        var orderName = intent.getStringExtra("orderName")
        var dbh = intent.getStringExtra("dbh")
        var grith = intent.getStringExtra("grith")
        var totalLength = intent.getStringExtra("totalLength")
        var merchLength = intent.getStringExtra("merchLength")
        var sawdustWeight = intent.getStringExtra("sawdustWeight")
        var hbhN = intent.getStringExtra("hbhN")
        var hbhS = intent.getStringExtra("hbhS")
        var hbhE = intent.getStringExtra("hbhE")
        var hbhW = intent.getStringExtra("hbhW")

        countTVEdit.text = "แก้ไขต้นที่ " + orderName
        DBHTextInputEdit.setText(dbh)
        GrithTextInputEdit.setText(grith)
        treeHeightTextInputEdit.setText(totalLength)
        activeTreeHeightTextInputEdit.setText(merchLength)
        sawdustTextInputEdit.setText(sawdustWeight)
        hardNorthTextInputEdit.setText(hbhN)
        hardSouthTextInputEdit.setText(hbhS)
        hardEastTextInputEdit.setText(hbhE)
        hardWestTextInputEdit.setText(hbhW)

        saveBTN.setOnClickListener {
            AlertDialog.Builder(this)
//                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("คุณแน่ใจที่จะแก้ไขใช่ไหม")
//                .setMessage("If yes then application will close")
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
                        hardWestTextInputEdit.text.toString()
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
