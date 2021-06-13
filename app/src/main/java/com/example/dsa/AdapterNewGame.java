package com.example.dsa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dsa.models.FullObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterNewGame extends RecyclerView.Adapter<AdapterNewGame.ViewHolder> {
    private List<FullObject> values;
    Activity_New_Game activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView icon;
        public View layout;
        public Button button;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            icon = v.findViewById(R.id.icon);
            button = v.findViewById(R.id.button);
        }
    }

    public void setData(List<FullObject> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public AdapterNewGame(Activity_New_Game activity) {
        values = new ArrayList<>();
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterNewGame.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout_button, parent, false);
        // set the view's size, margins, padding and layout parameters
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your data at this position
        // - replace the contents of the view with that element
        FullObject c = values.get(position);
        final String name = c.getName();
        holder.txtHeader.setText(name);
        holder.txtFooter.setText("ATK: " + c.getAttack() + "%  DEF: " + c.getDefense() + "%  HP: " + c.getLife() + "%");
        holder.button.setText("Use");
        Picasso.with(holder.icon.getContext()).load(c.getImageURL()).into(holder.icon);
        holder.button.setOnClickListener(v -> {
            if(holder.button.getText().equals("Use")) {
                holder.button.setText("Discard");
                int i = 0;
                boolean found = false;
                while (i < activity.objectsList.size() && !found) {
                    if(activity.objectsList.get(i).getName().contentEquals(holder.txtHeader.getText()))
                        found = true;
                    else
                        i++;
                }
                if(found) activity.objectsIdList.add(activity.objectsList.get(i).getId());
            }
            else {
                holder.button.setText("Use");
                int i = 0;
                boolean found = false;
                while (i < activity.objectsList.size() && !found) {
                    if(activity.objectsList.get(i).getName().contentEquals(holder.txtHeader.getText()))
                        found = true;
                    else
                        i++;
                }
                if(found) {
                    int idDelete = activity.objectsList.get(i).getId();
                    int j = 0;
                    boolean found2 = false;
                    while (j < activity.objectsIdList.size() && !found2) {
                        if(activity.objectsIdList.get(j).equals(idDelete))
                            found2 = true;
                        else
                            j++;
                    }
                    if(found2) {
                        int res = activity.objectsIdList.remove(j);
                    }
                }
            }
        });
    }

    // Return the size of your data (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }
}