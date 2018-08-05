package ru.nikartm.support.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.nikartm.support.R;

public class NavigationFragment extends Fragment {

    public NavigationFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.tv_title_layout)
    protected void onTitleLayout() {
        startFragment(new CollapsingTitleFragment(), true);
    }

    @OnClick(R.id.tv_avatar_layout)
    protected void onAvatarLayout() {

    }

    public void startFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, fragment);
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

}
