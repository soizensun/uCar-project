package com.example.ucar.SqliteConfig

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.ucar.model.ATree

class SQLiteHelperTableATree : SQLiteOpenHelper {
    companion object {
        const val DATABASE_NAME = "tree_info.db"
        const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "table_tree_information"

        private const val ID = "_id"
        private const val PLOT_ID = "plot_id"
        private const val ORDER_TREE = "order_tree"
        private const val GRITH = "grith"
        private const val DBH = "dbh"
        private const val TOTAL_LENGHT = "total_lenght"
        private const val MERCH_LENGHT = "merch_lenght"
        private const val SAW_DUST_WEIGHT = "saw_dust_weight"
        private const val HBH_N = "hbh_n"
        private const val HBH_S = "hbh_s"
        private const val HBH_E = "hbh_e"
        private const val HBH_W = "hbh_w"

        const val CREATE_TABLE_SQL: String =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " + "(" +
                    ID + " INTEGER PRIMARY KEY," +
                    PLOT_ID + " TEXT, " +
                    ORDER_TREE + " INTEGER, " +
                    GRITH + " REAL, " +
                    DBH + " REAL, " +
                    TOTAL_LENGHT + " REAL, " +
                    MERCH_LENGHT + " REAL, " +
                    SAW_DUST_WEIGHT + " REAL, " +
                    HBH_N + " REAL, " +
                    HBH_S + " REAL, " +
                    HBH_E + " REAL, " +
                    HBH_W + " REAL, " +
                    "created_at" + " TIMESTAMP NOT NULL," +
                    "deleted_at" + " TIMESTAMP NULL DEFAULT NULL ); "
    }

    var context: Context

    constructor(context: Context) : super(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {
        this.context = context
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREATE_TABLE_SQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(atree: ATree): Long {
        val values = ContentValues()
        val db = this.writableDatabase

        values.put(PLOT_ID, atree.plotID)
        values.put(ORDER_TREE, atree.orderTree)
        values.put(GRITH, atree.grith)
        values.put(DBH, atree.dbh)
        values.put(TOTAL_LENGHT, atree.totalLenght)
        values.put(MERCH_LENGHT, atree.merchLenght)
        values.put(SAW_DUST_WEIGHT, atree.sawdustWeight)
        values.put(HBH_N, atree.hbhNorth)
        values.put(HBH_S, atree.hbhsouth)
        values.put(HBH_E, atree.hbhEast)
        values.put(HBH_W, atree.hbhWest)
        values.put("created_at", "mockDateNow()")


        var success = db.insert(TABLE_NAME, null, values)
        Toast.makeText(context, "insert a tree", Toast.LENGTH_LONG).show()
//        Log.i("ttt", success.toString())
        db.close()
        return success
    }

    fun searchTree(plotID : String) : ArrayList<ATree> {
        val db = readableDatabase
        val searchATreeSQL = "SELECT * FROM $TABLE_NAME where $PLOT_ID = \"$plotID\""

        val cursor = db.rawQuery(searchATreeSQL, null)

        val aTreeArray : ArrayList<ATree> = ArrayList()

        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    val plotId = cursor.getString(cursor.getColumnIndex(PLOT_ID))
                    val orderTree = cursor.getInt(cursor.getColumnIndex(ORDER_TREE))
                    val grith = cursor.getFloat(cursor.getColumnIndex(GRITH))
                    val dbh = cursor.getFloat(cursor.getColumnIndex(DBH))
                    val totalLenght = cursor.getFloat(cursor.getColumnIndex(TOTAL_LENGHT))
                    val merchLenght = cursor.getFloat(cursor.getColumnIndex(MERCH_LENGHT))
                    val sawDustWeight = cursor.getFloat(cursor.getColumnIndex(SAW_DUST_WEIGHT))
                    val hbhN = cursor.getFloat(cursor.getColumnIndex(HBH_N))
                    val hbhS = cursor.getFloat(cursor.getColumnIndex(HBH_S))
                    val hbhE = cursor.getFloat(cursor.getColumnIndex(HBH_E))
                    val hbhW = cursor.getFloat(cursor.getColumnIndex(HBH_W))

                    aTreeArray.add(
                        ATree(
                            plotId,
                            orderTree,
                            dbh,
                            grith,
                            totalLenght,
                            merchLenght,
                            sawDustWeight,
                            hbhN,
                            hbhS,
                            hbhE,
                            hbhW
                        )
                    )
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return aTreeArray
    }


}