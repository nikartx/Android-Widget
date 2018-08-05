package ru.nikartm.support.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.nikartm.support.R;
import ru.nikartm.support.widget.CollapsingTitleLayout;

/**
 * A simple example of using {@link ru.nikartm.support.widget.CollapsingTitleLayout}
 */
public class CollapsingTitleFragment extends Fragment {

    @BindView(R.id.titleLayout)
    protected CollapsingTitleLayout titleLayout;

    public CollapsingTitleFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collapsing_title, container, false);
        ButterKnife.bind(this, view);
//        initTitles();
        return view;
    }

    private void initTitles() {
        titleLayout.setTitle(getString(R.string.title))
                .setSubTitle(getString(R.string.subtitle))
                .setTitleColor(getResources().getColor(R.color.colorAccent))
                .setSubTitleColor(getResources().getColor(R.color.colorAccent))
                .setTitleSize(40)
                .setSubTitleSize(16);
    }

}
