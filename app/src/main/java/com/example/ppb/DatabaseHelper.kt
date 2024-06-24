package com.example.ppb

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "MotorDB"
        private const val TABLE_MOTOR = "Motor"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE " + TABLE_MOTOR + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT)")
        db?.execSQL(createTable)
        insertDefaultMotorTypes(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MOTOR")
        onCreate(db)
    }

    private fun insertDefaultMotorTypes(db: SQLiteDatabase?) {
        val motorTypes = arrayOf("POX (2022)", "BeTA-FI (2021)", "SPACE (2020)", "Scoops-FI (2024)")
        for (type in motorTypes) {
            val values = ContentValues()
            values.put(COLUMN_NAME, type)
            db?.insert(TABLE_MOTOR, null, values)
        }
    }

    fun getAllMotorTypes(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_MOTOR", null)
    }
}