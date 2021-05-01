package sg.edu.rp.c346.julien.classjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DailyAdapter extends ArrayAdapter<DailyCA> {
    private ArrayList<DailyCA> dailyCA;
    private Context context;
    private TextView tvWeek, tvDG, tvGrade;

    public DailyAdapter(Context context, int resource, ArrayList<DailyCA> objects){
        super(context, resource, objects);
        dailyCA = objects;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);

        tvWeek = (TextView) rowView.findViewById(R.id.tvweek);
        tvDG = (TextView) rowView.findViewById(R.id.tvDG);
        tvGrade = (TextView) rowView.findViewById(R.id.tvGrade);

        DailyCA currentCA = dailyCA.get(position);
        tvWeek.setText("Week: " + (position + 1));
        tvGrade.setText(currentCA.getDgGrade());

        return rowView;
    }
}
