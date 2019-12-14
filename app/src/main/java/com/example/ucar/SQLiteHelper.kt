package com.example.ucar

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class SQLiteHelper : SQLiteOpenHelper {
    companion object {
        const val DATABASE_NAME = "plot.db"
        const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "table_plot"
        private const val ID = "_id"

//        private const val FIRST_NAME = "first_name"
////        private const val LAST_NAME = "last_name"
////        private const val AGE = "age"
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
                LATITUDE + " INTEGER, " +
                LONGITUDE + " INTEGER, " +
                SURVIVE_RATE + " INTEGER, " +
                SAMPLE_TREE + " INTEGER ); "


    }
    var context : Context

    constructor(context : Context) : super(context, DATABASE_NAME, null, DATABASE_VERSION){
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
}