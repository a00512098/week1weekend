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
    TextView operations, result;
    ImageView backspace;
    boolean isEqualJustBeenPressed;
    ScriptEngine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // This is a Javascript engine. It will handle all my operations.
        engine = new ScriptEngineManager().getEngineByName("rhino");
        initViews();
        setListeners();
        isEqualJustBeenPressed = false;
    }

    private void initViews() {
        operations = findViewById(R.id.operations);
        operations.setMovementMethod(new ScrollingMovementMethod());
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
        operations.setOnLongClickListener(this);
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
                appendNumber("0");
                break;
            case R.id.number1:
                appendNumber("1");
                break;
            case R.id.number2:
                appendNumber("2");
                break;
            case R.id.number3:
                appendNumber("3");
                break;
            case R.id.number4:
                appendNumber("4");
                break;
            case R.id.number5:
                appendNumber("5");
                break;
            case R.id.number6:
                appendNumber("6");
                break;
            case R.id.number7:
                appendNumber("7");
                break;
            case R.id.number8:
                appendNumber("8");
                break;
            case R.id.number9:
                appendNumber("9");
                break;
            case R.id.point:
                appendPoint();
                break;
            case R.id.equal:
                try {
                    isEqualJustBeenPressed = true;
                    result.setText(evaluate(operations.getText().toString()));
                } catch (ScriptException e) {
                    result.setText("E");
                    e.printStackTrace();
                }
                break;
            case R.id.plus:
                appendBasicSymbol("+");
                break;
            case R.id.minus:
                appendBasicSymbol("-");
                break;
            case R.id.multiplication:
                appendBasicSymbol("*");
                break;
            case R.id.division:
                appendBasicSymbol("/");
                break;
            case R.id.clear:
                clear();
                break;
            case R.id.percentage:
                try {
                    isEqualJustBeenPressed = true;
                    result.setText(evaluate("("+operations.getText().toString()+")/100"));
                } catch (ScriptException e) {
                    result.setText("E");
                    e.printStackTrace();
                }
                break;
            case R.id.closeP:
                appendParanthesis(")");
                break;
            case R.id.openP:
                appendParanthesis("(");
                break;
            case R.id.backspace:
                backspace();
                break;
            case R.id.cos:
                appendComplexFunction("cos(");
                break;
            case R.id.sin:
                appendComplexFunction("sin(");
                break;
            case R.id.tan:
                appendComplexFunction("tan(");
                break;
            case R.id.ln:
                appendComplexFunction("lN(");
                break;
            case R.id.log:
                appendComplexFunction("log(");
                break;
            case R.id.factorial:
                appendComplexFunction("!");
                break;
            case R.id.pi:
                appendComplexFunction("m");
                break;
            case R.id.euler:
                appendComplexFunction("e");
                break;
            case R.id.exponential:
                appendComplexFunction("^");
                break;
            case R.id.root:
                appendComplexFunction("{");
                break;
        }
    }

    private void appendComplexFunction(String function) {
        if (isEqualJustBeenPressed)
            clearOperations();
        isEqualJustBeenPressed = false;
        if (resultIsError())
            return;
        if (operationsIsZero())
            operations.setText(function);
        else
            operations.append(function);
    }

    private void backspace() {
        isEqualJustBeenPressed = false;
        if (operationsIsZero() || operationsIsOneDigit()){
            clearOperations();
            return;
        }
        String txt = operations.getText().toString();
        if (txt.length() > 2) {
            char special = txt.charAt(txt.length()-2);
            switch (special) {
                // special function with 3 letters cos(
                case 's':
                case 'g':
                case 'n':
                    operations.setText(txt.substring(0, txt.length()-4));
                    break;
                case 'N':
                    operations.setText(txt.substring(0, txt.length()-3));
                    break;
                case '^':
                case '{':
                    operations.setText(txt.substring(0, txt.length() -2));
                    break;
            }
            if (operations.getText().length() == 0)
                clearOperations();
            return;
        }
        operations.setText(txt.substring(0, txt.length()-1));
    }

    private void clearOperations() {
        isEqualJustBeenPressed = false;
        operations.setText("0");
    }

    private void clear() {
        isEqualJustBeenPressed = false;
        result.setText("0");
        operations.setText("0");
    }

    private boolean operationsIsZero() {
        return operations.getText().toString().equals("0");
    }

    private boolean operationsIsOneDigit() {
        return operations.getText().length() == 1;
    }

    private void appendNumber(String number) {
        if (isEqualJustBeenPressed)
            clearOperations();
        isEqualJustBeenPressed = false;
        if (resultIsError())
            return;
        if (operationsIsZero())
            operations.setText(number);
        else
            operations.append(number);
    }

    private void appendPoint() {
        isEqualJustBeenPressed = false;
        if (resultIsError())
            return;
        operations.append(".");
    }

    private void appendBasicSymbol(String symbol) {
        isEqualJustBeenPressed = false;
        if (resultIsError() || operationsIsZero())
            return;
        operations.append(symbol);
    }

    private void appendParanthesis(String symbol) {
        if (operationsIsZero() && symbol.equals(")")) {
            isEqualJustBeenPressed = false;
            return;
        }
        if (operationsIsZero() && symbol.equals("(") || isEqualJustBeenPressed) {
            isEqualJustBeenPressed = false;
            operations.setText(symbol);
            return;
        }
        operations.append(symbol);

    }

    private boolean resultIsError() {
        return result.getText().toString().equals("E");
    }

    // This method uses a Javascript engine to evaluate the operations
    private String evaluate(String equation) throws ScriptException {
        if (checkForFactorial(equation)){
            return "NOT!";
        }
        equation = checkForSymbols(equation);
        equation = checkForSpecial(equation);
        equation = checkForConstant(equation);
        Object result = engine.eval(equation);
        String format = String.valueOf(result);
        if (format.charAt(format.length()-1) == '0')
            return format.substring(0, format.length()-2);
        return result.toString();
    }

    private boolean checkForFactorial(String equation) {
        boolean hasFactorial = equation.contains("!");

        return hasFactorial;
    }

    private String checkForSpecial(String equation) {
        String str;
        str = equation.replaceAll("sin\\(", "Math.sin(");
        str = str.replaceAll("cos\\(", "Math.cos(");
        str = str.replaceAll("tan\\(", "Math.tan(");
        str = str.replaceAll("log\\(", "Math.log(");
        str = str.replaceAll("lN\\(", "Math.log(");
        str = str.replaceAll("(.+)\\^(.+)", "Math.pow($1,$2)");
        str = str.replaceAll("\\{(.+)", "Math.sqrt($1)");
        return str;
    }

    private String checkForConstant(String equation) {
        String str = equation.replaceAll("(\\d+)m", "$1*m");
        str = str.replaceAll("m(\\d+)", "m*$1");
        str = str.replaceAll("m", "Math.PI");
        str = str.replaceAll("(\\d+)e", "$1*e");
        str = str.replaceAll("e(\\d+)", "e*$1");
        str = str.replaceAll("e", "Math.E");

        return str;
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
        if (v.getId() == R.id.operations || v.getId() == R.id.result){
            ClipboardManager cm = (ClipboardManager)getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Result", operations.getText().toString());
            cm.setPrimaryClip(clip);
            Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
