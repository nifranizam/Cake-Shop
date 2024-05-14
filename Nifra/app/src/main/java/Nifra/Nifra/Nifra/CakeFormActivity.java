package Nifra.Nifra.Nifra;

import static Nifra.Nifra.Nifra.config.Validation.DecimalPointChecker;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import Nifra.Nifra.Nifra.config.Validation;
import uom.aysha.tha_204013d.R;

public class CakeFormActivity extends AppCompatActivity {
    TextInputEditText input_title;
    TextInputEditText input_desc;
    TextInputEditText input_price;
    Button btnUpdate;
    Button btnDel;
    Button btnAdd;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_form);

        input_title = findViewById(R.id.input_item_name);
        input_desc = findViewById(R.id.input_item_desc);
        input_price = findViewById(R.id.input_item_price);
        btnUpdate = findViewById(R.id.btn_update);
        btnDel = findViewById(R.id.btn_delete);
        btnAdd = findViewById(R.id.btn_Add);

        input_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String string1 = Objects.requireNonNull(input_price.getText()).toString();
                if(string1.isEmpty()) return;
                String string2 = DecimalPointChecker(string1, 2);

                if(!string2.equals(string1)){
                    input_price.setText(string2);
                    input_price.setSelection(string2.length());
                }
            }
        });

        Intent i = getIntent();
        if(i.getExtras().getInt("type")==1){
            btnUpdate.setVisibility(View.GONE);
            btnDel.setVisibility(View.GONE);
            btnAdd.setVisibility(View.VISIBLE);
            btnAdd.setOnClickListener(x -> {
                try{
                    Validation.InputsChecker(Objects.requireNonNull(input_title.getText()).toString(), Objects.requireNonNull(input_desc.getText()).toString(), Objects.requireNonNull(input_price.getText()).toString());
                    Intent intent = new Intent();
                    intent.putExtra("ProductTitle", input_title.getText().toString());
                    intent.putExtra("ProductDesc", input_desc.getText().toString());
                    intent.putExtra("ProductPrice", Double.valueOf(input_price.getText().toString()));
                    setResult(1,intent);
                    finish();
                }catch (RuntimeException e){
                    alert = new AlertDialog.Builder(this);
                    alert.setTitle("Cannot Proceed");
                    alert.setMessage(e.getMessage());
                    alert.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
                    alert.show();
                }
            });
        }else{
            input_title.setText(i.getExtras().getString("ProductTitle"));
            input_desc.setText(i.getExtras().getString("ProductDesc"));
            input_price.setText(String.valueOf(i.getExtras().getDouble("ProductPrice")));
            btnUpdate.setOnClickListener(x->{
                try{
                    Validation.InputsChecker(Objects.requireNonNull(input_title.getText()).toString(), Objects.requireNonNull(input_desc.getText()).toString(), Objects.requireNonNull(input_price.getText()).toString());
                    Intent intent = new Intent();
                    intent.putExtra("ProductTitle", input_title.getText().toString());
                    intent.putExtra("ProductDesc", input_desc.getText().toString());
                    intent.putExtra("ProductPrice", Double.valueOf(input_price.getText().toString()));
                    intent.putExtra("Position", getIntent().getIntExtra("Position", 0));
                    intent.putExtra("ID", getIntent().getLongExtra("ID", 0));
                    setResult(2,intent);
                    finish();
                }catch (Exception e){
                    alert = new AlertDialog.Builder(this);
                    alert.setTitle("Cannot Proceed");
                    alert.setMessage(e.getMessage());
                    alert.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
                    alert.show();
                }
            });

            btnDel.setOnClickListener(x -> {
                alert = new AlertDialog.Builder(this);
                alert.setTitle("Warning");
                alert.setMessage("Do you want delete this item? This action cannot be undone");
                alert.setPositiveButton("Yes", (dialog, which) -> {
                    Intent intent = new Intent();
                    intent.putExtra("Position", getIntent().getIntExtra("Position", 0));
                    setResult(3, intent);
                    finish();
                    dialog.dismiss();
                });
                alert.setNegativeButton("No", (dialog, which)-> dialog.dismiss());
                alert.show();

            });
        }
    }
}
