package com.azimolabs.mobile.aftermobileinternship.repositorieslist.repositorydetails;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.azimolabs.mobile.aftermobileinternship.R;
import com.azimolabs.mobile.aftermobileinternship.github.RepositoryItem;
import com.azimolabs.mobile.aftermobileinternship.utils.RepositoryDetailsFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepositoryDetailsDialog extends AppCompatDialogFragment {
    public static final String TAG = "RepositoryDetailsDialog";

    private static final String ARG_REPOSITORY = "arg_repository";

    @BindView(R.id.tvRepoName)
    TextView tvRepoName;
    @BindView(R.id.tvRepoDescription)
    TextView tvRepoDescription;
    @BindView(R.id.tvRepoLanguage)
    TextView tvRepoLanguage;

    RepositoryItem repository;

    public static RepositoryDetailsDialog newInstance(RepositoryItem repository) {
        RepositoryDetailsDialog dialog = new RepositoryDetailsDialog();

        Bundle args = new Bundle();
        args.putSerializable(ARG_REPOSITORY, repository);
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View dialogView = inflater.inflate(R.layout.dialog_repository_details, null);
        ButterKnife.bind(this, dialogView);

        repository = (RepositoryItem) getArguments().getSerializable(ARG_REPOSITORY);

        tvRepoName.setText(repository.getName());
        tvRepoDescription.setText(RepositoryDetailsFormatter.repoDescription(repository.getDescription()));
        tvRepoLanguage.setText(RepositoryDetailsFormatter.repoLanguage(repository.getLanguage()));

        builder.setView(dialogView);
        return builder.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @OnClick(R.id.btnClose)
    public void closeDialog() {
        dismiss();
    }
}
