package com.dicoding.courseschedule.ui.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.ui.ViewModelFactory
import com.dicoding.courseschedule.util.DayName
import com.dicoding.courseschedule.util.TimePickerFragment
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class AddCourseActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

    private lateinit var addCourseViewModel: AddCourseViewModel

    private lateinit var tetCourseName: TextInputEditText
    private lateinit var spinnerDay: Spinner
    private lateinit var tvStartTimeContent: TextView
    private lateinit var tvEndTimeContent: TextView
    private lateinit var tetLecturer: TextInputEditText
    private lateinit var tetNote: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        supportActionBar?.title = getString(R.string.add_course)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tetCourseName = findViewById(R.id.tet_courseName)
        spinnerDay = findViewById(R.id.spinner_day)
        tvStartTimeContent = findViewById(R.id.tv_startTimeContent)
        tvEndTimeContent = findViewById(R.id.tv_endTimeContent)
        tetLecturer = findViewById(R.id.tet_lecturer)
        tetNote = findViewById(R.id.tet_note)

        val factory = ViewModelFactory.getInstance(this)
        addCourseViewModel = ViewModelProvider(this, factory).get(AddCourseViewModel::class.java)

        addCourseViewModel.saved.observe(this){
            tetCourseName.text?.clear()
            spinnerDay.setSelection(0)
            tvStartTimeContent.text = "08:00"
            tvEndTimeContent.text = "09:00"
            tetLecturer.text?.clear()
            tetNote.text?.clear()

            tetCourseName.clearFocus()
            spinnerDay.clearFocus()
            tetLecturer.clearFocus()
            tetNote.clearFocus()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            android.R.id.home -> {
                finish()
                true
            }

            R.id.action_insert -> {

                val dayInt = when (spinnerDay.selectedItem.toString()){
                    DayName.MONDAY.value -> Calendar.MONDAY
                    DayName.TUESDAY.value -> Calendar.TUESDAY
                    DayName.WEDNESDAY.value -> Calendar.WEDNESDAY
                    DayName.THURSDAY.value -> Calendar.THURSDAY
                    DayName.FRIDAY.value -> Calendar.FRIDAY
                    DayName.SATURDAY.value -> Calendar.SATURDAY
                    DayName.SUNDAY.value -> Calendar.SUNDAY
                    else -> { 0 }
                }

                addCourseViewModel.insertCourse(
                    tetCourseName.text.toString().trim(),
                    dayInt,
                    tvStartTimeContent.text.toString().trim(),
                    tvEndTimeContent.text.toString().trim(),
                    tetLecturer.text.toString().trim(),
                    tetNote.text.toString().trim()
                )

                Toast.makeText(this, "Course successfully added", Toast.LENGTH_SHORT).show()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showTimePickerStartTime(view: View) {
        val dialogFragment = TimePickerFragment()
        dialogFragment.show(supportFragmentManager, "timePickerStartTime")
    }

    fun showTimePickerEndTime(view: View) {
        val dialogFragment = TimePickerFragment()
        dialogFragment.show(supportFragmentManager, "timePickerEndTime")
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)

        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        when (tag){
            "timePickerStartTime" -> tvStartTimeContent.text = dateFormat.format(calendar.time)
            "timePickerEndTime" -> tvEndTimeContent.text = dateFormat.format(calendar.time)
        }

    }

}