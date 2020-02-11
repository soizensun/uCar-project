package com.example.ucar.SqliteConfig

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.ucar.model.Plot
import kotlin.collections.ArrayList

class SQLiteHelperTablePlot : SQLiteOpenHelper {
    companion object {
        const val DATABASE_NAME = "plot.db"
        const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "table_plot"

        private const val ID = "_id"
        private const val PLOT_ID = "plot_id"
        private const val PLOT_OWNERSHIP = "plot_ownership"
        private const val PLOT_NAME = "plot_name"
        private const val CLONE_ID = "clone_id"
        private const val TREE_TYPE = "tree_type"
        private const val AGE_YEAR = "age_year"
        private const val AGE_MONTH = "age_month"
        private const val SPACING_ID = "spacing_id"
        private const val LATITUDE = "latitude"
        private const val LONGITUDE = "longitude"
        private const val SURVIVE_RATE = "survive_rate"
        private const val SAMPLE_TREE = "sample_tree"
        private const val SOIL_QUALITY = "soil_quality"
        private const val SAW_DUST = "saw_dust"

        const val CREATE_TABLE_SQL: String =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " + "(" +
                    ID + " INTEGER PRIMARY KEY," +
                    PLOT_ID + " TEXT, " +
                    PLOT_OWNERSHIP + " TEXT, " +
                    PLOT_NAME + " TEXT, " +
                    CLONE_ID + " TEXT, " +
                    TREE_TYPE + " TEXT, " +
                    AGE_YEAR + " INTEGER, " +
                    AGE_MONTH + " INTEGER, " +
                    SPACING_ID + " TEXT, " +
                    LATITUDE + " REAL, " +
                    LONGITUDE + " REAL, " +
                    SURVIVE_RATE + " REAL, " +
                    SAMPLE_TREE + " INTEGER, " +
                    SOIL_QUALITY + " TEXT, " +
                    SAW_DUST + " TEXT, " +
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

    fun insertData(plot: Plot): Long {

        val values = ContentValues()
        val db = this.writableDatabase

        values.put(PLOT_ID, plot.plotID)
        values.put(PLOT_OWNERSHIP, plot.ownerShip)
        values.put(PLOT_NAME, plot.plotName)
        values.put(CLONE_ID, plot.clone)
        values.put(TREE_TYPE, plot.plantType)
        values.put(AGE_YEAR, plot.ageYear)
        values.put(AGE_MONTH, plot.ageMonth)
        values.put(SPACING_ID, plot.spacing)
        values.put(LATITUDE, plot.latitude)
        values.put(LONGITUDE, plot.longitude)
        values.put(SURVIVE_RATE, plot.survivalRate)
        values.put(SAMPLE_TREE, plot.sampleTree)
        values.put(SOIL_QUALITY, plot.soilQuality)
        values.put(SAW_DUST, plot.sawType)
        values.put("created_at", "mockDateNow()")
//        Log.i(
//            "c",
//            plot.plotID + plot.ownerShip + plot.plotName + plot.clone + plot.plantType + plot.ageYear + plot.ageMonth + plot.spacing +
//                    plot.latitude + plot.longitude + plot.survivalRate + plot.sampleTree + plot.soilQuality + plot.sawType
//        )
        Toast.makeText(context, "insert a plot", Toast.LENGTH_LONG).show()
        var success = db.insert(TABLE_NAME, null, values)
//        Log.i("ttt", success.toString())
        db.close()
        return success
    }


    fun getAllPlot(): ArrayList<Plot> {
        val db = readableDatabase
        val selectALLSQL = "SELECT * FROM $TABLE_NAME"

        val cursor = db.rawQuery(selectALLSQL, null)

        val plotArray : ArrayList<Plot> = ArrayList()

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val plotID = cursor.getString(cursor.getColumnIndex(PLOT_ID))
                    val plotOwnership = cursor.getString(cursor.getColumnIndex(PLOT_OWNERSHIP))
                    val plotName = cursor.getString(cursor.getColumnIndex(PLOT_NAME))
                    val cloneId = cursor.getString(cursor.getColumnIndex(CLONE_ID))
                    val treeType = cursor.getString(cursor.getColumnIndex(TREE_TYPE))
                    val ageYear = cursor.getInt(cursor.getColumnIndex(AGE_YEAR))
                    val ageMonth = cursor.getInt(cursor.getColumnIndex(AGE_MONTH))
                    val spacingId = cursor.getString(cursor.getColumnIndex(SPACING_ID))
                    val latitude = cursor.getFloat(cursor.getColumnIndex(LATITUDE))
                    val longitude = cursor.getFloat(cursor.getColumnIndex(LONGITUDE))
                    val surviveRate = cursor.getFloat(cursor.getColumnIndex(SURVIVE_RATE))
                    val sampleTree = cursor.getInt(cursor.getColumnIndex(SAMPLE_TREE))
                    val soilQuality = cursor.getString(cursor.getColumnIndex(SOIL_QUALITY))
                    val sawDust = cursor.getString(cursor.getColumnIndex(SAW_DUST))

                    plotArray.add(
                        Plot(
                            plotID,
                            plotOwnership,
                            plotName,
                            cloneId,
                            treeType,
                            ageYear,
                            ageMonth,
                            spacingId,
                            latitude,
                            longitude,
                            surviveRate,
                            sampleTree,
                            soilQuality,
                            sawDust
                        )
                    )
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return plotArray
    }
}