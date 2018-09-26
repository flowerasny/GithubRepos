package com.azimolabs.mobile.aftermobileinternship.repositorieslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azimolabs.mobile.aftermobileinternship.R;
import com.azimolabs.mobile.aftermobileinternship.github.RepositoryItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoriesListAdapter extends RecyclerView.Adapter<RepositoriesListAdapter.RepositoryViewHolder> {

    private Context context;
    private OnRepositoryItemClickListener host;
    private List<RepositoryItem> repositories;

    @Inject
    public RepositoriesListAdapter(RepositoriesListActivity context, List<RepositoryItem> repositories) {
        this.context = context;
        this.host = context;
        this.repositories = repositories;
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_repository, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        RepositoryItem repository = repositories.get(position);

        holder.repository = repository;
        holder.name.setText(repository.getName());
        holder.watchers.setText(String.valueOf(repository.getIssues()));
        holder.stars.setText(String.valueOf(repository.getStars()));
        holder.forks.setText(String.valueOf(repository.getForks()));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }


    public class RepositoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private RepositoryItem repository;

        @BindView(R.id.tv_repo_name)
        TextView name;
        @BindView(R.id.tv_watchers)
        TextView watchers;
        @BindView(R.id.tv_stars)
        TextView stars;
        @BindView(R.id.tv_forks)
        TextView forks;

        RepositoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            host.onItemClicked(repository);
        }
    }

    public interface OnRepositoryItemClickListener {

        void onItemClicked(RepositoryItem repository);
    }
}
