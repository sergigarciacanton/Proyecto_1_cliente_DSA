package com.example.dsa;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dsa.models.Game;
import java.util.List;

public class AdapterStatistics extends RecyclerView.Adapter<AdapterStatistics.ViewHolder> {
    private List<Game> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
        }
    }

    public void setData(List<Game> myData) {
        values = myData;
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterStatistics.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout_no_images, parent, false);
        // set the view's size, margins, padding and layout parameters
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your data at this position
        // - replace the contents of the view with that element
        Game c = values.get(position);
        final int gameId = c.getId();
        holder.txtHeader.setText("Game ID: " + gameId);
        final int victory = c.getVictory();
        if(victory == 0) holder.txtFooter.setText("Result: You lost");
        else holder.txtFooter.setText("Result: You won");
        holder.txtHeader.setOnClickListener(v -> {
            int i = 0;
            boolean found = false;
            while(i < values.size() && !found) {
                if(values.get(i).getId() == Integer.parseInt(String.valueOf(holder.txtHeader.getText()).split(" ")[2])) found = true;
                else i++;
            }
            if(found) {
                Intent intent = new Intent(v.getContext(), Activity_Statistics_Specifications.class);
                intent.putExtra("id", String.valueOf(values.get(i).getId()));
                intent.putExtra("duration", String.valueOf(values.get(i).getDuration()));
                intent.putExtra("victory", String.valueOf(values.get(i).getVictory()));
                intent.putExtra("score", String.valueOf(values.get(i).getScore()));
                v.getContext().startActivity(intent);
            }
        });
    }

    // Return the size of your data (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(values == null) return 0;
        else return values.size();
    }
}