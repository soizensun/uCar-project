package com.example.ucar.SqliteConfig

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.util.ArrayList

class SQLiteHelperTableClone : SQLiteOpenHelper {
    companion object {
        const val DATABASE_NAME = "clone.db"
        const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "tmp_clone"

        private const val ID = "_id"
        private const val CLONE_ID = "clone_ID"
        private const val CLONE = "clone"

        const val CREATE_TABLE : String = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " + "(" +
                ID + " INTEGER PRIMARY KEY," +
                CLONE_ID + " INTEGER, " +
                CLONE + " TEXT ); "
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

    fun insertData(clone_ID : Int, clone : String){
        val values = ContentValues()
        val db = this.writableDatabase

        values.put(CLONE_ID, clone_ID)
        values.put(CLONE, clone)

        Toast.makeText(context, "insert clone", Toast.LENGTH_LONG).show()
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllClone(): ArrayList<String> {
        var allClone: String = ""
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectALLQuery, null)
        val cloneArray : ArrayList<String> = ArrayList()
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
//                    var id = cursor.getString(cursor.getColumnIndex(ID))
//                    var clone_ID = cursor.getString(cursor.getColumnIndex(CLONE_ID))
                    var clone = cursor.getString(cursor.getColumnIndex(CLONE))

                    cloneArray.add(clone)
//                    allClone = "$allClone\n$clone"
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return cloneArray
    }


}