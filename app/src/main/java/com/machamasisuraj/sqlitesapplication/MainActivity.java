package com.machamasisuraj.sqlitesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.machamasisuraj.sqlitesapplication.dbhelper.DbHelper;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

   private EditText et_word, et_meaning;
   private Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_word = findViewById(R.id.et_word);
        et_meaning=findViewById(R.id.et_meaning);
        btn_save=findViewById(R.id.btn_save);

        final DbHelper dbHelper = new DbHelper(this);
        final SQLiteDatabase sqLiteDatabase =dbHelper.getWritableDatabase();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.insertData(et_word.getText().toString(),et_meaning.getText().toString(),sqLiteDatabase)){
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
