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
        titleLayout.setTitle(getString(R.string.title))
                .setSubTitle(getString(R.string.subtitle))
                .setTitleColor(getResources().getColor(R.color.colorAccent))
                .setSubTitleColor(getResources().getColor(R.color.colorAccent))
                .setTitleSize(40)
                .setSubTitleSize(16);
    }
}
