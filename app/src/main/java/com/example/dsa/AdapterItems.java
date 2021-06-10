package com.example.dsa;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dsa.models.FullObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterItems extends RecyclerView.Adapter<AdapterItems.ViewHolder> {
    private List<FullObject> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView icon;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            icon = (ImageView) v.findViewById(R.id.icon);
        }
    }

    public void setData(List<FullObject> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public AdapterItems() {
        values = new ArrayList<>();
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterItems(List<FullObject> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterItems.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        FullObject c = values.get(position);
        final String name = c.getName();
        holder.txtHeader.setText(name);
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                boolean found = false;
                while(i < values.size() && !found) {
                    if(values.get(i).getName().contentEquals(holder.txtHeader.getText())) found = true;
                    else i++;
                }
                if(found) {
                    Intent intent = new Intent(v.getContext(), Activity_My_items_Specifications.class);
                    intent.putExtra("name", String.valueOf(values.get(i).getName()));
                    intent.putExtra("image", String.valueOf(values.get(i).getImageURL()));
                    intent.putExtra("quantity", String.valueOf(values.get(i).getQuantity()));
                    intent.putExtra("life", String.valueOf(values.get(i).getLife()));
                    intent.putExtra("attack", String.valueOf(values.get(i).getAttack()));
                    intent.putExtra("defense", String.valueOf(values.get(i).getDefense()));
                    intent.putExtra("price", String.valueOf(values.get(i).getPrice()));
                    v.getContext().startActivity(intent);
                }
            }
        });

        holder.txtFooter.setText("Quantity: " + c.getQuantity());

        Picasso.with(holder.icon.getContext()).load(c.getImageURL()).into(holder.icon);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}