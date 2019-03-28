package com.example.muhamedz_fiek.ticktacktoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

   //0 for yellow and 1 for red
   int activePlayer =0;
   int gameStates [] ={2,2,2,2,2,2,2,2,2};
   int winningPositions [][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{0,4,8},{1,4,7},{2,4,6},{2,5,8}};
   boolean gameIsActive = true;
   private TextView winningMessage;
   private LinearLayout winingLayout;
   private Button playAgain;
   private Button btnStartTheGame;
   private GridLayout gridLayout;
   private TextView tvplayer1name;
   private  TextView tvplayer2name;
   private EditText etplayer1name;
   private EditText etplayer2name;
   Random random = new Random();
    String player1;
    String player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winingLayout= findViewById(R.id.winningLayout);
        winningMessage = findViewById(R.id.mesazhiFitues);
        gridLayout = findViewById(R.id.gridLayout);
        playAgain = findViewById(R.id.playAgain);
        btnStartTheGame =findViewById(R.id.btnStartTheGame);
        tvplayer1name = findViewById(R.id.tvplayer1name);
        tvplayer2name = findViewById(R.id.tvplayer2name);
        etplayer1name = findViewById(R.id.etplayer1name);
        etplayer2name = findViewById(R.id.etplayer2name);
        winingLayout.setAlpha(0);

    }

    public void drop(View view)
    {
        ImageView pozicioni = (ImageView) view;
       int selectedField =Integer.parseInt(pozicioni.getTag().toString());
        Log.i("pressedTag",selectedField+"");

        if(gameStates[selectedField]==2 && gameIsActive)
        {
             gameStates[selectedField]=activePlayer;
            if (activePlayer ==0)
            {
                Toast.makeText(this, "Lojtari 2 ka radhen", Toast.LENGTH_LONG).show();
                pozicioni.setImageResource(R.drawable.yellow);


                activePlayer=1;
            }
            else
            {
                Toast.makeText(this, "Lojtari 1 ka radhen", Toast.LENGTH_LONG).show();
                pozicioni.setImageResource(R.drawable.red);

                activePlayer=0;
            }
        }


        for (int winner[]: winningPositions)
        {
            if(gameStates[winner[0]] == gameStates[winner[1]] && gameStates[winner[1]]== gameStates[winner[2]]    && gameStates[winner[0]]!=2)

            {
                if(gameStates[winner[0]]==0)
                {
                    Log.i("yellow","is the winner" );
                    winningMessage.setText(player1+" eshte fitues!");
                    winingLayout.setBackgroundColor(Color.YELLOW);

                    winingLayout .animate().rotation(360f).alpha(0.8f).setDuration(3200);

                }
                   else
                {
                    Log.i("red", "is the winner");
                    winningMessage.setText(player2+" eshte fitues!");
                    winingLayout.setBackgroundColor(Color.RED);
                    winingLayout .animate().rotation(360f).alpha(0.8f).setDuration(3200);


                }
                gameIsActive=false;
                winingLayout.setVisibility(View.VISIBLE);
                break;
            }
             else
            {
               boolean gameIsOver=true;
                for(int i :gameStates)
                {
                    if(i==2)
                    {
                        gameIsOver=false;
                    }
                    Log.i("gameState",i+"");
                }
                if(gameIsOver)
                {   gameIsActive=false;
                    winningMessage.setText("Loja perfundoi me barazim");
                    Log.i("gjendja e lojes","barazim");
                    winningMessage.setBackgroundColor(Color.GREEN);
                    winingLayout .animate().rotation(360f).alpha(0.8f).setDuration(3200);
                    winingLayout.setVisibility(View.VISIBLE);


                }
            }
        }
    }

    public void playAgain(View view)
    {
        gameIsActive=true;
        winingLayout.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.INVISIBLE);
        tvplayer1name.setVisibility(View.VISIBLE);
        tvplayer2name.setVisibility(View.VISIBLE);
        etplayer1name.setVisibility(View.VISIBLE);
        etplayer2name.setVisibility(View.VISIBLE);
        btnStartTheGame.setVisibility(View.VISIBLE);
        etplayer1name.setText("");
        etplayer2name.setText("");

        activePlayer=random.nextInt(2);

        for (int i =0 ;i<=8;i++)
        {
            gameStates[i]=2;
        }
        for (int i=0;i<gridLayout.getChildCount();i++)
        {
         ImageView child = (ImageView) gridLayout.getChildAt(i);
         child.setImageResource(0);

        }
    }

    public void startTheGame(View view)
    {
        player1 = etplayer1name.getText().toString();
        player2 = etplayer2name.getText().toString();
        tvplayer1name.setVisibility(View.INVISIBLE);
        tvplayer2name.setVisibility(View.INVISIBLE);
        etplayer1name.setVisibility(View.INVISIBLE);
        etplayer2name.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        btnStartTheGame.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "Lojtari 1 fillon lojen", Toast.LENGTH_LONG).show();


    }
}
