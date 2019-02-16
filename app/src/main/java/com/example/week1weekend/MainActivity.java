package com.example.week1weekend;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    TextView number0, number1, number2, number3, number4;
    TextView number5, number6, number7, number8, number9;
    TextView point, equal, addition, subtraction, multiplication;
    TextView division, clear, percentage, openP, closeP;
    TextView sin, cos, tan, ln, log, factorial;
    TextView pi, euler, power, root;
    TextView result;
    ImageView backspace;
    boolean isEqualJustBeenPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setListeners();
        isEqualJustBeenPressed = false;
    }

    private void initViews() {
        result = findViewById(R.id.result);
        result.setMovementMethod(new ScrollingMovementMethod());

        number0 = findViewById(R.id.number0);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        number3 = findViewById(R.id.number3);
        number4 = findViewById(R.id.number4);
        number5 = findViewById(R.id.number5);
        number6 = findViewById(R.id.number6);
        number7 = findViewById(R.id.number7);
        number8 = findViewById(R.id.number8);
        number9 = findViewById(R.id.number9);
        point = findViewById(R.id.point);
        equal = findViewById(R.id.equal);
        addition = findViewById(R.id.plus);
        subtraction = findViewById(R.id.minus);
        multiplication = findViewById(R.id.multiplication);
        division = findViewById(R.id.division);
        clear = findViewById(R.id.clear);
        percentage = findViewById(R.id.percentage);
        openP = findViewById(R.id.openP);
        closeP = findViewById(R.id.closeP);
        backspace = findViewById(R.id.backspace);
        sin = findViewById(R.id.sin);
        cos = findViewById(R.id.cos);
        tan = findViewById(R.id.tan);
        ln = findViewById(R.id.ln);
        log = findViewById(R.id.log);
        factorial = findViewById(R.id.factorial);
        pi = findViewById(R.id.pi);
        euler = findViewById(R.id.euler);
        power = findViewById(R.id.exponential);
        root = findViewById(R.id.root);
    }

    private void setListeners() {
        result.setOnLongClickListener(this);
        number0.setOnClickListener(this);
        number1.setOnClickListener(this);
        number2.setOnClickListener(this);
        number3.setOnClickListener(this);
        number4.setOnClickListener(this);
        number5.setOnClickListener(this);
        number6.setOnClickListener(this);
        number7.setOnClickListener(this);
        number8.setOnClickListener(this);
        number9.setOnClickListener(this);
        point.setOnClickListener(this);
        equal.setOnClickListener(this);
        addition.setOnClickListener(this);
        subtraction.setOnClickListener(this);
        multiplication.setOnClickListener(this);
        division.setOnClickListener(this);
        clear.setOnClickListener(this);
        percentage.setOnClickListener(this);
        openP.setOnClickListener(this);
        closeP.setOnClickListener(this);
        backspace.setOnClickListener(this);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            //Do some stuff
            sin.setOnClickListener(this);
            cos.setOnClickListener(this);
            tan.setOnClickListener(this);
            ln.setOnClickListener(this);
            log.setOnClickListener(this);
            factorial.setOnClickListener(this);
            pi.setOnClickListener(this);
            euler.setOnClickListener(this);
            power.setOnClickListener(this);
            root.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Log.d("Log.d", String.valueOf(v.getId()));
        switch (v.getId()) {
            case R.id.number0:
                appendNumberToResult("0");
                break;
            case R.id.number1:
                appendNumberToResult("1");
                break;
            case R.id.number2:
                appendNumberToResult("2");
                break;
            case R.id.number3:
                appendNumberToResult("3");
                break;
            case R.id.number4:
                appendNumberToResult("4");
                break;
            case R.id.number5:
                appendNumberToResult("5");
                break;
            case R.id.number6:
                appendNumberToResult("6");
                break;
            case R.id.number7:
                appendNumberToResult("7");
                break;
            case R.id.number8:
                appendNumberToResult("8");
                break;
            case R.id.number9:
                appendNumberToResult("9");
                break;
            case R.id.point:
                appendPointToResult();
                break;
            case R.id.equal:
                try {
                    isEqualJustBeenPressed = true;
                    result.setText(evaluate(result.getText().toString()));
                } catch (ScriptException e) {
                    result.setText("E");
                    e.printStackTrace();
                }
                break;
            case R.id.plus:
                appendBasicSymbolToResult("+");
                break;
            case R.id.minus:
                appendBasicSymbolToResult("-");
                break;
            case R.id.multiplication:
                appendBasicSymbolToResult("*");
                break;
            case R.id.division:
                appendBasicSymbolToResult("/");
                break;
            case R.id.clear:
                clearResult();
                break;
            case R.id.percentage:
                try {
                    isEqualJustBeenPressed = true;
                    result.setText(evaluate(result.getText().toString()+"/100"));
                } catch (ScriptException e) {
                    result.setText("E");
                    e.printStackTrace();
                }
                break;
            case R.id.closeP:
                appendParanthesisToResult(")");
                break;
            case R.id.openP:
                appendParanthesisToResult("(");
                break;
            case R.id.backspace:
                backspace();
                break;
            case R.id.cos:
                break;
            case R.id.sin:
                break;
            case R.id.tan:
                break;
            case R.id.ln:
                break;
            case R.id.log:
                break;
            case R.id.factorial:
                break;
            case R.id.pi:
                break;
            case R.id.euler:
                break;
            case R.id.exponential:
                break;
            case R.id.root:
                break;
        }
    }

    private void backspace() {
        isEqualJustBeenPressed = false;
        if (resultIsZero() || resultIsOneDigit()){
            clearResult();
            return;
        }
        String txt = result.getText().toString();
        result.setText(txt.substring(0, txt.length()-1));
    }

    private void clearResult() {
        isEqualJustBeenPressed = false;
        result.setText("0");
    }

    private boolean resultIsZero() {
        return result.getText().toString().equals("0");
    }

    private boolean resultIsOneDigit() {
        return result.getText().length() == 1;
    }

    private void appendNumberToResult(String number) {
        if (isEqualJustBeenPressed)
            clearResult();
        isEqualJustBeenPressed = false;
        if (resultIsError())
            return;
        if (resultIsZero())
            result.setText(number);
        else
            result.append(number);
    }

    private void appendPointToResult() {
        isEqualJustBeenPressed = false;
        if (resultIsError())
            return;
        result.append(".");
    }

    private void appendBasicSymbolToResult(String symbol) {
        isEqualJustBeenPressed = false;
        if (resultIsError() || resultIsZero())
            return;
        result.append(symbol);
    }

    // ToDo: check this and finish
    private void appendParanthesisToResult(String symbol) {
        if (resultIsZero() && symbol.equals(")")) {
            isEqualJustBeenPressed = false;
            return;
        }
        if (resultIsZero() && symbol.equals("(") || isEqualJustBeenPressed) {
            isEqualJustBeenPressed = false;
            result.setText(symbol);
            return;
        }
        result.append(symbol);

    }

    private boolean resultIsError() {
        return result.getText().toString().equals("E");
    }

    // This method uses a Javascript engine to evaluate the operations
    private String evaluate(String equation) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        equation = checkForSymbols(equation);
        Object result = engine.eval(equation);
        String format = String.valueOf(result);
        if (format.charAt(format.length()-1) == '0')
            return format.substring(0, format.length()-2);
        return result.toString();
    }

    private String checkForSymbols(String equation) {
        // This regex will match any numbers or symbols before an opening parenthesis
        // The first regex help the calculator work with stuff like 2(2)(2)(2) --> 2*(2)*(2)*(2)
        // The second one helps with (-3+5)(4-2) --> (-3+5)*(4-2)
        // And the third one with (4+2)2 --> (4+2)*2
        String str = equation.replaceAll("(\\d+)(\\([+-]?\\d+)", "$1*$2");
        str = str.replaceAll("\\)\\(", ")*(");
        str = str.replaceAll("(.+\\))(\\d+)", "$1*$2");
        Log.d("Log.d","str: " + str);
        Log.d("Log.d","equation: " + equation);
        return str;
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.result){
            ClipboardManager cm = (ClipboardManager)getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Result", result.getText().toString());
            cm.setPrimaryClip(clip);
            Toast.makeText(getApplicationContext(), "Text copied to clipboard", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
