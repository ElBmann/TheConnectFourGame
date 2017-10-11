package com.androiddev.brianrecuero.theconnectfourgame;

/**
 * Created by Brian Recuero on 10/10/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

/**
 * Created by Brian Recuero on 10/5/2017.
 */

public class ConnectFourView extends GridLayout {
    public int column;
    public int row;
    private Button[][] buttons;
    private Button[] btn;
    private TextView status;
    //private TextView[][] XandO;
    public ConnectFourView(Context context, int width, int newSide2,int newSide,
                           OnClickListener listener) {
        super(context);
        column=newSide;
        row=newSide2;

        setColumnCount(column);
        setRowCount(row+1);

//        XandO=new TextView[row-1][column];
//        for(int r=0;r<row;r++){
//            for(int c=0;c<column;c++){
//                XandO[r][c]=new TextView(context);
//                XandO[r][c].setText((int)(width*.2));
//               addView(XandO[r][c],width,width);
//            }
//        }
        buttons=new Button[row][column];
        for(int r=0;r<row;r++){
            for(int c=0;c<column;c++){

                buttons[r][c]=new Button(context);
                buttons[r][c].setTextSize((int)(width*.2));
                buttons[r][c].setOnClickListener(listener);
                addView(buttons[r][c],width,width);

            }
        }
//        btn=new Button[row];
//        for(int r=0;r<row;r++){
//            btn[r]=new Button(context);
//            btn[r].setTextSize((int)(width*.2));
//            btn[r].setOnClickListener(listener);
//            addView(btn[r],width, width);
//        }


        status=new TextView(context);
        Spec rowSpec=GridLayout.spec(row,1);
        Spec columnSpec = GridLayout.spec(0,column);
        LayoutParams lpStatus= new LayoutParams(rowSpec, columnSpec);
        status.setLayoutParams(lpStatus);

        status.setWidth(row*width);
        status.setHeight(width);//may have to change this
        status.setGravity(Gravity.CENTER);
        status.setBackgroundColor(Color.GREEN);
        status.setTextSize((int)(width*1.5));

        addView(status);
    }
    public void setStatusText(String text){
        status.setText(text);
    }
    public void setStatusBackgroundColor(int color){
        status.setBackgroundColor(color);
    }
    public void setButtonText(int row,int column,String text){
        int r;
        for(r=ConnectFour.row-1;r > 0;r--){
            if(!buttons[r][column].getText().toString().contains("X")&&
                    !buttons[r][column].getText().toString().contains("0")){
                break;
            }
        }
        buttons[r][column].setText(text);
    }
    public boolean isButton(Button b,int row,int column){
        return (b==buttons[row][column]);

    }
    public void resetButtons(){
        for(int r=0; r<row;r++){
            for(int c=0;c<column;c++){
                buttons[r][c].setText("");
            }
        }
    }
    public void enableButtons(boolean enabled){
        for(int r=0;r<row;r++){
            for(int c=0;c<column;c++){
                buttons[r][c].setEnabled(enabled);

            }

        }

    }


}
