package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.abahnj.jokedisplaylibrary.JokeDisplayActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Objects;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }


    private Button jokeDisplayButton;
    private InterstitialAd mInterstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView =  root.findViewById(R.id.adView);
        jokeDisplayButton = root.findViewById(R.id.jokeButton);

        jokeDisplayButton.setOnClickListener(this::launchJokeActivity);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();


        mInterstitialAd = new InterstitialAd(Objects.requireNonNull(getContext()));
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mAdView.loadAd(adRequest);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        return root;
    }

    public void launchJokeActivity(View view) {

        new EndpointsAsyncTask(result -> {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            Intent intent = new Intent(getActivity(), JokeDisplayActivity.class);
            intent.putExtra(JokeDisplayActivity.JOKE_KEY, result);
            startActivity(intent);
        }).execute(getActivity());

    }


}
