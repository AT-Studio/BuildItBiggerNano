package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.alit.builditbiggernano.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by AliT on 10/30/17.
 */

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, JokeResultFragment>, Void, String> {
    private static MyApi myApiService = null;
    private JokeResultFragment jokeResultFragment;

    @Override
    protected String doInBackground(Pair<Context, JokeResultFragment>... params) {

        Context context = params[0].first;
        jokeResultFragment = params[0].second;

        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://" + context.getResources().getString(R.string.GCPPrjectID) + ".appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        try {
            String result = myApiService.getJoke().execute().getData();
            return result;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        jokeResultFragment.endpointsAsyncTaskResult(result);
    }
}