package com.tutorial;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TutorialActivity extends Activity {
    /** Called when the activity is first created. */
    private EditText textNome;
    private EditText textTel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        textNome = (EditText) findViewById(R.id.editText1);
        textTel = (EditText) findViewById(R.id.editText2);
    }

    public void okClickHandler(View button) {
        Toast.makeText(
                this,
                textNome.getText().toString() + ":\n"
                        + textTel.getText().toString(), Toast.LENGTH_SHORT)
                .show();
    }

    public void limparClickHandler(View button) {
        textNome.setText("");
        textTel.setText("");
    }
}