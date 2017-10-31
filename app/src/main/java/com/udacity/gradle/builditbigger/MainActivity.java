package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsContextWrapper;


public class MainActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "jokeExtra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem aboutItem = menu.findItem(R.id.action_licenses);
        IconicsDrawable about = new IconicsDrawable(this)
                .icon(GoogleMaterial.Icon.gmd_import_contacts)
                .color(ContextCompat.getColor(this, R.color.icons))
                .sizeDp(24)
                .paddingDp(2);
        aboutItem.setIcon(about);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_licenses) {
            Intent intent = new Intent(this, LicenseActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
