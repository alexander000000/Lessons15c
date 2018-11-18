package av.shangin.lessons15c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {



    private ImageView imageViewLeft;
    private ImageView imageViewRight;
    private ImageView imageViewCenter;

    private Button mNewGame;
    private TextView mText;
    private TextView mScore;

    public final static int COUNT_THIMBLE =3;

    public  final static int CLOSE_NAP =1;
    public  final static int OPEN_NAP =2;
    public  final static int BALL =3;


    private boolean userIsPress =false;

    private int mUserCountWins =0;
    private int mUserCountLost =0;

    private  Random rand;
    private  int mWhereIsBall=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        imageViewLeft  =  findViewById(R.id.IVLeft); //0
        imageViewCenter  =  findViewById(R.id.IVCenter);//1
        imageViewRight  =  findViewById(R.id.IVRight);//2

        mNewGame = findViewById(R.id.buttonNewGame);
        mText = findViewById(R.id.textView);
        mScore = findViewById(R.id.tvScore);

        //Log.d("MainActivity","onCreate");
        setDrawable();

        if (!userIsPress) initGame();
        updateScroll();

        //-----

        //------
        imageViewLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!userIsPress) {
                    //Log.d("MainActivity","imageViewLeft ball in==="+mWhereIsBall);
                    userIsPress = true;
                    //mText.setText("imageViewLeft");

                    imageViewLeft.getDrawable().setLevel(getLevel(0)); //0 - t.k. left
                    updateScroll();
                }
            }
        });
        imageViewCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!userIsPress) {
                    //Log.d("MainActivity","imageViewCenter ball in=="+mWhereIsBall);
                    userIsPress = true;
                    //mText.setText("imageViewCenter");
                    imageViewCenter.getDrawable().setLevel(getLevel(1)); //0 - t.k. center
                    updateScroll();
                }
            }
        });
        imageViewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!userIsPress) {
                    //Log.d("MainActivity","imageViewRight ball in="+mWhereIsBall);;
                    userIsPress = true;
                    //mText.setText("imageViewRight");
                    imageViewRight.getDrawable().setLevel(getLevel(2)); //0 - t.k. right
                    updateScroll();
                }
            }
        });



        mNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("MainActivity","mNewGame");
                initGame();
                updateScroll();

            }
        });
        //setDrawable();
    }


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mUserCountWins", mUserCountWins);
        outState.putInt("mUserCountLost", mUserCountLost);
        outState.putInt("mWhereIsBall", mWhereIsBall);
        //Log.d("MainActivity","onSaveInstanceState");
        //og.d(LOG_TAG, "onSaveInstanceState");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mUserCountWins = savedInstanceState.getInt("mUserCountWins");
        mUserCountLost = savedInstanceState.getInt("mUserCountLost");
        mWhereIsBall = savedInstanceState.getInt("mWhereIsBall");
        //Log.d(LOG_TAG, "onRestoreInstanceState");
        //Log.d("MainActivity","onRestoreInstanceState");
        updateScroll();
    }

    private void setDrawable() {

        imageViewLeft.setImageResource(R.drawable.level_list);
        imageViewCenter.setImageResource(R.drawable.level_list);
        imageViewRight.setImageResource(R.drawable.level_list);


        //imageViewLeft.setImageResource(R.drawable.level);
        //imageViewCenter.setImageResource(R.drawable.level);
        //imageViewRight.setImageResource(R.drawable.level);

    }

    private void setBall() {
        mWhereIsBall =rand.nextInt(COUNT_THIMBLE);
        String res = getString(R.string.helpText);
        mText.setText(String.format(res,(mWhereIsBall+1)));
        //Log.d("MainActivity","setBall. ball in ="+mWhereIsBall);
        //for (int x : ballInThimble){
        //    x=0;
        //}
        //ballInThimble[mWhereIsBall]=1;

    }

    private void updateScroll() {

        String res1 = getString(R.string.score);
        //Log.d("MainActivity","updateScroll"+mUserCountWins+"/"+mUserCountLost);

        mScore.setText(String.format(res1,mUserCountWins, mUserCountLost));
    }

    private int getLevel(int ir) {

        //Log.d("MainActivity","getLevel i="+ir+" and mWhereIsBall="+mWhereIsBall);
        //mWhereIsBall
        if (ir==mWhereIsBall){
            mUserCountWins++;
            mUserCountLost++;
            return BALL;
        }
        else {
            if (userIsPress) {
                mUserCountLost++;
                //Log.d("MainActivity","getLevel OPEN_NAP");
                return OPEN_NAP;
            } else {
                //Log.d("MainActivity","getLevel CLOSE_NAP");
                return CLOSE_NAP;
            }
        }


    }

    private void initGame() {

        rand = new Random();
        userIsPress =false;
        imageViewLeft.getDrawable().setLevel(CLOSE_NAP);
        imageViewCenter.getDrawable().setLevel(CLOSE_NAP);
        imageViewRight.getDrawable().setLevel(CLOSE_NAP);

        setBall();

    }

}
