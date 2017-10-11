package com.androiddev.brianrecuero.theconnectfourgame;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private ConnectFour game;
    private ConnectFourView CFView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        game = new ConnectFour();
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w= size.x/ ConnectFour.column;//not sure if this will work
        ButtonHandler bh=new ButtonHandler();
        CFView=new ConnectFourView(this,w,ConnectFour.row, ConnectFour.column,bh);//take a look at
        CFView.setStatusText(game.result());
        setContentView(CFView);


    }
    public void showNewGameDialog(){
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("This is fun");
        alert.setMessage("Play Agian?");
        PlayDialog playAgian = new PlayDialog();
        alert.setPositiveButton("YES", playAgian);
        alert.setNegativeButton("NO",playAgian);
        alert.show();
        //todo: if user chooses no send to credit page

    }
    private class ButtonHandler implements ConnectFourView.OnClickListener{
        public void onClick(View v){
            for (int row = 0; row< CFView.row; row++){
                for(int column = 0; column< CFView.column; column++){
                    if(CFView.isButton(( Button ) v, row, column)){
                        int play = game.play( row, column );
                        if(play==1 ){

                            CFView.setButtonText(row,column,"X");
                        }
                        else if(play==2){
                            CFView.setButtonText(row,column,"0");
                        }
                        if(game.isGameOver()){
                            CFView.setStatusBackgroundColor(Color.RED);
                            CFView.enableButtons(false);
                            CFView.setStatusText(game.result());
                            showNewGameDialog();//Offers to play agian
                        }

                    }

                }

            }

        }
    }

    private class PlayDialog implements DialogInterface.OnClickListener{
        public void onClick(DialogInterface dialog,int id){
            if(id==-1){/*Yes Button*/{
                game.resetGame();
                CFView.enableButtons(true);
                CFView.resetButtons();
                CFView.setStatusBackgroundColor(Color.GREEN);
                CFView.setStatusText(game.result());
            }


            }else if(id==2){
                MainActivity.this.finish();

            }

        }


    }
}
