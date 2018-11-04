package com.example.heejung.calendarapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast


class splashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            Thread.sleep(1300)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val dbVersion = 1
        var db1 = scheduleDB(this,"Schedule.db",dbVersion)
        var db: SQLiteDatabase = db1.writableDatabase
        db.execSQL("CREATE TABLE IF NOT EXISTS SCHEDULE (calendar DATE, info TEXT);")
//        db.execSQL("DROP TABLE IF EXISTS SCHEDULE");
        var intent = Intent(this,MainActivity::class.java);
        startActivity(intent)
        finish()
    }
}
