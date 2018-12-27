package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.abahnj.jokedisplaylibrary.JokeDisplayActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button jokeDisplayButton = root.findViewById(R.id.jokeButton);

        jokeDisplayButton.setOnClickListener(this::launchJokeActivity);

        return root;
    }

    public void launchJokeActivity(View view) {

        new EndpointsAsyncTask(result -> {
            Intent intent = new Intent(getActivity(), JokeDisplayActivity.class);
            intent.putExtra(JokeDisplayActivity.JOKE_KEY, result);
            startActivity(intent);
        }).execute(getActivity());

    }


}
