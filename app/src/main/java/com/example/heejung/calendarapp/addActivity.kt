package com.example.heejung.calendarapp

import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*


class addActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        var date = intent.getStringExtra("date")
        dateText.text = date

    }

    fun addData(view: View){

        if(addText.text.length < 1)
            Toast.makeText(applicationContext,"한 글자 이상 입력해주세요.",Toast.LENGTH_SHORT).show()
        else{
            var date = dateText.text.toString()
            var info = addText.text.toString()
            Toast.makeText(applicationContext,info,Toast.LENGTH_SHORT).show()
            val dbVersion = 1
            var db1 = scheduleDB(this,"Schedule.db",dbVersion)
            var db:SQLiteDatabase = db1.writableDatabase
            db.execSQL("INSERT INTO SCHEDULE VALUES ('$date','$info');")
            db.close()
            finish()
        }

    }
}
