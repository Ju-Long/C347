package sg.edu.rp.c346.julien.classjournal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ModuleInfoActivity extends AppCompatActivity {

    ListView lv;
    DailyAdapter aa;
    ArrayList<DailyCA> dca;
    Button info, btnAdd;
    Button email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_info);

        Intent intent = getIntent();
        Module module = (Module) intent.getSerializableExtra("module");

        lv = (ListView) this.findViewById(R.id.lvDaily);
        info = findViewById(R.id.btnInfo);
        email=findViewById(R.id.btnEmail);
        btnAdd = (Button) findViewById(R.id.btnAddWeek);
        dca = new ArrayList<DailyCA>();
        dca.add(new DailyCA("B", 1));
        dca.add(new DailyCA("C", 2));
        dca.add(new DailyCA("A", 3));

        aa = new DailyAdapter(this, R.layout.row, dca);
        lv.setAdapter(aa);

        info.setOnClickListener(v -> {
            Intent rpIntent = new Intent(Intent.ACTION_VIEW);
            rpIntent.setData(Uri.parse("https://www.rp.edu.sg/soi/full-time-diplomas/details/diploma-in-digital-design-and-development"));
            startActivity(rpIntent);
        });

        email.setOnClickListener(v -> {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL,new String[]{"jason_lim@rp.edu.sg"});

            String text = "";
            text = "Hi faci, \n" +"I am your student \n" +"Please see my remarks so far, thank you! \n ";
            for(int i =0;i<dca.size();i++){
                text += "Week " + dca.get(i).getWeek() + ": DG: " +dca.get(i).getDgGrade() + "\n";
                email.putExtra(Intent.EXTRA_TEXT, text);
            }
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Choose an Email client :"));
        });

        btnAdd.setOnClickListener(v -> {
            Intent i = new Intent(ModuleInfoActivity.this, AddInfoActivity.class);
            int weekNumber = dca.size();
            i.putExtra("currWk", weekNumber);
            startActivityForResult(i, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                int week = data.getIntExtra("week", 0);
                String grade = data.getStringExtra("grade");
                dca.add(new DailyCA(grade, week));

                aa.notifyDataSetChanged();
            }
        }
    }
}
