package com.example.philippinehistoryquizgamefinal;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizHelperAverage extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "QuizBeeAverage";
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
    public QuizHelperAverage(Context context) {
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
        Question q1 = new Question("Who was the last general of the Filipino-American Revolution to surrender to the Americans?", "Simeon Ola", "Miguel Malvar", "Artemio Ricarte",
                "Simeon Ola"); //Answer: A
        this.addQuestion(q1);
        Question q2 = new Question("Who led the longest revolt in the Philippines during the Spanish times?", "Palaris", "Francisco Dagohoy", "Rajah Sulayman",
                "Francisco Dagohoy"); //Answer: B
        this.addQuestion(q2);
        Question q3 = new Question("Who named the country 'Islas de San Lazaro'?", "Miguel Lopez de Legazpi", "Alvaro Saavedra", "Ferdinand Magellan",
                "Ferdinand Magellan"); //Answer: C
        this.addQuestion(q3);
        Question q4 = new Question("He founded the Socialist Party of the Philippines in 1929, which merged with the\n" +
                "older Communist Party of the Philippines (PKP) in 1939.", "Casto Alejandrino", "Pedro Abad Santos", "Luis Taruc",
                "Pedro Abad Santos"); //Answer: B
        this.addQuestion(q4);
        Question q5 = new Question("The president who passed RA 6657 or the Comprehensive Agrarian Reform Law.", "Corazon C. Aquino", "Fidel V. Ramos", "Ferdinand Marcos",
                "Corazon C. Aquino"); //Answer: A
        this.addQuestion(q5);
        Question q6 = new Question("Who was the last Sultan of Sulu?", "Abu Bakr", "Haji Butu", "Jamalul Kiram II",
                "Jamalul Kiram II"); //Answer: C
        this.addQuestion(q6);
        Question q7 = new Question("He is an American general who surrendered his approximately 75,000 troops at the Fall of Bataan.", "General Edward King Jr.", "General Douglas MacArthur", "General George M. Parker",
                "General Edward King Jr."); //Answer: A
        this.addQuestion(q7);
        Question q8 = new Question("It was the Philippine Army contingent of the United Nations forces that fought in the Korean War in 1950–1953.", "Philippine Executive Forces to Korea", "Philippine Expeditionary Forces to Korea", "Philippine Special Forces to Korea",
                "Philippine Expeditionary Forces to Korea"); //Answer: B
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
