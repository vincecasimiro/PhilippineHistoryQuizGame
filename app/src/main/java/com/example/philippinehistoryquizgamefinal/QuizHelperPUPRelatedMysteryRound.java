package com.example.philippinehistoryquizgamefinal;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizHelperPUPRelatedMysteryRound extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "PUPRelatedMysteryRound";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
    // tasks Table Columns names
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; // correct option
    private static final String KEY_OPTA = "opta"; // option a
    private static final String KEY_OPTB = "optb"; // option b
    private static final String KEY_OPTC = "optc"; // option c
    private SQLiteDatabase dbase;
    public QuizHelperPUPRelatedMysteryRound(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        addQuestion();
// db.close();
    }
    private void addQuestion() {
        Question q1 = new Question("Polytechnic University of the Philippines was founded in what year?", "1904", "1906", "1908",
                "1904"); //Answer: A
        this.addQuestion(q1);
        Question q2 = new Question("How many campuses does PUP have?", "17", "19", "21",
                "21"); //Answer:C
        this.addQuestion(q2);
        Question q3 = new Question("She is the First Transwoman Valedictorian of the Polytechnic University of the Philippines.", "Ianne Gamboa", "Gretchen Diez", "Jess Labares",
                "Ianne Gamboa"); //Answer: A
        this.addQuestion(q3);
        Question q4 = new Question("She is the current Dean of the College of Engineering.", "Engr. Florinda Oquindo", "Dr. Remedios Ado", "Dr. Zenaida Sarmiento",
                "Dr. Remedios Ado"); //Answer: B
        this.addQuestion(q4);
        Question q5 = new Question("What is the hashtag for the Mr. and Ms. PUP Engineering when it was canceled in 2017 due to EJK-themed photo shoot?", "#EmbraceYourFlaws", "#StepOutoftheDark", "#DisplayYourself",
                "#EmbraceYourFlaws"); //Answer: A
        this.addQuestion(q5);
        Question q6 = new Question("From the year 2015 to 2020, the Computer Engineering Department has garnered _ championship trophies in the College of Engineering Week.", "4", "2", "3",
                "3"); //Answer: C
        this.addQuestion(q6);
        Question q7 = new Question("FEWA is a delicious food and considered as PUPian's favorite. What does FEWA mean?", "Footlong with Egg Wrapped Around", "Fish with Egg Wrapped Around", "Fried Egg Wrapped Around",
                "Footlong with Egg Wrapped Around"); //Answer: A
        this.addQuestion(q7);
        Question q8 = new Question("The Vivo Campus Invasion was an event held at the PUP Gymnasium in 2017, but later on, got canceled. What is the reason?", "The organizers felt tired", "Stampede at the entrance", "A fire broke out",
                "Stampede at the entrance"); //Answer: B
        this.addQuestion(q8);
        Question q9 = new Question("The PUP Pylon symbolizes what?", "Reputation, Peace, Order", "Strength, Perseverance, Respect", "Truth, Excellence, Wisdom",
                "Truth, Excellence, Wisdom"); //Answer: C
        this.addQuestion(q9);
        Question q10 = new Question("He is the current President of the Polytechnic University of the Philippines.", "Dr. Emanuel C. de Guzman", "Dr. Manuel M. Muhi", "Dr. Nemesio E. Prudente",
                "Dr. Manuel M. Muhi"); //Answer: B
        this.addQuestion(q10);
        Question q11 = new Question("The students, faculty, staff, and alumni broke this world record in the centennial year of PUP. What is the record?", "World's Biggest University", "World's Oldest University", "World's Largest Human Rainbow",
                "World's Largest Human Rainbow"); //Answer: C
        this.addQuestion(q11);
        Question q12 = new Question("The Pylon Run is a famous event held annually during the university's founding anniversary. What is Pylon Run?", "Male members running naked", "Male members running with a Pylon costume", "Male members running towards the Pylon",
                "Male members running naked"); //Answer: A
        this.addQuestion(q12);
// END
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
// Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(Question quest) {
// SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
// Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_QUEST;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
// return quest list
        return quesList;
    }
}
