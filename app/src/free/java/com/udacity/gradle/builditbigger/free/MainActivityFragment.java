package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.alit.androiddisplay.AndroidDisplayActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.JokeResultFragment;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeResultFragment {

    @BindView(R.id.tellJokeButton)
    CardView tellJokeButton;

    @BindView(R.id.adView)
    AdView adView;

    @BindView(R.id.jokeButtonText)
    TextView jokeButtonText;

    @BindView(R.id.jokeProgressBar)
    ProgressBar jokeProgressBar;

    Context context;

    public InterstitialAd interstitialAd;

    private String joke;
    boolean jokeIsLoaded;
    boolean addIsClosed;
    boolean addDidntLoad;

    boolean isLoadingJoke;

    Unbinder unbinder;

    public MainActivityFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        unbinder = ButterKnife.bind(this, root);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("F4AF9BDCF9C410C49798712DABC478CB")
                .build());

        interstitialAd.setAdListener(new AdListener() {


            @Override
            public void onAdClosed() {
                interstitialAd.loadAd(new AdRequest.Builder().build());
                addIsClosed = true;
                if (jokeIsLoaded) {
                    Intent intent = new Intent(context, AndroidDisplayActivity.class);
                    intent.putExtra(MainActivity.JOKE_EXTRA, joke);
                    context.startActivity(intent);
                    jokeIsLoaded = false;
                    tellJokeButton.setEnabled(true);
                }


            }

        });

        tellJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJokeButton.setEnabled(false);
                jokeButtonText.setVisibility(View.INVISIBLE);
                jokeProgressBar.setVisibility(View.VISIBLE);
                isLoadingJoke = true;

                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                    addIsClosed = false;
                } else {
                    addDidntLoad = true;
                }

                isLoadingJoke = true;
                new EndpointsAsyncTask().execute(new Pair<Context, JokeResultFragment>(context, MainActivityFragment.this));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!isLoadingJoke) {
            jokeButtonText.setVisibility(View.VISIBLE);
            jokeProgressBar.setVisibility(View.INVISIBLE);
            tellJokeButton.setEnabled(true);
        }
    }

    @Override
    public void endpointsAsyncTaskResult(String joke) {
        isLoadingJoke = false;
        if (addIsClosed || addDidntLoad) {
            addDidntLoad = false;
            Intent intent = new Intent(context, AndroidDisplayActivity.class);
            intent.putExtra(MainActivity.JOKE_EXTRA, joke);
            context.startActivity(intent);
        } else {
            jokeIsLoaded = true;
            this.joke = joke;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
