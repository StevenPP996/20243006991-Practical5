package com.example.practicalfive;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.view.View;
import android.view.WindowInsets;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    public static final String EXTRA_MUNICIPALITY = "com.example.practicalfive.EXTRA_MUNICIPALITY";

    private EditText municipalityInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureWindowInsets();
        setContentView(R.layout.activity_main);
        applySystemBarInsets(findViewById(R.id.main_root));

        municipalityInput = findViewById(R.id.municipality_input);
        municipalityInput.setOnEditorActionListener((TextView view, int actionId, android.view.KeyEvent event) -> {
            boolean enterPressed = event != null
                    && event.getKeyCode() == android.view.KeyEvent.KEYCODE_ENTER
                    && event.getAction() == android.view.KeyEvent.ACTION_DOWN;
            if (actionId == EditorInfo.IME_ACTION_GO
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || enterPressed) {
                submitMunicipality();
                return true;
            }
            return false;
        });
    }

    private void submitMunicipality() {
        String municipality = TextProcessor.processMunicipality(municipalityInput.getText().toString());
        if (municipality == null) {
            municipalityInput.setError(getString(R.string.error_invalid_municipality));
            municipalityInput.requestFocus();
            return;
        }

        municipalityInput.setError(null);
        Intent intent = new Intent(this, Municipality.class);
        intent.putExtra(EXTRA_MUNICIPALITY, municipality);
        startActivity(intent);
    }

    private void configureWindowInsets() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        }
    }

    private void applySystemBarInsets(View root) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            return;
        }

        final int left = root.getPaddingLeft();
        final int top = root.getPaddingTop();
        final int right = root.getPaddingRight();
        final int bottom = root.getPaddingBottom();
        root.setOnApplyWindowInsetsListener((view, windowInsets) -> {
            android.graphics.Insets systemBars = windowInsets.getInsets(
                    WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout());
            view.setPadding(left + systemBars.left, top + systemBars.top,
                    right + systemBars.right, bottom + systemBars.bottom);
            return windowInsets;
        });
        root.requestApplyInsets();
    }
}
