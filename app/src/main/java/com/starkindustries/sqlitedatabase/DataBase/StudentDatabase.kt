package com.starkindustries.sqlitedatabase.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.starkindustries.sqlitedatabase.Keys.Keys
import com.starkindustries.sqlitedatabase.Model.Student

class StudentDatabase(var context_: Context,var dbName:String,var version:Int): SQLiteOpenHelper(context_,dbName,null,version)
{
    lateinit var context:Context
    init{
        this.context=context_
    }
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("Create Table "+Keys.STUDENTS_TABLE+" ("+Keys.SID+" Text Primary Key, "+Keys.NAME+" Text, "+Keys.DEPARTMENT+" Text,"+Keys.EMAIL+" Text,"+Keys.USERNAME+" Text,"+Keys.PASSWORD+" Text)")
        }
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        if (db!=null)
        {
            db.execSQL("drop table if exists"+Keys.STUDENTS_TABLE)
            onCreate(db)
        }
    }
    fun insertStudent(student: Student)
    {
        var db:SQLiteDatabase = this.writableDatabase
        var values:ContentValues = ContentValues()
        values.put(Keys.SID,student.sid)
        values.put(Keys.NAME,student.name)
        values.put(Keys.DEPARTMENT,student.department)
        values.put(Keys.EMAIL,student.email)
        values.put(Keys.USERNAME,student.username)
        values.put(Keys.PASSWORD,student.password)
        db.insert(Keys.STUDENTS_TABLE,null,values)
        Toast.makeText(context, "Student inserted ${student.name} Sucessfully ", Toast.LENGTH_SHORT).show()
    }
    fun getstudents():ArrayList<Student>
    {
        var studentList = ArrayList<Student>()
        var db = this.writableDatabase
        var cursor: Cursor = db.rawQuery("Select * from "+Keys.STUDENTS_TABLE,null,null)
        if(cursor!=null)
            cursor.moveToFirst()
        do{
            var student = Student()
            student.sid=cursor.getString(0)
            student.name=cursor.getString(1)
            student.department=cursor.getString(2)
            student.email=cursor.getString(3)
            student.username=cursor.getString(4)
            student.password=cursor.getString(5)
            studentList.add(student)
        }
            while(cursor.moveToNext())
            return studentList
    }
    fun updateStudent(student:Student,sid:String)
    {
        var db:SQLiteDatabase = this.writableDatabase
        var values:ContentValues = ContentValues()
        values.put(Keys.NAME,student.name)
        values.put(Keys.DEPARTMENT,student.department)
        values.put(Keys.EMAIL,student.email)
        values.put(Keys.USERNAME,student.username)
        db.update(Keys.STUDENTS_TABLE,values,Keys.SID+"=?", arrayOf(sid))
        Toast.makeText(context, "Student data updated Sucessfully", Toast.LENGTH_SHORT).show()
    }
    fun getCount():Int
    {
        var db:SQLiteDatabase = this.writableDatabase
        var cursor:Cursor = db.rawQuery("Select * from "+Keys.STUDENTS_TABLE,null,null)
        if(cursor!=null)
            cursor.moveToFirst()
        return cursor.count
    }
    fun deleteStudent(sid:String)
    {
        var db:SQLiteDatabase = this.writableDatabase
        db.delete(Keys.STUDENTS_TABLE,Keys.SID+"=?", arrayOf(sid))
        Toast.makeText(context, "Student Deleted Sucessfully", Toast.LENGTH_SHORT).show()
    }
    fun getName(sid:String):String
    {
        var db:SQLiteDatabase = this.writableDatabase
        var cursor:Cursor = db.query(Keys.STUDENTS_TABLE, arrayOf<String>(Keys.SID,Keys.NAME,Keys.DEPARTMENT,Keys.EMAIL,Keys.USERNAME,Keys.PASSWORD),Keys.SID+"=?",
            arrayOf<String>(sid),null,null,null)
        if(cursor!=null)
            cursor.moveToFirst()
        return cursor.getString(1)
    }
    fun login(sid:String,password:String):Boolean
    {
        var db:SQLiteDatabase = this.writableDatabase
        var cursor:Cursor = db.query(Keys.STUDENTS_TABLE, arrayOf<String>(Keys.SID,Keys.NAME,Keys.DEPARTMENT,Keys.EMAIL,Keys.USERNAME,Keys.PASSWORD),
            Keys.SID+"=?"+" and "+Keys.PASSWORD+"=?", arrayOf<String>(sid,password),null,null,null)
        if((cursor!=null)&&(cursor.count!=0)&&(cursor.moveToNext()))
            return true
        else
            return false
    }

}