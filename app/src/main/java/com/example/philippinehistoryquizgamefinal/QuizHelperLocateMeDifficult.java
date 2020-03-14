package com.example.philippinehistoryquizgamefinal;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizHelperLocateMeDifficult extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "LocateMeDifficult";
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
    public QuizHelperLocateMeDifficult(Context context) {
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
        Question q1 = new Question("Where is Jose Rizal University located?", "Greenhills", "Shaw Boulevard", "Timog, Quezon City",
                "Shaw Boulevard"); //Answer: A
        this.addQuestion(q1);
        Question q2 = new Question("The first bank of the Philippines (Bank of the Philippine Islands) can be found where?", "Paco, Manila", "Sta. Mesa, Manila", "Binondo, Manila",
                "Binondo, Manila"); //Answer: C
        this.addQuestion(q2);
        Question q3 = new Question("Ang Aklatang Pambansa ng Pilipinas can be found where?", "Quiapo, Manila", "Ermita, Manila", "Intramuros, Manila",
                "Ermita, Manila"); //Answer: B
        this.addQuestion(q3);
        Question q4 = new Question("The oldest stone church built in the Philippines, Chruch of San Agustin, can be found where?", "Tondo, Manila", "Intramuros, Manila", "Malabon City",
                "Intramuros, Manila"); //Answer: B
        this.addQuestion(q4);
        Question q5 = new Question("To go to the Cultural Center of the Philippines, what station will I alight when I ride the LRT1?", "Vito Cruz", "Pedro Gil", "Central Terminal",
                "Vito Cruz"); //Answer: A
        this.addQuestion(q5);
        Question q6 = new Question("Where can you find the wholesale flower market in Manila?", "Blumentritt", "Carriedo", "Tayuman",
                "Tayuman"); //Answer: C
        this.addQuestion(q6);
        Question q7 = new Question("Where can you find the Malacanang Palace which houses the President of the Philippines?", "Doroteo Jose Station", "Legarda Station", "Gilmore Station",
                "Legarda Station"); //Answer: B
        this.addQuestion(q7);
        Question q8 = new Question("The Pinaglabanan Shrine can be found where?", "Betty-Go Belmonte Station", "Gilmore Station", "J. Ruiz Station",
                "Imelda Marcos"); //Answer: C
        this.addQuestion(q8);
        Question q9 = new Question("Where can you find the ABS-CBN Network Station when you ride the MRT?", "Quezon Avenue", "Santolan-Annapolis", "Guadalupe",
                "Quezon Avenue"); //Answer: A
        this.addQuestion(q9);
        Question q10 = new Question("Where can you find the People Power Monument?", "Makati City", "Quezon City", "Muntinlupa City",
                "Quezon City"); //Answer: B
        this.addQuestion(q10);
        Question q11 = new Question("The Oakwood Mutiny led by Sen. Antonio Trillanes IV happened where?", "SM North Edsa", "TriNoMa", "Glorietta",
                "Glorietta"); //Answer: C
        this.addQuestion(q11);
        Question q12 = new Question("Where is the blood compact or Sanduguan between Magellan and Rajah Kulambo happened?", "Limasawa", "Mactan", "Iloilo",
                "Limasawa"); //Answer: A
        this.addQuestion(q12);
        Question q13 = new Question("The USS Stingray Memorial can be found in what region?", "Region V", "Region I", "Region IX",
                "Region IS"); //Answer: B
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
