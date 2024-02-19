package com.example.stack_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    int totalcount=0,score=0;
    ArrayList<Integer> arrayList=new ArrayList<>();

//    ALL Button IDS
    Random random=new Random();

    int[] button= { R.id.button00,R.id.button01,R.id.button02,R.id.button03,
                   R.id.button10,R.id.button11,R.id.button12,R.id.button13,
                   R.id.button20,R.id.button21,R.id.button22,R.id.button23,
                   R.id.button30,R.id.button31,R.id.button32,R.id.button33,
                   R.id.button40,R.id.button41,R.id.button42,R.id.button43,
                   R.id.button50,R.id.button51,R.id.button52,R.id.button53,
                   R.id.button60,R.id.button61,R.id.button62,R.id.button63,
                   R.id.button70,R.id.button71};
      int[] text={R.id.textView3,R.id.textView4,R.id.textView5,R.id.textView6,R.id.textView7,R.id.textView8};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //generating random numbers
        for(int i=0;i<(button.length/3);i++)
        {
            int a=random.nextInt(100);
            arrayList.add(a);
            arrayList.add(a);
            arrayList.add(a);
        }
        //shufling all numbers
        Collections.shuffle(arrayList);
        //setting value of buttons
        for(int i=0;i<button.length;i++) {
            int a = arrayList.get(i);
            Button btn = findViewById(button[i]);
            btn.setText(String.valueOf(a));
        }
        for (int i = 0; i < button.length; i++) {
            final int index = i; // Store the index of the button for later use in the OnClickListener
            Button btn = findViewById(button[i]);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean buttonclicked=false;
                    for(int i=0;i<text.length;i++)
                    {
                        TextView t=findViewById(text[i]);
                        if(t.length()==0)
                        {
                            Button clickedButton = (Button) v; // Cast the View to Button
                            String buttonText = clickedButton.getText().toString(); // Get the text of the clicked button
//                            Toast.makeText(MainActivity.this, "Button text: " + buttonText, Toast.LENGTH_SHORT).show();
                            t.setText(buttonText);
                            buttonclicked=true;
                            Log.d("count ",String.valueOf(i));
                            clickedButton.setEnabled(false);
                            totalcount++;
                            if(totalcount==button.length){
                                Toast.makeText(MainActivity.this, "YOU WIN", Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(MainActivity.this, Homepage.class);
                                startActivity(intent);
                            }
                            break;
                        }
                    }
                    if(buttonclicked)
                    {
                        int Count = getCount();
                        if (Count > 2)
                        {
                            sort(Count);
                        }
                    }
                }
            });

        }


        //going back
        imageView=findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Homepage.class);
                startActivity(intent);

            }
        });
    }
    public int getCount() {
        int count = 0;

        for (int i = 0; i < text.length; i++) {
            TextView t = findViewById(text[i]);
            if (t.length() > 0) {
                count++;
            }
        }
        return count;
    }
    public  void sort(int count)
    {
        Stack<Integer> stackresult=new Stack<>();

        int top1=0,top2=0,top3=0;
        for(int i=0;i<count;i++)
        {
            TextView textView=findViewById(text[i]);
            int a= Integer.parseInt(textView.getText().toString());
            stackresult.push(a);
        }
        top1=stackresult.peek();
        Log.d("top",String.valueOf(top1));
        stackresult.pop();
        top2=stackresult.peek();
        Log.d("top2",String.valueOf(top2));
        stackresult.pop();
        top3=stackresult.peek();
        Log.d("top3",String.valueOf(top3));
        stackresult.pop();
        if(top1==top2 && top1==top3)
        {
            for(int i=0;i<=2;i++) {
                TextView textView = findViewById(text[count - 1-i]);
                textView.setText("");
            }
            score++;
            increseScore(score);
        }
        else if(count==6)
        {
            Toast.makeText(this,"YOU LOOS",Toast.LENGTH_LONG).show();

            for(int i=0;i<count;i++)
            {
                TextView textView = findViewById(text[count - 1-i]);
                textView.setText("");

            }
            Intent intent=new Intent(MainActivity.this, Homepage.class);
            startActivity(intent);


        }
    }
    public void increseScore(int score)
    {
        TextView textView=findViewById(R.id.checkscore);
        textView.setText(String.valueOf(score));
    }
}