package com.example.practicalfive;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Municipality extends Activity {
    private String municipality;
    private TextView municipalityName;
    private TextView informationText;
    private Button visitSiteButton;
    private int accentColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureWindowInsets();
        setContentView(R.layout.activity_municipality);
        applySystemBarInsets(findViewById(R.id.municipality_root));

        municipalityName = findViewById(R.id.municipality_name);
        ImageView municipalityImage = findViewById(R.id.municipality_image);
        informationText = findViewById(R.id.municipality_information);
        visitSiteButton = findViewById(R.id.visit_site_button);

        String incomingName = getIntent().getStringExtra(MainActivity.EXTRA_MUNICIPALITY);
        municipality = TextProcessor.processMunicipality(incomingName);
        if (municipality == null) {
            Toast.makeText(this, R.string.error_invalid_municipality, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        int summaryIndex;
        int imageResource;
        int colourResource;
        switch (municipality) {
            case "Beijing":
                summaryIndex = 0;
                imageResource = R.drawable.beijing;
                colourResource = R.color.municipality_beijing;
                break;
            case "Shanghai":
                summaryIndex = 1;
                imageResource = R.drawable.shanghai;
                colourResource = R.color.municipality_shanghai;
                break;
            case "Chongqing":
                summaryIndex = 2;
                imageResource = R.drawable.chongqing;
                colourResource = R.color.municipality_chongqing;
                break;
            case "Tianjin":
                summaryIndex = 3;
                imageResource = R.drawable.tianjin;
                colourResource = R.color.municipality_tianjin;
                break;
            default:
                Toast.makeText(this, R.string.error_invalid_municipality, Toast.LENGTH_LONG).show();
                finish();
                return;
        }

        accentColor = getColor(colourResource);
        municipalityName.setText(municipality);
        municipalityName.setBackgroundColor(accentColor);
        municipalityName.setTextColor(getColor(R.color.color_text_light));
        municipalityImage.setImageResource(imageResource);
        informationText.setText(getResources().getStringArray(R.array.municipalities_array)[summaryIndex]);
        informationText.setTextColor(accentColor);
        visitSiteButton.setBackgroundTintList(android.content.res.ColorStateList.valueOf(accentColor));
        visitSiteButton.setTextColor(Color.WHITE);
        visitSiteButton.setOnClickListener(this::openOfficialWebsite);
    }

    private void openOfficialWebsite(View ignored) {
        String url;
        switch (municipality) {
            case "Beijing":
                url = "https://english.beijing.gov.cn/";
                break;
            case "Shanghai":
                url = "https://english.shanghai.gov.cn/";
                break;
            case "Tianjin":
                url = "https://en.tj.gov.cn/";
                break;
            case "Chongqing":
                url = "https://english.cq.gov.cn/";
                break;
            default:
                return;
        }

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        try {
            startActivity(browserIntent);
        } catch (ActivityNotFoundException exception) {
            Toast.makeText(this, R.string.error_no_browser, Toast.LENGTH_LONG).show();
        }
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
