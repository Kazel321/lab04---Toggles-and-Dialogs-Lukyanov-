package com.example.lab04_togglesadndialogslukyanov;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.FormatException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox[] chk = new CheckBox[4];
    EditText[] num = new EditText[4];
    EditText[] prcText = new EditText[4];
    Float[] price = new Float[4];
    RadioButton toast, dialog;
    int[] count = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chk[0] = findViewById(R.id.cbApple);
        chk[1] = findViewById(R.id.cbStrawberry);
        chk[2] = findViewById(R.id.cbBlueberry);
        chk[3] = findViewById(R.id.cbPotatoes);

        num[0] = findViewById(R.id.tvApple);
        num[1] = findViewById(R.id.tvStrawberry);
        num[2] = findViewById(R.id.tvBlueberry);
        num[3] = findViewById(R.id.tvPotatoes);

        prcText[0] = findViewById(R.id.prcApple);
        prcText[1] = findViewById(R.id.prcStrawberry);
        prcText[2] = findViewById(R.id.prcBlueberry);
        prcText[3] = findViewById(R.id.prcPotatoes);

        toast = findViewById(R.id.rbToast);
        dialog = findViewById(R.id.rbDialog);
    }

    public void onCalc(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String s_from, rep = "";
        float sum = 0.0f;
        int count = 0;
        float val =0;
        for (int i = 0; i < chk.length; i++)
        {
            if(chk[i].isChecked())
            {
                s_from = num[i].getText().toString();
                if (CheckValue(s_from, 1))
                count = Integer.parseInt(s_from);
                else return;
                s_from = prcText[i].getText().toString();
                if (CheckValue(s_from, 2))
                    price[i] = Float.parseFloat(s_from);
                else return;
                val = count*price[i];
                sum += count * val;
                rep += String.format("%d: %d x %s = %d x %.2f = %.2f\n", i+1, count, chk[i].getText().toString(), count, price[i], val);
            }
        }
        rep += String.format("total: %.2f", sum);
        if (toast.isChecked())
        Toast.makeText(this, rep, Toast.LENGTH_SHORT).show();
        else if (dialog.isChecked())
            builder.setTitle("Result").setMessage(rep).setIcon(R.drawable.result).show();
        else
            Toast.makeText(this, "RadioButton is not selected", Toast.LENGTH_SHORT).show();
    }

    public boolean CheckValue(String s, int type)
    {
        Boolean result = true;
        Float f_from;
        Integer i_from;
        if (s.isEmpty())
        {
            result = false;
            Log.e("empty", "text field is empty");
            Toast.makeText(this, "text field is empty", Toast.LENGTH_SHORT).show();
        }
        else if (type == 1) {
            try {
                i_from = Integer.parseInt(s);
            } catch (Exception e) {
                result = false;
                Log.e("invalid format", "invalid numeric format");
                Toast.makeText(this, "invalid numeric format", Toast.LENGTH_SHORT).show();
            }
        }
        else if (type == 2) {
            try {
                f_from = Float.parseFloat(s);
            } catch (Exception e) {
                result = false;
                Log.e("invalid format", "invalid numeric format");
                Toast.makeText(this, "invalid numeric format", Toast.LENGTH_SHORT).show();
            }
        }
            return result;
    }
}