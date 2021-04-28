package sg.edu.rp.c346.julien.classjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvModule;
    ArrayList<Module> al;
    ModuleAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvModule = (ListView) findViewById(R.id.listViewModule);
        al = new ArrayList<>();
        al.add(new Module("C302", "Web Service"));
        al.add(new Module("C347", "Android Programming II"));
        aa = new ModuleAdapter(this, R.layout.modulerow, al);
        lvModule.setAdapter(aa);

        lvModule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ModuleInfoActivity.class);
                intent.putExtra("module", al.get(position));
                startActivity(intent);
            }
        });


    }
}