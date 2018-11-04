package com.example.heejung.calendarapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.TabHost
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.database.sqlite.SQLiteDatabase
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var cal1 = Calendar.getInstance()
    var cal2 = Calendar.getInstance()
    var cal3 = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabHost1 = findViewById(R.id.tabHost) as TabHost
        tabHost1.setup()

        var tab1 = tabHost1.newTabSpec("Tab Spec1")
        tab1.setContent(R.id.tab1)
        tab1.setIndicator("MONTHLY")
        tabHost1.addTab(tab1)

        var tab2 = tabHost1.newTabSpec("Tab Spec2")
        tab2.setContent(R.id.tab2)
        tab2.setIndicator("WEEKLY")
        tabHost1.addTab(tab2)

        var tab3 = tabHost1.newTabSpec("Tab Spec3")
        tab3.setContent(R.id.tab3)
        tab3.setIndicator("DAILY")
        tabHost1.addTab(tab3)

        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
            cal1.set(year,month,dayOfMonth)
        }
        monthly()
        weekly()
        daily()
        floatingActionButton3.setOnClickListener(View.OnClickListener {
            cal2.add(Calendar.DAY_OF_WEEK_IN_MONTH, -1)
            weekly()
        })
        floatingActionButton4.setOnClickListener(View.OnClickListener {
            cal2.add(Calendar.DAY_OF_WEEK_IN_MONTH, 1)
            weekly()
        })

        floatingActionButton1.setOnClickListener(View.OnClickListener {
            cal3.add(Calendar.DATE, -1)
            daily()
        })
        floatingActionButton2.setOnClickListener(View.OnClickListener {
            cal3.add(Calendar.DATE, 1)
            daily()
        })
    }

    fun sync(view: View){
        monthly()
        weekly()
        daily()
    }

    fun Button(view:View){
        var intent = Intent(this,addActivity::class.java)
        intent.putExtra("date", "" + cal1.get(Calendar.YEAR) + "/" + (cal1.get(Calendar.MONTH)+1) + "/" + cal1.get(Calendar.DATE))
        startActivity(intent)
    }

    fun imgB(view:View){
        var intent = Intent(this,addActivity::class.java)
        intent.putExtra("date", "" + cal3.get(Calendar.YEAR) + "/" + (cal3.get(Calendar.MONTH)+1) + "/" + cal3.get(Calendar.DATE))
        startActivity(intent)
    }
    fun monthly(){
        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        val dbVersion = 1
        var db1 = scheduleDB(this,"Schedule.db",dbVersion)
        var db: SQLiteDatabase = db1.writableDatabase

        db.close()
    }
    fun weekly(){
        var num = cal2.get(Calendar.DAY_OF_WEEK) - 1
        cal2.add(Calendar.DATE, -num); sun2.text = "" + cal2.get(Calendar.DATE)
        var date = "" + cal2.get(Calendar.YEAR) + "/" + (cal2.get(Calendar.MONTH)+1) + "/" + cal2.get(Calendar.DATE)
        doing(date,sun3);
        dateText.text = "" + cal2.get(Calendar.YEAR) + "/" + (cal2.get(Calendar.MONTH)+1)
        cal2.add(Calendar.DATE, 1); mon2.text = "" + cal2.get(Calendar.DATE)
        date = "" + cal2.get(Calendar.YEAR) + "/" + (cal2.get(Calendar.MONTH)+1) + "/" + cal2.get(Calendar.DATE)
        doing(date,mon3);
        cal2.add(Calendar.DATE, 1); tue2.text = "" + cal2.get(Calendar.DATE)
        date = "" + cal2.get(Calendar.YEAR) + "/" + (cal2.get(Calendar.MONTH)+1) + "/" + cal2.get(Calendar.DATE)
        doing(date,tue3);
        cal2.add(Calendar.DATE, 1); wed2.text = "" + cal2.get(Calendar.DATE)
        date = "" + cal2.get(Calendar.YEAR) + "/" + (cal2.get(Calendar.MONTH)+1) + "/" + cal2.get(Calendar.DATE)
        doing(date,wed3);
        cal2.add(Calendar.DATE, 1); thr2.text = "" + cal2.get(Calendar.DATE)
        date = "" + cal2.get(Calendar.YEAR) + "/" + (cal2.get(Calendar.MONTH)+1) + "/" + cal2.get(Calendar.DATE)
        doing(date,thr3);
        cal2.add(Calendar.DATE, 1); fri2.text = "" + cal2.get(Calendar.DATE)
        date = "" + cal2.get(Calendar.YEAR) + "/" + (cal2.get(Calendar.MONTH)+1) + "/" + cal2.get(Calendar.DATE)
        doing(date,fri3);
        cal2.add(Calendar.DATE, 1); sat2.text = "" + cal2.get(Calendar.DATE)
        date = "" + cal2.get(Calendar.YEAR) + "/" + (cal2.get(Calendar.MONTH)+1) + "/" + cal2.get(Calendar.DATE)
        doing(date,sat3);

    }
    fun daily(){
        textView.text = "" + cal3.get(Calendar.YEAR) + "/" + (cal3.get(Calendar.MONTH)+1) + "/" + cal3.get(Calendar.DATE)
        var date = textView.text.toString()

        doing(date,textView2)

    }
    fun doing(date:String, textView: TextView){
        val dbVersion = 1
        var db1 = scheduleDB(this,"Schedule.db",dbVersion)
        var db: SQLiteDatabase = db1.writableDatabase

        var cursor = db.rawQuery("SELECT info FROM SCHEDULE WHERE calendar = '$date'",null)
        var sb = StringBuffer()
        while(cursor.moveToNext())
            sb.append(cursor.getString(0)+"\n")
        textView.text = sb.toString()

        db.close()
    }
}
