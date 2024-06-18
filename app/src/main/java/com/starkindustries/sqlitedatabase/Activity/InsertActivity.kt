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
import com.starkindustries.sqlitedatabase.databinding.ActivityInsertBinding
class InsertActivity : AppCompatActivity() {
    lateinit var binding:ActivityInsertBinding
    lateinit var dbHandler:StudentDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_insert)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_insert)
        dbHandler= StudentDatabase(applicationContext, Keys.DATABASE_NAME,Keys.VERSION)
        binding.insertButton.setOnClickListener()
        {
            val view = this.currentFocus
            if (view != null) {
                var manager:InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(view.getWindowToken(),0)
            }
            var student = Student(binding.sid.text.toString().trim(),binding.name.text.toString()
                .trim(),binding.department.text.toString().trim(),binding.email.text.toString().trim(),binding.username.text.toString().trim(),
                binding.password.text.toString().trim())
            dbHandler.insertStudent(student)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}