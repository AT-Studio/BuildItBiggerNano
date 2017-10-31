package com.udacity.gradle.builditbigger.paid;

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

    @BindView(R.id.jokeProgressBar)
    ProgressBar jokeProgressBar;

    @BindView(R.id.jokeButtonText)
    TextView jokeButtonText;

    Context context;

    Unbinder unbinder;

    boolean isLoadingJoke;

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

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tellJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJokeButton.setEnabled(false);
                jokeButtonText.setVisibility(View.INVISIBLE);
                jokeProgressBar.setVisibility(View.VISIBLE);
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
        Intent intent = new Intent(context, AndroidDisplayActivity.class);
        intent.putExtra(MainActivity.JOKE_EXTRA, joke);
        context.startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
