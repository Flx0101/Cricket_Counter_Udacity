package com.example.knight.cricket;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final int[] IMAGES = {R.drawable.cskwon, R.drawable.csklosing,R.drawable.whowillwin};
    private ImageSwitcher mImageSwitcher;
    int toss = 0;
    int team_a_overs = 0;
    int team_b_overs = 0;
    int team_a_balls = 0;
    int team_b_balls = 0;
    int total_overs = 5;
    int total_balls = total_overs * 6;
    int team_a_wickets = 0;
    int team_b_wickets = 0;
    int match_start = 0;
    int team_a_score = 0;
    int team_b_score = 0;
    String winnerName = "";
    int inningsOver = 0;
    int team_a_batting = 0;
    int team_b_batting = 0;
    int winners = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageSwitcher = (ImageSwitcher) findViewById(R.id.image_switcher);
        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                return imageView;
            }
        });
        mImageSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        mImageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);

        onSwitch(null);
    }
    public void onSwitch(View view) {
        if(winnerName=="Chennai Super Kings"){
            mImageSwitcher.setBackgroundResource(IMAGES[1]);
        }
        else if(winnerName=="Royal Challenge Bangalore")
        {
            mImageSwitcher.setBackgroundResource(IMAGES[0]);
        }
        else{
            mImageSwitcher.setBackgroundResource(IMAGES[2]);
        }
    }

    @SuppressLint("SetTextI18n")
    public void rematch(View view) {
        team_a_overs = 0;
        team_b_overs = 0;
        team_a_balls = 0;
        team_b_balls = 0;
        total_overs = 5;
        total_balls = total_overs * 6;
        team_a_wickets = 0;
        team_b_wickets = 0;
        match_start = 0;
        team_a_score = 0;
        team_b_score = 0;
        team_a_wickets = 0;
        team_b_wickets = 0;
        inningsOver = 0;
        winnerName = "";
        winners=0;
        display_team_a_wickets(team_a_wickets);
        display_team_b_wickets(team_b_wickets);
        display_team_a_score(team_a_score);
        display_team_b_score(team_a_score);
        display_oversa(team_a_overs);
        display_oversb(team_b_overs);
        display_team_a_balls();
        display_team_b_balls();
        TextView toss_winner = findViewById(R.id.choose_to_bat);
        toss_winner.setText("Yet to be decided");
        displayWinnerName("Yet to be decided");
    }

    @SuppressLint("SetTextI18n")

    public void displayTossWinner(View view) {
        if (match_start == 0) {
            Random random = new Random();
            toss = random.nextInt(2);
            TextView toss_winner = findViewById(R.id.choose_to_bat);
            if (toss == 0) {
                toss_winner.setText("Chennai Super Kings");
                team_a_batting = 1;
                winnerName = "Chennai Super Kings";
            } else if (toss == 1) {
                toss_winner.setText("Royal Challenge Bangalore");
                team_b_batting = 1;
                winnerName = "Royal Challenge Bangalore";
            }
            match_start = 1;
        }
    }

    public void add_run(View view) {
        if (match_start == 1 && toss == 0 && team_a_batting == 1 && team_a_balls <= 30 && winners==0) {
            Random random = new Random();
            int score;
            score = random.nextInt(6);
            if (score != 5) {
                team_a_balls = team_a_balls + 1;
                score = random.nextInt(5);
            } else if (score == 5) {
                team_a_balls = team_a_balls + 1;
                team_a_wickets = team_a_wickets + 1;
                score = score - 5;
            } else {
                score = 6;
                team_a_balls = team_a_balls + 1;
            }
            team_a_score = team_a_score + score;
            display_team_a_score(team_a_score);
            display_team_a_balls();
            display_team_a_wickets(team_a_wickets);
        } else if (match_start == 1 && toss == 1 && team_b_batting == 1 && team_b_balls <= 30 && winners==0)

        {
            Random random = new Random();
            int score;
            score = random.nextInt(6);
            if (score != 5) {
                score = random.nextInt(5);
            } else if (score == 5) {
                team_b_wickets = team_b_wickets + 1;
                score = score - 5;
            } else {
                score = 6;
            }
            team_b_score = team_b_score + score;
            team_b_balls = team_b_balls + 1;
            display_team_b_score(team_b_score);
            display_team_b_balls();
            display_team_b_wickets(team_b_wickets);
        }
    }


    public void display_team_a_balls() {
        TextView team_a_ball_view = (TextView) findViewById(R.id.team_a_balls);
        team_a_ball_view.setText(("" + team_a_balls % 6));
        if (team_a_balls < 30 && team_a_wickets < 10 && match_start == 1 && winners==0) {
            if (team_a_balls % 6 == 0) {
                team_a_overs += 1;
                display_oversa(team_a_overs);
            }
        } else if (team_a_balls == 30 && team_a_wickets < 10 && match_start == 1 && winners==0)

        {
            team_a_overs += 1;
            toss = 1;
            inningsOver = 1;
            display_oversa(team_a_overs);
            team_a_batting = 0;
            team_b_batting = 1;
        } else if (team_a_balls < 30 && team_a_wickets == 10 && match_start == 1 && winners==0)

        {
            toss = 1;
            inningsOver = 1;
            team_a_batting = 0;
            team_b_batting = 1;
        }
        if (inningsOver == 2)

        {
            checkWinner();
        }

    }

    public void display_team_b_balls() {
        TextView team_b_ball_view = (TextView) findViewById(R.id.team_b_value);
        team_b_ball_view.setText(("" + team_b_balls % 6));
        if (team_b_balls < 30 && team_b_wickets < 10 && match_start == 1) {
            if (team_b_balls % 6 == 0) {
                team_b_overs += 1;
                display_oversb(team_b_overs);
            }
        } else if (team_b_balls == 30 && team_b_wickets < 10 && match_start == 1) {
            team_b_overs += 1;
            toss = 0;
            inningsOver = 2;
            display_oversb(team_b_overs);
            team_b_batting = 0;
            team_a_batting = 1;
        } else if (team_b_balls < 30 && team_b_wickets == 10 && match_start == 1) {
            toss = 0;
            inningsOver = 2;
            team_b_batting = 0;
            team_a_batting = 1;
        }
        if (inningsOver == 2) {
            checkWinner();
        }
    }

    public void display_oversa(int overs) {
        TextView overs_view = (TextView) findViewById(R.id.team_a_overs);
        overs_view.setText("" + overs);
    }

    public void display_oversb(int overs) {
        TextView overs_view = (TextView) findViewById(R.id.team_b_overs);
        overs_view.setText("" + overs);
    }


    public void display_team_a_score(int team_a_score) {
        TextView team_a_score_view = (TextView) findViewById(R.id.score_csk);
        team_a_score_view.setText("" + team_a_score);
    }

    public void display_team_b_score(int team_b_score) {
        TextView team_a_score_view = (TextView) findViewById(R.id.score_rcb);
        team_a_score_view.setText("" + team_b_score);
    }


    public void checkWinner() {
        if (inningsOver == 2 && team_a_score > team_b_score && team_b_batting == 1 && (team_b_wickets == 10 || team_b_balls == 30)) {
            winnerName = "Chennai Super Kings";
            displayWinnerName(winnerName);
        } else if (inningsOver == 2 && team_a_score < team_b_score && team_b_batting == 1) {
            winnerName = "Royal Challenge Bangalore";
            displayWinnerName(winnerName);
        }
        if (inningsOver == 2 && team_b_score > team_a_score && team_a_batting == 1 && (team_a_wickets == 10 || team_a_balls == 30)) {
            winnerName = "Chennai Super Kings";
            displayWinnerName(winnerName);
        } else if (inningsOver == 2 && team_b_score < team_a_score && team_a_batting == 1) {
            winnerName = "Royal Challenge Bangalore";
            displayWinnerName(winnerName);
        }
    }


    public void displayWinnerName(String winner) {
        //@SuppressLint("WrongViewCast") TextView winner_view = (TextView) findViewById(R.id.image_switcher);
        toss = -1;
        winners=1;
        onSwitch(null);
    }

    public void display_team_a_wickets(int team_a_wickets) {
        TextView team_a_wicket_view = (TextView) findViewById(R.id.wicket_csk);
        team_a_wicket_view.setText("" + team_a_wickets);
    }

    public void display_team_b_wickets(int team_b_wickets) {
        TextView team_b_wicket_view = (TextView) findViewById(R.id.wicket_rcb);
        team_b_wicket_view.setText("" + team_b_wickets);

    }


}