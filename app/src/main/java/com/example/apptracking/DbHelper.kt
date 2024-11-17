package com.example.apptracking

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(myContext: Context): SQLiteOpenHelper(myContext,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "klo.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sql = "CREATE TABLE IF NOT EXISTS usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, usu TEXT NOT NULL, pwd TEXT NOT NULL, nombre TEXT NOT NULL)"
        db.execSQL(sql)

        val values = ContentValues()
        values.put("usu", "useradmin")
        values.put("pwd", "1234567")
        values.put("nombre", "Diego Alonso Guevara")
        db.insert("usuario", null, values)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuario")
        onCreate(db)

    }
}
