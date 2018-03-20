package ru.nikartm.support;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.nikartm.support.widget.CollapsingTitleLayout;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.titleLayout)
    protected CollapsingTitleLayout titleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        initTitles();
    }

    private void initTitles() {
        titleLayout.setAllTitles(getString(R.string.title), getString(R.string.subtitle))
                .setTitleSize(40)
                .setTitleColor(getResources().getColor(R.color.colorAccent))
                .setSubTitleSize(16)
                .setSubTitleColor(getResources().getColor(R.color.colorAccent));
    }
}
