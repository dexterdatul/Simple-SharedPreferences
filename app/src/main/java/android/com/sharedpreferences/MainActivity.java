package android.com.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = (CheckBox) findViewById(R.id.checkBox);

        checkBox.setOnCheckedChangeListener(MainActivity.this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        if (isChecked) {
            checkBox.setText("The CheckBox is Checked");
        } else {
            checkBox.setText("The CheckBox is Not Checked");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(getApplicationContext(), "The onPause Method is Called", Toast.LENGTH_LONG).show();

        SharedPreferences sharedPreferences = getPreferences(0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("CHECKBOX_TEXT_VALUE", checkBox.getText().toString());
        editor.putBoolean("CHECKBOX_VALUE", checkBox.isChecked());
        editor.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(), "The onResume Method is Called", Toast.LENGTH_LONG).show();

        SharedPreferences sharedPreferences = getPreferences(0);
        boolean checkboxValue = sharedPreferences.getBoolean("CHECKBOX_VALUE", false);
        String checkboxStringValue = sharedPreferences.getString("CHECKBOX_TEXT_VALUE", "This is my CheckBox");
        checkBox.setChecked(checkboxValue);
        checkBox.setText(checkboxStringValue);

    }

}
