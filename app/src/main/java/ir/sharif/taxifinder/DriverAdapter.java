package ir.sharif.taxifinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ir.sharif.taxifinder.webservice.webservices.drivers.Driver;

import java.util.ArrayList;
import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverViewHolder> {

    private Context mContext;
    private List<Driver> drivers;

    public DriverAdapter(Context context, List<Driver> drivers) {
        mContext = context;
        this.drivers = drivers;
    }

    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.group_view, null);
        return new DriverViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
        Driver driver = drivers.get(position);
        holder.bind(driver);
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers.clear();
        this.drivers.addAll(drivers);
    }
}