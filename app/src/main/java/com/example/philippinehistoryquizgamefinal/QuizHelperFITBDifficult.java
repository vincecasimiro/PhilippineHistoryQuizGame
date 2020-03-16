package com.example.philippinehistoryquizgamefinal;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizHelperFITBDifficult extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "FITBDifficult";
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
    public QuizHelperFITBDifficult(Context context) {
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
        Question q1 = new Question("The country's first local newspaper, _______ is established.", "El Filipino", "El Liga Filipina", "El Ilocano",
                "El Ilocano"); //Answer: C
        this.addQuestion(q1);
        Question q2 = new Question("The First Republic in the Philippines is known as _______ where Aguinaldo is the first President of the Philippines.", "The Malolos Republic", "The Kawit Republic", "The Manila Republic",
                "The Malolos Republic"); //Answer: A
        this.addQuestion(q2);
        Question q3 = new Question("The Philippine Flag was first flown in battle on May 28, ____.", "1894", "1898", "1902",
                "1898"); //Answer: B
        this.addQuestion(q3);
        Question q4 = new Question("The ______ was a gendarmerie-type police force of the Philippines that was created to replace Guardia Civil.", "Philippine Troops", "Philippine Constabulary", "Philippine Police Force",
                "Philippine Constabulary"); //Answer: B
        this.addQuestion(q4);
        Question q5 = new Question("The _____ Massacre is famous for the Americans' worst single defeat.", "Balangiga", "Cabanatuan", "Bataan",
                "Balangiga"); //Answer: A
        this.addQuestion(q5);
        Question q6 = new Question("_____ establishes a second Tagalog Republic on May 2, 1902.", "Miguel Malvar", "Simeon Ola", "Macario Sakay",
                "Macario Sakay"); //Answer: C
        this.addQuestion(q6);
        Question q7 = new Question("The _______ is passed establishing an all-Filipino legislature.", "Jones Act", "Tydings-Mcduffie Act", "Treaty of Paris",
                "Jones Act"); //Answer: A
        this.addQuestion(q7);
        Question q8 = new Question("The _______ is known as the Philippine Independence Law.", "Hare-Hawes-Cutting Act", "Tydings-Mcduffie Act", "Philippine Organic Act",
                "Tydings-Mcduffie Act"); //Answer: B
        this.addQuestion(q8);
        Question q9 = new Question("The National Defense Act of 1935 was signed that creates ________.", "Armed Forces of the Philippines", "Philippine National Police", "Philippine Air Force",
                "Armed Forces of the Philippines"); //Answer: A
        this.addQuestion(q9);
        Question q10 = new Question("The _______ is considered to have been the largest naval battle of World War II.", "Battle of Corregidor", "Battle of Leyte Gulf", "Battle of Manila",
                "Battle of Leyte Gulf"); //Answer: B
        this.addQuestion(q10);
        Question q11 = new Question("General MacArthur announced the liberation of the Philippines on ______.", "July 3, 1945", "July 4, 1945", "July 5, 1945",
                "July 5, 1945"); //Answer: C
        this.addQuestion(q11);
        Question q12 = new Question("_____ is the last Japanese soldier to come out of hiding and surrender, almost 30 years after the end of the Second World War.", "Hiroo Onoda", "Tomoyuki Yamashita", "Shigenori Kuroda",
                "Hiroo Onoda"); //Answer: A
        this.addQuestion(q12);
        Question q13 = new Question("The newly-formed _______ is declared capital of the Philippines by Pres. Quirino (Republic Act No.333) in July 1948.", "Makati City", "Quezon City", "Manila City",
                "Quezon City"); //Answer: B
        this.addQuestion(q13);
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
