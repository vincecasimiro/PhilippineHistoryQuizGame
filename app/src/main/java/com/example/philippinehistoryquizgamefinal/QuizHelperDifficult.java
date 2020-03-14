package com.example.philippinehistoryquizgamefinal;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizHelperDifficult extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "QuizBeeDifficult";
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
    public QuizHelperDifficult(Context context) {
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
        Question q1 = new Question("The date when the Philippines won over the arbitration case regarding the West Philippine Sea on the Permanent Court of Arbitration against China.", "July 11, 2016", "July 14, 2016", "July 12, 2016",
                "July 12, 2016"); //Answer: C
        this.addQuestion(q1);
        Question q2 = new Question("After the death of her husband Andres Bonifacio, Gregoria de Jesus was later married to whom?", "Julio Nakpil", "Felipe Calderon", "Francisco Trinidad",
                "Julio Nakpil"); //Answer: A
        this.addQuestion(q2);
        Question q3 = new Question("When did the Battle of Pinaglabanan happened?", "1894", "1896", "1898",
                "1896"); //Answer: B
        this.addQuestion(q3);
        Question q4 = new Question("What is the mission of Battle of Pinaglabanan?", "To overthrow the Spaniards", "To seize the reservoir and gunpowder depot", "To find a new hideout",
                "To seize the reservoir and gunpowder depot"); //Answer: B
        this.addQuestion(q4);
        Question q5 = new Question("The Plaza Miranda Bombing happened in what year?", "1971", "1963", "1969",
                "1971"); //Answer: A
        this.addQuestion(q5);
        Question q6 = new Question("What was the name of the Filipino soldier who led the Cavite Mutiny of 1872?", "Gen. Mariano Noriel", "Lt. Taviel de Andrade", "Sergeant Lamadrid",
                "Sergeant Lamadrid"); //Answer: C
        this.addQuestion(q6);
        Question q7 = new Question("The newspaper established by Marcelo H. del Pilar which served as a propaganda material for the reformists.", "Diariong Tagalog", "La Solidaridad", "La Liga Filipina",
                "Diariong Tagalog"); //Answer: A
        this.addQuestion(q7);
        Question q8 = new Question("Who is the first Chief Justice who was removed from office by an impeachment case?", "Cayetano Arellano", "Renato Corona", "Ma. Lourdes Sereno",
                "Renato Corona"); //Answer: B
        this.addQuestion(q8);
        Question q9 = new Question("The newspaper established by Marcelo H. del Pilar which served as a propaganda material for the reformists.", "Diariong Tagalog", "La Solidaridad", "La Liga Filipina",
                "Diariong Tagalog"); //Answer: A
        this.addQuestion(q9);
        Question q10 = new Question("Who is the first Chief Justice who was removed from office by an impeachment case?", "Cayetano Arellano", "Renato Corona", "Ma. Lourdes Sereno",
                "Renato Corona"); //Answer: B
        this.addQuestion(q10);
        Question q11 = new Question("He exercises all executive, legislative and judicial powers in the small communities during the pre-colonial era.", "Babaylan", "Timawa", "Datu",
                "Datu"); //Answer: C
        this.addQuestion(q11);
        Question q12 = new Question("Written on this famous work by Emilio Jacinto are the fundamental teachings of the Katipunan.", "Kartilya", "La Esperanza", "La Solidaridad",
                "Kartilya"); //Answer: A
        this.addQuestion(q12);
        Question q13 = new Question("Who was called \"The First Filipino Diplomat\" ?", "Miguel Cuaderno", "Felipe Agoncillo", "Robert Cortez",
                "Felipe Agoncillo"); //Answer: B
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
