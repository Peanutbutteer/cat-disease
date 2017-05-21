package com.iampeanut.cat;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by satjawatpanakarn on 5/2/2017 AD.
 */

public class PlaceholderFragment extends Fragment {

    private static final String ARG_PROBLEM_LIST = "ARG_PROBLEM_LIST";
    private static final String ARG_QUESTION = "ARG_QUESTION";
    private static final String ARG_GIF = "ARG_GIF";
    private ProblemListAdapter problemListAdapter;
    private RecyclerView rvProblem;
    private List<Problem> problems;
    private ImageView ivGif;
    private TextView tvQuestion;

    public PlaceholderFragment() {
    }

    public static PlaceholderFragment newInstance(List<Problem> problems, String question, int image) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        Parcelable listParcelable = Parcels.wrap(problems);
        args.putParcelable(ARG_PROBLEM_LIST, listParcelable);
        args.putInt(ARG_GIF, image);
        args.putString(ARG_QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        rvProblem = (RecyclerView) rootView.findViewById(R.id.rv_problems);
        tvQuestion = (TextView) rootView.findViewById(R.id.tv_question);
        ivGif = (ImageView) rootView.findViewById(R.id.iv_gif);
        Parcelable parcelable = getArguments().getParcelable(ARG_PROBLEM_LIST);
        problems = Parcels.unwrap(parcelable);
        String question = getArguments().getString(ARG_QUESTION);
        if (question != null) {
            tvQuestion.setText(question);
        }
        int image = getArguments().getInt(ARG_GIF, 0);
        if (image != 0) {
            Glide.with(getContext()).load(image).asGif().into(ivGif);
        }
        problemListAdapter = new ProblemListAdapter(getContext());
        rvProblem.setLayoutManager(new LinearLayoutManager(getContext()));
        rvProblem.setAdapter(problemListAdapter);
        problemListAdapter.setProblemList(problems);
        return rootView;
    }

    public List<Problem> getProblems() {
        return problemListAdapter.getProblemList();
    }
}