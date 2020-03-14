package com.example.philippinehistoryquizgamefinal;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizHelperIdentifyMeDifficult extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "IdentifyMeDifficult";
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
    public QuizHelperIdentifyMeDifficult(Context context) {
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
        Question q1 = new Question("I am the one who declared Manila as an open city to avoid the destruction during the Japanese occupation.\n" +
                "Who am I?", "Gen. Douglas MacArthur", "Gen. Edward King Jr.", "Gen. George M. Parker",
                "Gen. Douglas MacArthur"); //Answer: A
        this.addQuestion(q1);
        Question q2 = new Question("When the Japanese invaded the country, we cooperated with the Japanese troops. What are we called?", "MAKABAGO", "MAKAHAPON", "MAKAPILI",
                "MAKAPILI"); //Answer: C
        this.addQuestion(q2);
        Question q3 = new Question("I am the first woman to top the Philippine Bar Examination. Who am I?", "Tina Munoz Palma", "Tecla San Andres Ziga", "Josefa Diokno",
                "1896"); //Answer: B
        this.addQuestion(q3);
        Question q4 = new Question("I am the Vice President of Manuel L. Quezon when he was inaugurated as the second President of the Philippines\n" +
                "Who am I?", "Jose P. Laurel", "Sergio Osmena", "Jose Abad Santos",
                "Sergio Osmena"); //Answer: B
        this.addQuestion(q4);
        Question q5 = new Question("I am appointed as the Supreme Court Chief Justice of Manuel L. Quezon when he was inaugurated as the second President of the Philippines\n" +
                "Who am I?", "Jose Abad Santos", "Jose P. Laurel", "Carlos P. Garcia",
                "Jose Abad Santos"); //Answer: A
        this.addQuestion(q5);
        Question q6 = new Question("I am a Filipino soldier who led the Cavite Mutiny of 1872. Who am I?", "Gen. Mariano Noriel", "Lt. Taviel de Andrade", "Sergeant Lamadrid",
                "Sergeant Lamadrid"); //Answer: C
        this.addQuestion(q6);
        Question q7 = new Question("I am a record holder for having the highest average in the Philippine Bar Examinations. Who am I?", "Ma. Lourdes Sereno", "Florenz Regalado", "Cayetano Arellano",
                "Florenz Regalado"); //Answer: B
        this.addQuestion(q7);
        Question q8 = new Question("I am the first \"First Lady of the Philippines\". Who am I?", "Veronica Duterte", "Victoria \"Vicky\" Syquia Quirino-Gonzalez", "Imelda Marcos",
                "Imelda Marcos"); //Answer: C
        this.addQuestion(q8);
        Question q9 = new Question("In my tenure as the President of the Philippines, hunger was my main worry. Who am I?", "Jose P. Laurel", "Manuel Roxas", "Manuel Quezon",
                "Jose P. Laurel"); //Answer: A
        this.addQuestion(q9);
        Question q10 = new Question("I am a Filipino revolutionary who led the Philippine-American war. Who am I?", "Jose Alejandrino", "Emilio Aguinaldo", "Jose Rizal",
                "Emilio Aguinaldo"); //Answer: B
        this.addQuestion(q10);
        Question q11 = new Question("I am the 5th President of the Philippines but briefly served as the third and last President of the Commonwealth. Who am I?", "Manuel Quezon", "Jose P. Laurel", "Manuel Roxas",
                "Manuel Roxas"); //Answer: C
        this.addQuestion(q11);
        Question q12 = new Question("The Philippines was named in honor after me. Who am I?", "King Philip II of Spain", "Queen Elizabeth", "King Philip I of Spain",
                "King Philip II of Spain"); //Answer: A
        this.addQuestion(q12);
        Question q13 = new Question("I am the first editor of La Solidaridad. I am known in my pseudonym as Plaridel. Who am I?", "Graciano Lopez Jaena", "Marcelo H. Del Pilar", "Emilio Aguinaldo",
                "Marcelo H. Del Pilar"); //Answer: B
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
