package com.kipli.courtcounter;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kipli.courtcounter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityMainBinding activityMainBinding;
    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);

        // observer data
        gameViewModel.getScoreTeamA().observe(this, score -> activityMainBinding.tvTeamAScore.setText(score));
        gameViewModel.getScoreTeamB().observe(this, score -> activityMainBinding.tvTeamBScore.setText(score));
        gameViewModel.getGameTitle().observe(this, gameTitle -> activityMainBinding.tvGameTitle.setText(gameTitle));
        gameViewModel.getTeamAName().observe(this, name -> activityMainBinding.tvTeamAName.setText(name));
        gameViewModel.getTeamBName().observe(this, name -> activityMainBinding.tvTeamBName.setText(name));

        // set onClickListener
        activityMainBinding.bTeamAPlus1.setOnClickListener(this);
        activityMainBinding.bTeamAPlus3.setOnClickListener(this);
        activityMainBinding.bTeamBPlus1.setOnClickListener(this);
        activityMainBinding.bTeamBPlus3.setOnClickListener(this);
        activityMainBinding.bReset.setOnClickListener(this);

        //set data
        gameViewModel.setGameTitle("Pertandingan Akbar");
        gameViewModel.setTeamAName("Team A");
        gameViewModel.setTeamBName("Team B");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bTeamAPlus1:
                gameViewModel.addToTeamA(1);
                break;
            case R.id.bTeamAPlus3:
                gameViewModel.addToTeamA(3);
                break;
            case R.id.bTeamBPlus1:
                gameViewModel.addToTeamB(1);
                break;
            case R.id.bTeamBPlus3:
                gameViewModel.addToTeamB(3);
                break;
            case R.id.bReset:
                gameViewModel.resetScore();
                break;
        }
    }
}
