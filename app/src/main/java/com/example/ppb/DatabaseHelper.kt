package com.example.ppb

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 3
        private const val DATABASE_NAME = "MotorDB"
        private const val TABLE_MOTOR = "Motor"
        private const val TABLE_MOBIL = "Mobil"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableMotor = ("CREATE TABLE " + TABLE_MOTOR + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT)")
        db?.execSQL(createTableMotor)
        insertDefaultMotorTypes(db)

        val createTableMobil = ("CREATE TABLE " + TABLE_MOBIL + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT)")
        db?.execSQL(createTableMobil)
        insertDefaultMobilTypes(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MOTOR")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MOBIL")
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

    private fun insertDefaultMobilTypes(db: SQLiteDatabase?) {
        val mobilTypes = arrayOf("Muzdi CX-5 (2024)", "Hundi Bro (2023)", "Totoya Hulek (2024)")
        for (type in mobilTypes) {
            val values = ContentValues()
            values.put(COLUMN_NAME, type)
            db?.insert(TABLE_MOBIL, null, values)
        }
    }

    fun getAllMotorTypes(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_MOTOR", null)
    }

    fun getAllMobilTypes(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_MOBIL", null)
    }
}