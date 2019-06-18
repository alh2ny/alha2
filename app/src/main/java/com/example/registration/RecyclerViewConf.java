package com.example.registration;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewConf {
    private Context mContext;
    private FormAdapter mFormAdapter;
    public void setconfig(RecyclerView recyclerView,Context context,List<Requests> requests,List<String> keys){
        mContext = context;
        mFormAdapter = new FormAdapter(requests,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mFormAdapter);
    }

    class FormItemView extends RecyclerView.ViewHolder {
        private TextView textViewNumber;
        private TextView textViewComment;
        private TextView textViewLongitude;
        private TextView textViewAttribute;

        private String key;

        public FormItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.form_list_item, parent, false));

            textViewNumber = (TextView) itemView.findViewById(R.id.textViewNumber);
            textViewComment = (TextView) itemView.findViewById(R.id.textViewComment);
            textViewLongitude = (TextView) itemView.findViewById(R.id.textViewLongitude);
            textViewAttribute = (TextView) itemView.findViewById(R.id.textViewAttribute);
        }

        public void bind(Requests form, String key) {
            textViewNumber.setText(form.getNumber());
            textViewComment.setText(form.getComment());
            textViewLongitude.setText(form.getLongitude());
            textViewAttribute.setText(form.getAttribute());
            this.key = key;
        }
    }
    class FormAdapter extends RecyclerView.Adapter<FormItemView>{
        private List<Requests> mFormList;
        private List<String> mKeys;

        public FormAdapter(List<Requests> mFormList, List<String> mKeys) {
            this.mFormList = mFormList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public FormItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new FormItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull FormItemView holder, int position) {
            holder.bind(mFormList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mFormList.size();
        }
    }
}
