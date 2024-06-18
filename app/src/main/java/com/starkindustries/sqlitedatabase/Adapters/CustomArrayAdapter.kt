package com.starkindustries.sqlitedatabase.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatTextView
import com.starkindustries.sqlitedatabase.Model.Student
import com.starkindustries.sqlitedatabase.R
class CustomArrayAdapter(var context_: Context,var studentList_:ArrayList<Student>):ArrayAdapter<Student>(context_,0,studentList_)
{
    lateinit var name:AppCompatTextView
    lateinit var sid:AppCompatTextView
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var view : View = LayoutInflater.from(context_).inflate(R.layout.customrow,parent,false)
        name=view.findViewById(R.id.name)
        sid=view.findViewById(R.id.sid)
        name.setText(studentList_.get(position).name)
        sid.setText(studentList_.get(position).sid)
        return view
    }
}