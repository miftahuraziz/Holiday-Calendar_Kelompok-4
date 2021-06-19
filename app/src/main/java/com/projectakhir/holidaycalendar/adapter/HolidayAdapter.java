package com.projectakhir.holidaycalendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.projectakhir.holidaycalendar.R;
import com.projectakhir.holidaycalendar.model.HolidaysItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.ViewHolder> {

    private ArrayList<HolidaysItem> holidaysItems = new ArrayList<>();
    private ArrayList<Integer> listColor = new ArrayList<>();
    private Context context;
    
    public void setData(Context context, ArrayList<HolidaysItem> item) {
        this.context = context;

        holidaysItems.clear();
        holidaysItems.addAll(item);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(context, R.color.biru));
        colors.add(ContextCompat.getColor(context, R.color.merah));
        colors.add(ContextCompat.getColor(context, R.color.hijau));
        colors.add(ContextCompat.getColor(context, R.color.ungu));
        colors.add(ContextCompat.getColor(context, R.color.kuning));

        int posColor = 0;
        for (int i = 0; i < holidaysItems.size(); i++) {
            if (posColor >= colors.size()) {
                posColor = 0;
            }
            listColor.add(colors.get(posColor));
            posColor++;
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvHolidayName.setText(holidaysItems.get(position).getName());

        Locale locale = Locale.ENGLISH;
        String sDate = holidaysItems.get(position).getDate();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd", locale).parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String fullDate = new SimpleDateFormat("EEEE, dd MMMM yyyy", locale).format(date);
        String dateNumber = new SimpleDateFormat("dd", locale).format(date);
        String day = new SimpleDateFormat("EEEE", locale).format(date);
        String month = new SimpleDateFormat("MMMM", locale).format(date);

        holder.tvFullDate.setText(fullDate);
        holder.tvDateNumber.setText(dateNumber);
        holder.tvDay.setText(day);
        holder.tvMonth.setText(month);

        holder.dateBox.setBackgroundColor(listColor.get(position));
        holder.tvHolidayName.setTextColor(listColor.get(position));
    }

    @Override
    public int getItemCount() {
        return holidaysItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDateNumber, tvMonth, tvHolidayName, tvFullDate, tvDay;
        LinearLayout dateBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDateNumber = itemView.findViewById(R.id.item_tv_date_number);
            tvDay = itemView.findViewById(R.id.item_tv_day);
            tvMonth = itemView.findViewById(R.id.item_tv_month);
            tvHolidayName = itemView.findViewById(R.id.item_tv_holiday_name);
            tvFullDate = itemView.findViewById(R.id.item_tv_full_date);
            
            dateBox = itemView.findViewById(R.id.item_date_box);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, tvHolidayName.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
