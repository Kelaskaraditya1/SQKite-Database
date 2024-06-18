package com.starkindustries.sqlitedatabase.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.starkindustries.sqlitedatabase.DataBase.StudentDatabase
import com.starkindustries.sqlitedatabase.Keys.Keys
import com.starkindustries.sqlitedatabase.R
import com.starkindustries.sqlitedatabase.databinding.ActivityOperationsScreenBinding
class OperationsScreen : AppCompatActivity() {
    lateinit var binding:ActivityOperationsScreenBinding
    lateinit var dbHandler: StudentDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_operations_screen)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_operations_screen)
        dbHandler=StudentDatabase(applicationContext, Keys.DATABASE_NAME,Keys.VERSION)
        dbHandler.getCount()
        Log.d("nameCheck","The name is:"+dbHandler.getName("2021FHCO042"))
        Log.d("login","The loogin is: ${dbHandler.login("2021FHCO042","Aditya@1234")}")
        binding.insertData.setOnClickListener()
        {
            var inext = Intent(applicationContext,InsertActivity::class.java)
            startActivity(inext)
        }
        binding.getData.setOnClickListener()
        {
            var inext = Intent(this,DisplayActivity::class.java)
            startActivity(inext)
        }
        binding.UpdateData.setOnClickListener()
        {
            var inext:Intent = Intent(this,UpdateActivity::class.java)
            startActivity(inext)
        }
        binding.deleteData.setOnClickListener()
        {
            dbHandler.deleteStudent(binding.sid.text.toString().trim())
            val view = this.currentFocus
            if(view!=null)
            {
                var manager:InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(view.windowToken,0)
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}