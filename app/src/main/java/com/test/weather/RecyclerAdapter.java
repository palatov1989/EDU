package com.test.weather;

/**
 * Created by virus on 11.03.2017.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        private ArrayList<WeekCastElement> array;

        public void setList(ArrayList<WeekCastElement> list) {
            array = list;
            notifyDataSetChanged();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView cardID;
            public TextView cardText;

            public ViewHolder(View v) {
                super(v);
                cardID      = (TextView)    v.findViewById(R.id.item_id);
                cardText    = (TextView)    v.findViewById(R.id.item_text);
            }
        }

        public RecyclerAdapter(ArrayList<WeekCastElement> dataset) {
            array = dataset;
        }
        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_view, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.cardID.setText(array.get(position).getDate());
            holder.cardText.setText(array.get(position).getText());
        }

        @Override
        public int getItemCount() {
            return array.size();
        }
}
