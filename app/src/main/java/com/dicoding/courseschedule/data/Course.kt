package com.dicoding.courseschedule.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dicoding.courseschedule.data.DataCourseName.COL_COURSE_NAME
import com.dicoding.courseschedule.data.DataCourseName.COL_DAY
import com.dicoding.courseschedule.data.DataCourseName.COL_END_TIME
import com.dicoding.courseschedule.data.DataCourseName.COL_ID
import com.dicoding.courseschedule.data.DataCourseName.COL_LECTURER
import com.dicoding.courseschedule.data.DataCourseName.COL_NOTE
import com.dicoding.courseschedule.data.DataCourseName.COL_START_TIME
import com.dicoding.courseschedule.data.DataCourseName.TABLE_NAME

//TODO 1 : Define a local database table using the schema in app/schema/course.json
@Entity(tableName = TABLE_NAME)
data class Course(

    @field:ColumnInfo(name = COL_ID)
    @field:PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @field:ColumnInfo(name = COL_COURSE_NAME)
    val courseName: String,

    @field:ColumnInfo(name = COL_DAY)
    val day: Int,

    @field:ColumnInfo(name = COL_START_TIME)
    val startTime: String,

    @field:ColumnInfo(name = COL_END_TIME)
    val endTime: String,

    @field:ColumnInfo(name = COL_LECTURER)
    val lecturer: String,

    @field:ColumnInfo(name = COL_NOTE)
    val note: String

)
