package sg.edu.rp.c346.julien.classjournal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class AddInfoActivity extends AppCompatActivity {

    Button btnSubmit;
    RadioGroup rdGrade;
    RadioButton rbGrade;
    TextView tvWeek;
    String choice = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnSubmit = (Button) findViewById(R.id.buttonSubmit);
        rdGrade = (RadioGroup) findViewById(R.id.radioGrades);
        tvWeek = (TextView) findViewById(R.id.weekNumber);

        Intent i = getIntent();
        int wkNum = i.getIntExtra("currWk",0) + 1;
        tvWeek.setText("Week " + (wkNum));

        btnSubmit.setOnClickListener(v -> {
            int selectedID = rdGrade.getCheckedRadioButtonId();
            rbGrade = (RadioButton) findViewById(selectedID);
            Intent n = new Intent();
            n.putExtra("grade", rbGrade.getText().toString());
            n.putExtra("week", wkNum);
            setResult(RESULT_OK, n);
            finish();
        });
    }
}
