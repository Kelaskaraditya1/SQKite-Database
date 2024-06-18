package com.starkindustries.sqlitedatabase.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.starkindustries.sqlitedatabase.Adapters.CustomArrayAdapter
import com.starkindustries.sqlitedatabase.DataBase.StudentDatabase
import com.starkindustries.sqlitedatabase.Keys.Keys
import com.starkindustries.sqlitedatabase.Model.Student
import com.starkindustries.sqlitedatabase.R
import com.starkindustries.sqlitedatabase.databinding.ActivityDisplayBinding
class DisplayActivity : AppCompatActivity() {
    lateinit var binding:ActivityDisplayBinding
    lateinit var studentList:ArrayList<Student>
    lateinit var dbhandler:StudentDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_display)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_display)
        dbhandler= StudentDatabase(applicationContext, Keys.DATABASE_NAME,Keys.VERSION)
        studentList= ArrayList<Student>()
        studentList=dbhandler.getstudents()
        binding.listview.adapter=CustomArrayAdapter(applicationContext,studentList)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}