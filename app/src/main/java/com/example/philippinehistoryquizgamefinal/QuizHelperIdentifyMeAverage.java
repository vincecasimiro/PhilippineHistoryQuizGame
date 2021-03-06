package com.example.philippinehistoryquizgamefinal;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizHelperIdentifyMeAverage extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "IdentifyMeAverage";
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
    public QuizHelperIdentifyMeAverage(Context context) {
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
        Question q1 = new Question("I am an American General who surrendered my troops at the Fall of Bataan.\n" +
                "Who am I?", "Gen. Douglas MacArthur", "Gen. George M. Parker", "Gen. Edward King Jr.",
                "General Edward King Jr."); //Answer: C
        this.addQuestion(q1);
        Question q2 = new Question("I am a former President of the United States who supported the Tydings-Mcduffie Act of 1934.\n" +
                "Who am I?", "William Howard Taft", "Franklin D. Roosevelt", "George Washington",
                "Franklin D. Roosevelt"); //Answer: B
        this.addQuestion(q2);
        Question q3 = new Question("I am the last general of the Filipino-American revolution to surrender to the Americans.\n" +
                "Who am I?", "Simeon Ola", "Artemio Ricarte", "Miguel Malvar",
                "Simeon Ola"); //Answer: A
        this.addQuestion(q3);
        Question q4 = new Question("I am the childhood sweetheart of Jose Rizal.\n" +
                "Who am I?", "Marina Dizon", "Segunda Katigbak", "Gregoria de Jesus",
                "Segunda Katigbak"); //Answer: B
        this.addQuestion(q4);
        Question q5 = new Question("I am a former President of the USA who appointed Gen. MacArthur to rule and govern the Philippines.\n" +
                "Who am I?", "George Washington", "Dwight D. Eisenhower", "William Howard Taft",
                "William Howard Taft"); //Answer: C
        this.addQuestion(q5);
        Question q6 = new Question("I am the one who wrote the Kartilya that includes the fundamental teachings of the Katipunan.\n" +
                "Who am I?", "Emilio Jacinto", "Gregorio del Pilar", "Apolinario Mabini",
                "Emilio Jacinto"); //Answer: A
        this.addQuestion(q6);
        Question q7 = new Question("I am the first Chief Justice that was removed from office by an impeachment case.\n" +
                "Who am I?", "Hilario Davide", "Ma. Lourdes Sereno", "Renato Corona",
                "Renato Corona"); //Answer: C
        this.addQuestion(q7);
        Question q8 = new Question("I am the composer of the Philippine National Anthem, Lupang Hinirang.\n" +
                "Who am I?", "Ador De Leon", "Julian Felipe", "Jose Palma",
                "Julian Felipe"); //Answer: B
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
