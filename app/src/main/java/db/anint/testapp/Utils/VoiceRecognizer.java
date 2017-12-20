package db.anint.testapp.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import db.anint.testapp.R;


/**
 * Voice recognizer class used for voice navigation over application.
 */

public class VoiceRecognizer {

    private SpeechRecognizer speech;
    private Intent recognizerIntent;

    private boolean exit = false;
    private boolean deletePoint = false;
    private static ArrayList<String> matches;

    private static boolean checkCommands(ArrayList<String> commands) {
        return !Collections.disjoint(matches, commands);
    }

    public String executeCommands(ArrayList<String> matches,
                                  ListView listView,
                                  int position,
                                  Activity activity,
                                  BaseAdapter baseAdapter,
                                  Snackbar snackbar,
                                  Boolean pointActivity) {

        VoiceRecognizer.matches = matches;


        if (!exit) {
        /*
        Command used to set focus on next list item.
         */
            if (checkCommands(COMMANDS_DOWN)) {
                if (position < listView.getAdapter().getCount()) {
                    return "down";
                }
            }

        /*
        Command used to set focus on previous list item.
         */
            else if (checkCommands(COMMANDS_UP)) {
                if (position != 0) {
                    return "up";
                }
            }

        /*
        Command used to back on activities.
         */
            else if (checkCommands(COMMANDS_BACK)) {
                activity.onBackPressed();
                return "back";
            }

        /*
        Command used to enter specific item on listview.
        */
            else if (checkCommands(COMMANDS_ENTER)) {
                listView.performItemClick(listView, position, baseAdapter.getItemId(position));
                return "enter";
            }

        /*
        Command used to show exit confirm box.
        */
            else if (checkCommands(COMMANDS_FINISH)) {
                snackbar.show();
                exit = true;
                return "exitBar";
            }

        /*
        Commands specific for points activity.
         */

            if (pointActivity) {

            /*
            Command used to navigate to specific point.
             */
                if (checkCommands(COMMANDS_NAVIGATE)) {
                    return "navigate";
                }

            /*
            Command used to delete specific point from list.
             */
                else if (checkCommands(COMMANDS_DELETE)) {
                    deletePoint = true;
                    return "delete";
                }

            /*
            Command used to mark specific point as completed.
             */
                else if (checkCommands(COMMANDS_COMPLETED)) {
                    return "completed";
                }

            /*
            Commands used to make photo of specific point
             */
                else if (checkCommands(COMMANDS_PHOTO)) {
                    return "photo";
                }

                 /*
                 Commands specific for delete point confirm bar.
                 */

                if (deletePoint) {

                    /*
                    Yes command - used only when trying to delete point.
                    */

                    if (checkCommands(COMMANDS_YES)) {
                        return "yesDelete";
                    }

                     /*
                     No command - used only when trying to exit application.
                     */

                    if (checkCommands(COMMANDS_NO)) {
                        deletePoint = false;
                        return "no";
                    }
                }
            }
        }

          /*
        Commands specific for exit confirm bar.
         */

        if (exit) {

            /*
            Yes command - used only when trying to exit application.
            */
            if (checkCommands(COMMANDS_YES)) {
                return "yes";
            }

            /*
            No command - used only when trying to exit application.
            */
            if (checkCommands(COMMANDS_NO)) {
                snackbar.dismiss();
                exit = false;
                return "no";

            }
        }
        return "nothing";
    }

    private static final ArrayList<String> COMMANDS_DOWN = new ArrayList<String>() {{
        add("dalej");
        add("następny");
        add("Dalej");
        add("Następny");
    }};
    private static final ArrayList<String> COMMANDS_UP = new ArrayList<String>() {{
        add("poprzedni");
        add("Poprzedni");
    }};
    private static final ArrayList<String> COMMANDS_BACK = new ArrayList<String>() {{
        add("wróć");
        add("Wróć");
    }};
    private static final ArrayList<String> COMMANDS_ENTER = new ArrayList<String>() {{
        add("wybierz");
        add("Wybierz");
    }};
    private static final ArrayList<String> COMMANDS_NAVIGATE = new ArrayList<String>() {{
        add("nawiguj");
        add("Nawiguj");
    }};
    private static final ArrayList<String> COMMANDS_DELETE = new ArrayList<String>() {{
        add("usuń");
        add("Usuń");
    }};
    private static final ArrayList<String> COMMANDS_COMPLETED = new ArrayList<String>() {{
        add("zrealizowane");
        add("Zrealizowane");
    }};
    private static final ArrayList<String> COMMANDS_PHOTO = new ArrayList<String>() {{
        add("fotka");
        add("Fotka");
    }};
    private static final ArrayList<String> COMMANDS_FINISH = new ArrayList<String>() {{
        add("zakończ");
        add("Zakończ");
    }};
    private static final ArrayList<String> COMMANDS_YES = new ArrayList<String>() {{
        add("tak");
        add("Tak");
    }};
    private static final ArrayList<String> COMMANDS_NO = new ArrayList<String>() {{
        add("nie");
        add("Nie");
    }};

    public void initListener(Context context, RecognitionListener recognitionListener) {
        speech = SpeechRecognizer.createSpeechRecognizer(context);
        speech.setRecognitionListener(recognitionListener);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "pl");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
    }

    public SpeechRecognizer getListener() {
        return speech;
    }

    public void startListener() {
        speech.startListening(recognizerIntent);
    }


}