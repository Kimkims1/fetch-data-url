package com.carldroid.firestorefetch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class AdapterTopCompanies extends RecyclerView.Adapter<AdapterTopCompanies.TopCompaniesHolder> {

    private List<ModelTopCompanies> topCompaniesList;
    private Context context;
    private FirebaseFirestore firestoreDB;

    public AdapterTopCompanies(List<ModelTopCompanies> topCompaniesList, Context context, FirebaseFirestore firestoreDB) {
        this.topCompaniesList = topCompaniesList;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }

    @NonNull
    @Override
    public TopCompaniesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_companies_row, parent, false);
        return new TopCompaniesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopCompaniesHolder holder, int position) {

        final ModelTopCompanies companies = topCompaniesList.get(position);
        holder.uber.setText(companies.getUber());
        holder.google.setText(companies.getGoogle());
        holder.apple.setText(companies.getApple());
        holder.facebook.setText(companies.getFacebook());
        holder.netflix.setText(companies.getNetflix());
    }

    @Override
    public int getItemCount() {
        return topCompaniesList.size();
    }

    class TopCompaniesHolder extends RecyclerView.ViewHolder {

        private TextView google, apple, facebook, uber, netflix;

        public TopCompaniesHolder(@NonNull View itemView) {
            super(itemView);

            google = itemView.findViewById(R.id.googleTv);
            apple = itemView.findViewById(R.id.appleTv);
            facebook = itemView.findViewById(R.id.facebookTv);
            uber = itemView.findViewById(R.id.uberTv);
            netflix = itemView.findViewById(R.id.netflixTv);
        }
    }
}
