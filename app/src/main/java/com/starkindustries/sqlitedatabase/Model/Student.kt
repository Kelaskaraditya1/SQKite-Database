package com.starkindustries.sqlitedatabase.Model
class Student
{
    lateinit var sid:String
    lateinit var name:String
    lateinit var department:String
    lateinit var email:String
    lateinit var username:String
    lateinit var password:String
    constructor(sid_:String,  name_:String,  department_:String,  email_:String,  username_:String,  password_:String)
    {
        this.sid=sid_
        this.name=name_
        this.department=department_
        this.email=email_
        this.username=username_
        this.password=password_
    }

    constructor(name_: String,  department_: String,  email_: String,  username_: String,  password_: String)
    {
        this.name=name_
        this.department=department_
        this.email=email_
        this.username=username_
        this.password=password_
    }
    constructor( name_: String, department_: String, email_: String,username_: String)
    {
        this.name=name_
        this.department=department_
        this.email=email_
        this.username=username_
    }
    constructor()
    {}
}