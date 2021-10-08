package com.comp2601.Assignment1;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Exam {

    private static final String TAG = Exam.class.getSimpleName();

    public static ArrayList<Question> pullParseFrom(BufferedReader reader, Student student){

        ArrayList<Question> questions = new ArrayList<>(); //for now
        Question question = null;
        String text = null;


        // Get our factory and create a PullParser
        XmlPullParserFactory factory;
        try {
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(reader); // set input file for parser
            int eventType = xpp.getEventType(); // get initial eventType

            int answerCount = 0;
            // Loop through pull events until we reach END_DOCUMENT
            while (eventType != XmlPullParser.END_DOCUMENT) {

                String tagname = xpp.getName();

                // handle the xml tags encountered
                switch (eventType) {

                    case XmlPullParser.START_TAG: //XML opening tags
                        //to do
                        Log.i(TAG, xpp.getName());
                        if (tagname.equalsIgnoreCase(Question.XML_QUESTION)) {
                            question = new Question("",null,Integer.parseInt(xpp.getAttributeValue(null, Question.XML_ATTR_Id)));
                            answerCount = 0;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //to do
                        Log.i(TAG, xpp.getText());
                        text = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG: //XML closing tags
                        Log.i(TAG, xpp.getName());
                        if (tagname.equalsIgnoreCase(Question.XML_QUESTION)) {
                            questions.add(question);
                        }else if (tagname.equalsIgnoreCase(Question.XML_QUESTION_TEXT)) {
                            assert question != null;
                            question.setQuestionString(text);
                        }  else if (tagname.equalsIgnoreCase(Question.XML_ANSWER)) {
                            assert question != null;
                            question.getAnswer()[answerCount] = text;
                            answerCount++;
                        }  else if (tagname.equalsIgnoreCase(Student.XML_EMAIL)) {
                            student.setEmail(text);
                        }
                        break;

                    default:
                        break;
                }
                //iterate
                eventType = xpp.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        return questions;
    }
}
