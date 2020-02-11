package com.example.ucar.SqliteConfig

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class SQLiteHelperTableSpacing : SQLiteOpenHelper {


    companion object {
        const val DATABASE_NAME = "spacing.db"
        const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "tmp_spacing"

        private const val ID = "_id"
        private const val SPACING_ID = "spacing_ID"
        private const val SPACING = "spacing"
        const val CREATE_TABLE : String = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " + "(" +
                ID + " INTEGER PRIMARY KEY, " +
                SPACING_ID + " INTEGER, " +
                SPACING + " TEXT ); "
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

    fun insertData(spacing_ID : Int, spacing : String){
        val values = ContentValues()
        val db = this.writableDatabase

        values.put(SPACING_ID, spacing_ID)
        values.put(SPACING, spacing)

        Toast.makeText(context, "insert spacing", Toast.LENGTH_LONG).show()
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllSpacing(): ArrayList<String> {
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectALLQuery, null)
        val spacingArray : ArrayList<String> = ArrayList()
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
//                    var id = cursor.getString(cursor.getColumnIndex(ID))
//                    var clone_ID = cursor.getString(cursor.getColumnIndex(CLONE_ID))
                    var spacing = cursor.getString(cursor.getColumnIndex(SPACING))

                    spacingArray.add(spacing)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return spacingArray
    }
}
