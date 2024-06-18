package com.starkindustries.sqlitedatabase.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.starkindustries.sqlitedatabase.DataBase.StudentDatabase
import com.starkindustries.sqlitedatabase.Keys.Keys
import com.starkindustries.sqlitedatabase.Model.Student
import com.starkindustries.sqlitedatabase.R
import com.starkindustries.sqlitedatabase.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    lateinit var binding:ActivityUpdateBinding
    lateinit var dbHandler:StudentDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_update)
        dbHandler=StudentDatabase(applicationContext, Keys.DATABASE_NAME,Keys.VERSION)
        dbHandler.getCount()
        binding.updateButton.setOnClickListener()
        {
            val view = this.currentFocus
            if(view!=null)
            {
                var manager : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(view.windowToken,0)
            }
            var student: Student = Student()
            student.name=binding.updateName.text.toString().trim()
            student.department=binding.updateDepartment.text.toString().trim()
            student.email=binding.updateEmail.text.toString().trim()
            student.username=binding.updateUsername.text.toString().trim()
            dbHandler.updateStudent(student,binding.updateSid.text.toString().trim())
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}