package db.anint.testapp.Utils;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;


/**
 * Voice recognizer class used for voice navigation over application.
 */

public class VoiceRecognizer {

    private SpeechRecognizer speech;
    private Intent recognizerIntent;

    public static final String[] COMMANDS_DOWN = {
            "dalej",
            "następny",
            "Dalej",
            "Następny"
    };
    public static final String[] COMMANDS_UP = {
            "poprzedni",
            "Poprzedni"
    };
    public static final String[] COMMANDS_BACK = {
            "wróć",
            "Wróć"
    };
    public static final String[] COMMANDS_ENTER = {
            "wybierz",
            "Wybierz"
    };
    public static final String[] COMMANDS_NAVIGATE = {
            "nawiguj",
            "Nawiguj"
    };
    public static final String[] COMMANDS_DELETE = {
            "usuń",
            "Usuń"
    };
    public static final String[] COMMANDS_COMPLETED = {
            "zrealizowane",
            "Zrealizowane"
    };
    public static final String[] COMMANDS_PHOTO = {
            "fotka",
            "Fotka"
    };
    public static final String[] COMMANDS_FINISH = {
            "zakończ",
            "Zakończ"
    };

    public void initListener(Context context, RecognitionListener recognitionListener){
        speech = SpeechRecognizer.createSpeechRecognizer(context);
        speech.setRecognitionListener(recognitionListener);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "pl");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
        speech.startListening(recognizerIntent);
    }
    public SpeechRecognizer getListener(){
        return speech;
    }
    public void startListener(){
            speech.startListening(recognizerIntent);
    }


}