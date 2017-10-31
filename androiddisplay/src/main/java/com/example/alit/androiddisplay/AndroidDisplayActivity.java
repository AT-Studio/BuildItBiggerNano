package com.example.alit.androiddisplay;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.iconics.context.IconicsContextWrapper;

public class AndroidDisplayActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "jokeExtra";

    TextView jokeTextView;

    ImageView closeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androiddisplay);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        jokeTextView = (TextView) findViewById(R.id.jokeTextView);
        closeActivity = (ImageView) findViewById(R.id.closeActivity);

        String joke = getIntent().getStringExtra(JOKE_EXTRA);

        jokeTextView.setText(joke);

        closeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }
}
