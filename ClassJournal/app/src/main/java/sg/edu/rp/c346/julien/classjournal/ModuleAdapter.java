package sg.edu.rp.c346.julien.classjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ModuleAdapter extends ArrayAdapter<Module> {

    private ArrayList<Module> modules;
    private Context context;
    TextView tvcode, tvname;

    public ModuleAdapter(Context context, int resource, ArrayList<Module> objects){
        super(context, resource, objects);
        modules = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.modulerow, parent, false);

        tvcode = (TextView) rowView.findViewById(R.id.moduleCode);
        tvname = (TextView) rowView.findViewById(R.id.moduleName);

        tvcode.setText(modules.get(position).getModuleCode());
        tvname.setText(modules.get(position).getModuleName());
        return rowView;
    }
}

