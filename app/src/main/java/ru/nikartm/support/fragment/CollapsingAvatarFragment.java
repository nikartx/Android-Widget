package ru.nikartm.support.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.nikartm.support.R;

/**
 * A simple example of using {@link ru.nikartm.support.widget.CollapsingViewBehavior}
 */
public class CollapsingAvatarFragment extends Fragment {

    @BindView(R.id.iv_back)
    protected ImageView ivBack;

    public CollapsingAvatarFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collapsing_avatar, container, false);
        ButterKnife.bind(this, view);
        ivBack.setOnClickListener(v -> getActivity().onBackPressed());
        return view;
    }

}
