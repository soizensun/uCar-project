package com.example.ucar.SqliteConfig

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.ucar.model.Plot

class SQLiteHelperTablePlot : SQLiteOpenHelper {
    companion object {
        const val DATABASE_NAME = "plot.db"
        const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "table_plot"

        private const val ID = "_id"
        private const val PLOT_ID = "plot_id"
        private const val PLOT_NAME = "plot_name"
        private const val AGE_YEAR = "age_year"
        private const val AGE_MONTH = "age_month"
        private const val LATITUDE = "latitude"
        private const val LONGITUDE = "longitude"
        private const val SURVIVE_RATE = "survive_rate"
        private const val SAMPLE_TREE = "sample_tree"

        const val CREATE_TABLE : String = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " + "(" +
                ID + " INTEGER PRIMARY KEY," +
                PLOT_ID + " TEXT, " +
                PLOT_NAME + " TEXT, " +
                AGE_YEAR + " INTEGER, " +
                AGE_MONTH + " INTEGER, " +
                LATITUDE + " REAL, " +
                LONGITUDE + " REAL, " +
                SURVIVE_RATE + " REAL, " +
                SAMPLE_TREE + " INTEGER ); "
    }
    var context : Context

    constructor(context : Context) : super(context,
        DATABASE_NAME, null,
        DATABASE_VERSION
    ){
        this.context = context
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(plot: Plot){
        val values = ContentValues()
        val db = this.writableDatabase

        values.put(PLOT_ID, plot.plotID)
        values.put(PLOT_NAME, plot.plotName)
        values.put(AGE_YEAR, plot.ageYear)
        values.put(AGE_MONTH,plot.ageMonth)
        values.put(LATITUDE,plot.latitude)
        values.put(LONGITUDE,plot.longitude)
        values.put(SURVIVE_RATE,plot.serviveRate)
        values.put(SAMPLE_TREE,plot.sampleTree)

        Toast.makeText(context, "insert", Toast.LENGTH_LONG).show()
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllPlot(): ArrayList<Plot> {
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME"

        val cursor = db.rawQuery(selectALLQuery, null)

        val plotArray : ArrayList<Plot> = ArrayList()

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    var plot_id = cursor.getString(cursor.getColumnIndex(PLOT_ID))
                    var plot_name = cursor.getString(cursor.getColumnIndex(PLOT_NAME))
                    var age_year = cursor.getInt(cursor.getColumnIndex(AGE_YEAR))
                    var age_month = cursor.getInt(cursor.getColumnIndex(AGE_MONTH))
                    var latitude = cursor.getFloat(cursor.getColumnIndex(LATITUDE))
                    var longitude = cursor.getFloat(cursor.getColumnIndex(LONGITUDE))
                    var survive_rate = cursor.getFloat(cursor.getColumnIndex(SURVIVE_RATE))
                    var sample_tree = cursor.getInt(cursor.getColumnIndex(SAMPLE_TREE))

                    plotArray.add(Plot(plot_id, plot_name, age_year, age_month, latitude, longitude, survive_rate, sample_tree))
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return plotArray
    }
}