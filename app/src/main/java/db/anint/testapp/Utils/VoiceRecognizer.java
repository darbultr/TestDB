package db.anint.testapp.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import java.util.ArrayList;

/**
 * Voice recognizer class used for voice navigation over application.
 */

public class VoiceRecognizer {
    private SpeechRecognizer speechRecognizer;
    private Intent intent;
    private ArrayList<String> recognizedCommands = new ArrayList<>();


    public ArrayList<String> getCommand(Context context) {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
        SpeechListener listener = new SpeechListener();

        speechRecognizer.setRecognitionListener(listener);
        speechRecognizer.startListening(intent);

        if (recognizedCommands != null) {
            return recognizedCommands;
        } else {
            recognizedCommands.add("null");
            return recognizedCommands;
        }
    }

    public void destroyRecognizer() {
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }

    protected class SpeechListener implements RecognitionListener {
        @Override
        public void onReadyForSpeech(Bundle bundle) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float v) {

        }

        @Override
        public void onBufferReceived(byte[] bytes) {

        }

        @Override
        public void onEndOfSpeech() {
            speechRecognizer.startListening(intent);
        }

        @Override
        public void onError(int i) {
            speechRecognizer.startListening(intent);
        }

        @Override
        public void onResults(Bundle bundle) {
            recognizedCommands = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        }

        @Override
        public void onPartialResults(Bundle bundle) {
            speechRecognizer.startListening(intent);
        }

        @Override
        public void onEvent(int i, Bundle bundle) {

        }
    }
}

