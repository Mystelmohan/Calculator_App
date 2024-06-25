package com.example.caculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {
    TextView display;
    Button add,Ans,sub,div,mul,num1,num2,num3,num4,num5,num6,num7,num8,num9,num0
            ,num00,point,del,ac,dec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        display=findViewById(R.id.textView);
        assignId(add,R.id.add);
        assignId(Ans,R.id.ans);
        assignId(sub,R.id.sub);
        assignId(div,R.id.div);
        assignId(mul,R.id.mul);
        assignId(num0,R.id.zero);
        assignId(num1,R.id.num1);
        assignId(num2,R.id.num2);
        assignId(num3,R.id.num3);
        assignId(num4,R.id.num4);
        assignId(num5,R.id.num5);
        assignId(num6,R.id.num6);
        assignId(num7,R.id.num7);
        assignId(num8,R.id.num8);
        assignId(num9,R.id.num9);
        assignId(num00,R.id.double_0);
        assignId(point,R.id.point);
        assignId(dec,R.id.percent);
        assignId(del,R.id.del);
        assignId(ac,R.id.Ac);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    void assignId(Button btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        Button btn = (Button) view;
        String btnTxt = btn.getText().toString();
        String dataToCalculate = display.getText().toString();

        switch (btnTxt) {
            case "AC":
                display.setText("0");
                return;
            case "C":
                if (!dataToCalculate.isEmpty()) {
                    dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
                    if (dataToCalculate.isEmpty()) {
                        dataToCalculate = "0";
                    }
                }
                break;
            case "=":
                String finalRes = getResult(dataToCalculate);
                display.setText(finalRes);
                return;
            default:
                if (dataToCalculate.equals("0")) {
                    dataToCalculate = "";
                }
                dataToCalculate += btnTxt;
                break;
        }
        display.setText(dataToCalculate);
    }

    public String getResult(String data) {
        try {
            Expression e = new ExpressionBuilder(data).build();
            double result = e.evaluate();
            return String.valueOf(result);
        } catch (Exception e) {
            Log.d("error", "getResult: " + e.getMessage());
            return "Error";
        }
    }
}