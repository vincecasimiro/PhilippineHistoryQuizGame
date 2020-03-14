package com.example.philippinehistoryquizgamefinal;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizHelperLocateMeAverage extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "LocateMeAverage";
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
    public QuizHelperLocateMeAverage(Context context) {
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
        Question q1 = new Question("This is where the First Shot in the Filipino-American war happened.", "Pandacan, Manila", "Malabon City", "Sta. Mesa, Manila",
                "Sta. Mesa, Manila"); //Answer: C
        this.addQuestion(q1);
        Question q2 = new Question("It is where the La Liga Filipina was established.", "Mandaluyong City", "Tondo, Manila", "Sta. Cruz, Manila",
                "Tondo, Manila"); //Answer: B
        this.addQuestion(q2);
        Question q3 = new Question("New Bilibid Prison houses the prison population of the Philippines. Where is it located?", "Muntinlupa City", "Pasay City", "Navotas City",
                "Muntinlupa City"); //Answer: A
        this.addQuestion(q3);
        Question q4 = new Question("The monument commemorating the Cry of Pugad Lawin can be found where?", "FEU Manila", "UP Diliman", "San Beda College",
                "UP Diliman"); //Answer: B
        this.addQuestion(q4);
        Question q5 = new Question("Ang Dambana ni Melchora Aquino can be found where in Quezon City?", "Loyola Heights, Quezon City", "Novaliches, Quezon City", "Tandang Sora, Quezon City",
                "Tandang Sora, Quezon City"); //Answer: C
        this.addQuestion(q5);
        Question q6 = new Question("The Quezon National Memorial Shrine can be found where?", "Quezon City", "Pasay City", "Marikina City",
                "Quezon City"); //Answer: A
        this.addQuestion(q6);
        Question q7 = new Question("The control tower of the first airport of the Philippines, built in 1937, can be found where?", "Clark International Airport", "Manila International Airport", "Nielson Tower",
                "Nielson Tower"); //Answer: C
        this.addQuestion(q7);
        Question q8 = new Question("This is the last destination of the Filipino and American soldiers in the Bataan Death March.", "Bulacan", "Tarlac", "Ilocos Norte",
                "Tarlac"); //Answer: B
        this.addQuestion(q8);
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
