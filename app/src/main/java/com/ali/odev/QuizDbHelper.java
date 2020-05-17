package com.ali.odev;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ali.odev.QuizContract.QuestionTable;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {

   private static final String DATABASE_NAME = "GoQuiz.db";
   private static final int DATBASE_VERSION = 7;

    private SQLiteDatabase db;


    QuizDbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATBASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);

    }


    private void fillQuestionsTable()
    {

        Questions q1 = new Questions("Android nedir ?","İşletim sistemi","Sürücü","Yazılım","Donanım",1);
        addQuestions(q1);

        Questions q2 = new Questions("İbrahim hoca 2013'te ne kordinatörü oldu ?","Mevlana","Yunus emre","Celaleddin rumi","Hazreti Ali",1);
        addQuestions(q2);

        Questions q3 = new Questions("HTML nedir ?","Metin İşaretleme Dili","Programlama dili","Veri tabanı yönetim sistemi","hiçbirisi",1);
        addQuestions(q3);

        Questions q4 = new Questions("Betül Bayrak Yüksek lisansını nerede yapmıştır? ?","Gazi Üniversitesi","Çankaya üniversitesi","Odtü","Anadolu Üniversitesi",2);
        addQuestions(q4);

        Questions q6 = new Questions("Merve hocanın soyadı nedir?","Papatyalı","Sümbüllü","Güllü","Zambaklı",3);
        addQuestions(q6);

        Questions q7 = new Questions("Dünyanın en uzun dağı hangisidir?","Ağrı dağı","Chin Su toi dağı","Kamutomu dağı","Everest dağı",4);
        addQuestions(q7);

        Questions q8 = new Questions("Kediler nasıl beslenir ?","Etçil","Otçul","Hem etçil hem otçul","Fotosentez",1);
        addQuestions(q8);

        Questions q9 = new Questions("Aslanlar nerde yaşar ?","Avusturalya","Güney Amerika","Kuzey Amerika","Afrika",4);
        addQuestions(q9);

        Questions q10 = new Questions("nüfusu en yüksek 2.ülke hangisidir","Hindistan","Arabistan","Çin","Amerika",1);
        addQuestions(q10);

        Questions q11 = new Questions("Osmanlının ilk başkenti neresidir ?","istanbul","Bursa","Ankara","Bolu",2);
        addQuestions(q11);

        Questions q12 = new Questions("İstanbulu kaçıncı mehmed fethetmiştir?","1.mehmed","2.mehmed","kanuni sultan süleyman","4.murat",2);
        addQuestions(q12);

        Questions q13 = new Questions("Betül Hocanın soyadı nedir ?","Albayrak","Bayrak","Albayram","Bayram",2);
        addQuestions(q13);

        Questions q14 = new Questions("günümüz insanlarının bilimsel ismi nedir ?","homo erectus","homo neandarthalensis","homo sapiens","homo habilis",3);
        addQuestions(q14);

    }

    private void addQuestions(Questions question){

        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        db.insert(QuestionTable.TABLE_NAME,null,cv);

    }

    public ArrayList<Questions> getAllQuestions() {

        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();



        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR
        };



            Cursor c = db.query(QuestionTable.TABLE_NAME,
                    Projection,
                    null,
                    null,
                    null,
                    null,
                    null);


            if (c.moveToFirst()) {
                do {

                    Questions question = new Questions();
                    question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                    question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                    question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                    question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                    question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                    question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));

                    questionList.add(question);

                } while (c.moveToNext());

            }
            c.close();
            return questionList;

        }

    }


