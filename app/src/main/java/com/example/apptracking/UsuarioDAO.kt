package com.example.apptracking

import android.content.Context
import android.database.Cursor

class UsuarioDAO (myContext: Context)  {

    private var dbHelper: DbHelper = DbHelper(myContext)

    fun validarUsuario(username: String, password: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM usuario WHERE usu = ? AND pwd = ?",
            arrayOf(username, password)
        )
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

}