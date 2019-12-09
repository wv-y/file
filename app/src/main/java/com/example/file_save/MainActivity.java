package com.example.file_save;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.but_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit_name = findViewById(R.id.name);
                EditText edit_num = findViewById(R.id.num);
                String name = edit_name.getText().toString();
                String num = edit_num.getText().toString();
                String data = "姓名：" + name + " 学号：" + num;

                Date dat = new Date();
                String file_name = "data_" + dat.toLocaleString() + ".txt";

                FileOutputStream out = null;
                BufferedWriter writer = null;;

                TextView text = findViewById(R.id.text);

                text.setText(data+file_name);

               /* try {
                    File fs = new File(Environment.getExternalStorageDirectory()+"/msc/" + file_name);
                    FileOutputStream outputStream =new FileOutputStream(fs);
                    outputStream.write(data.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    Toast.makeText(getBaseContext(), "File created successfully", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("higuuy");
                }*/

                //使用java流的方式将data写入文件
                try {
                    out = openFileOutput(file_name, Context.MODE_PRIVATE);

                   writer = new BufferedWriter(new OutputStreamWriter(out));
                   writer.write(data);
                   Toast.makeText(getBaseContext(), "File created successfully", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (writer != null) {
                            writer.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
