package com.example.philippinehistoryquizgamefinal;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizHelperIdentifyMe extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "IdentifyMeEasy";
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
    public QuizHelperIdentifyMe(Context context) {
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
        Question q1 = new Question("I am known in my pen name as Laong Laan. I was exiled in Dapitan and killed in Bagumbayan.\n" +
                "Who am I?", "Jose Rizal", "Marcelo H. Del Pilar", "Andres Bonifacio",
                "Jose Rizal"); //Answer: A
        this.addQuestion(q1);
        Question q2 = new Question("I am The Father of the Philippine Revolution, one of the founders and later, become the Supremo of the Katipunan.\n" +
                "Who am I?", "Graciano Lopez Jaena", "Emilio Aguinaldo", "Andres Bonifacio",
                "Andres Bonifacio"); //Answer:C
        this.addQuestion(q2);
        Question q3 = new Question("I am the writer of the Spanish poem Filipinas, from which Lupang Hinirang was adapted from.\n" +
                "Who am I?", "Julian Felipe", "Jose Palma", "Julio Nakpil",
                "Jose Palma"); //Answer: B
        this.addQuestion(q3);
        Question q4 = new Question("I am the chief advisor of Gen. Emilio Aguinaldo. I am known as the Sublime Paralytic.\n" +
                "Who am I?", "Felipe Calderon", "Apolinario Mabini", "Pedro Paterno",
                "Apolinario Mabini"); //Answer: B
        this.addQuestion(q4);
        Question q5 = new Question("I exercise all executive, legislative and judicial powers in the small communities during the pre-colonial era.\n" +
                "Who am I?", "Datu", "Sagigilid", "Babaylan",
                "Datu"); //Answer: A
        this.addQuestion(q5);
        Question q6 = new Question("I am the president during the Revolutionary Government also known as the Malolos Republic.\n" +
                "Who am I?", "Elpidio Quirino", "Gregorio del Pilar", "Emilio Aguinaldo",
                "Emilio Aguinaldo"); //Answer: C
        this.addQuestion(q6);
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
