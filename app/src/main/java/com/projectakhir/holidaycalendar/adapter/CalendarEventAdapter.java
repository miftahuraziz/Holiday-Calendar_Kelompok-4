package com.projectakhir.holidaycalendar.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projectakhir.holidaycalendar.R;
import com.projectakhir.holidaycalendar.database.AppDatabase;
import com.projectakhir.holidaycalendar.database.CalendarEventModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CalendarEventAdapter extends RecyclerView.Adapter<CalendarEventAdapter.ViewHolder> {
    private Context context;
    private ArrayList<CalendarEventModel> calendarEventItems = new ArrayList<>();
    private AppDatabase appDatabase;

    public CalendarEventAdapter(Context context) {
        this.context = context;
        appDatabase = AppDatabase.database(this.context);
    }

    public void setData(ArrayList<CalendarEventModel> items) {
        calendarEventItems.clear();
        calendarEventItems.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CalendarEventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_calendar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarEventAdapter.ViewHolder holder, int position) {
        holder.tvDate.setText(calendarEventItems.get(position).getDate());
        holder.tvEventName.setText(calendarEventItems.get(position).getEventName());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CalendarEventModel calendarEventModel = new CalendarEventModel();
                    calendarEventModel.setId(calendarEventItems.get(position).getId());
                    calendarEventModel.setDate(calendarEventItems.get(position).getDate());
                    calendarEventModel.setEventName(calendarEventItems.get(position).getEventName());

                    appDatabase.calendarEventDAO().deleteCalendarEvent(calendarEventModel);
                    Log.d("CalendarFragment", "Delete Event Succeed");
                    Toast.makeText(context, "Delete Event Succeed", Toast.LENGTH_SHORT).show();
                }catch (Exception ex) {
                    Log.e("CalendarFragment", "Delete Event Failed, msg: "+ex.getMessage());
                    Toast.makeText(context, "Delete Event Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return calendarEventItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvEventName;
        Button btnDelete;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.item_tv_date);
            tvEventName = itemView.findViewById(R.id.item_tv_event);
            btnDelete = itemView.findViewById(R.id.btn_delete_event);
        }
    }
}
