package company.my.lesson10;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> list;
    TextView text;
    ArrayAdapter<String> adapter;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner spinner = findViewById(R.id.spinner);
        editText = ((TextInputLayout)findViewById(R.id.textInputLayout)).getEditText();
        text = findViewById(R.id.text);
        // Заполняем массив значениями типа String
        list = new ArrayList<>();
        list.add("Android Studio");
        list.add("Activity");
        list.add("Layouts");

        // Создаём объект типа ArrayAdapter для показа содержимого массива в списке элементов Spinner
        // В качестве макета для элементов создаём файл макета и затем выбираем через класс R в качестве
        // макета для элементов списка нашего Spinner-a
        adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_item, list);

        // Привязываем Adapter к нашему Spinner-у
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text.setText(list.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Эта функция пока нам не нужна и вызывается когда ни один элемент не выбран из списка
            }
        });


        Button clickme = findViewById(R.id.button);
        clickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (editText.getText().length() > 0)
                {
                    // Добавляем новые элементы в массив
                    list.add(editText.getText().toString());
                    // Обновляем прикрепленный к Spinner-у список
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Введите текст", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
