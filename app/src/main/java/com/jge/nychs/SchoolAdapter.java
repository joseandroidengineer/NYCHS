package com.jge.nychs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchoolAdapterViewHolder> {
    private List<School> schools;
    private final ListItemClickListener mOnClickListener;


    public SchoolAdapter(ListItemClickListener mOnClickListener){
        this.mOnClickListener = mOnClickListener;

    }

    public interface ListItemClickListener{
        void onListItemClick(School schoolNameIndexClicked);
    }



    public class SchoolAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView schoolNameTextView;
        public TextView schoolBoroughTextView;

        public SchoolAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            schoolNameTextView = itemView.findViewById(R.id.school_name);
            schoolBoroughTextView = itemView.findViewById(R.id.borough);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mOnClickListener.onListItemClick(schools.get(position));
        }
    }

    @Override
    public SchoolAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.school_item_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new SchoolAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolAdapterViewHolder schoolAdapterViewHolder, int i) {
        School school = schools.get(i);
        schoolAdapterViewHolder.schoolNameTextView.setText(school.getSchoolName());
        schoolAdapterViewHolder.schoolBoroughTextView.setText(school.getBorough());
    }

    @Override
    public int getItemCount() {
        if(schools == null) return 0;
        return schools.size();
    }
    public void setSchoolData(List<School> schoolData){
        schools = schoolData;
        notifyDataSetChanged();
    }


}
