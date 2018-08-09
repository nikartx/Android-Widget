# CollapsingTitleLayout
In CollapsingToolbarLayout we can create only one animated title, but this solution eliminates this drawback.
Example custom layout for 2 animated titles into CollapsingToolbarLayout.

## Screenshots
![screenshot](https://raw.githubusercontent.com/nikartm/Android-Widget/master/screenshots/screenshot.gif)

## How to use?
Adjust the xml view [Example](https://github.com/nikartm/Android-Widget/blob/master/app/src/main/res/layout/fragment_collapsing_title.xml):
```
<android.support.design.widget.CoordinatorLayout>
    <android.support.design.widget.AppBarLayout>
        <android.support.design.widget.CollapsingToolbarLayout>
            <android.support.v7.widget.Toolbar />

            <ru.nikartm.support.widget.CollapsingTitleLayout
                android:id="@+id/titleLayout"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:paddingTop="75dp"
                app:ctl_textTitle="@string/title"
                app:ctl_titleTextSize="34sp"
                app:ctl_titleColor="@color/colorWhite"
                app:ctl_textSubtitle="@string/subtitle"
                app:ctl_subtitleTextSize="16sp"
                app:ctl_subtitleColor="@color/colorWhite"
                app:layout_collapseMode="parallax"
                app:layout_collapseParallaxMultiplier="0.8" />

        </android.support.design.widget.CollapsingToolbarLayout>
    </android.support.design.widget.AppBarLayout>
</android.support.design.widget.CoordinatorLayout>
```
Or programmatically:
```
titleLayout.setTitle(getString(R.string.title))
                .setSubTitle(getString(R.string.subtitle))
                .setTitleColor(getResources().getColor(R.color.colorAccent))
                .setSubTitleColor(getResources().getColor(R.color.colorAccent))
                .setTitleSize(40)
                .setSubTitleSize(16);
```

# CollapsingViewBehavior
CollapsingViewBehavior to expand or collapse view to target place. You can use this behavior with ImageView, TextView, Switch e.t.c. Or your anyone custom view.

## Screenshots
![screenshot](https://raw.githubusercontent.com/nikartm/Android-Widget/master/screenshots/screenshot2.gif)

## How to use?
Adjust the xml view [Example](https://github.com/nikartm/Android-Widget/blob/master/app/src/main/res/layout/fragment_collapsing_avatar.xml):
```
<android.support.design.widget.CoordinatorLayout>
    <android.support.design.widget.AppBarLayout>
        <android.support.design.widget.CollapsingToolbarLayout>
            <android.support.v7.widget.Toolbar>
            
                <Space
                    android:id="@+id/target_place"
                    android:layout_marginStart="16dp"
                    android:layout_width="40dp"
                    android:layout_height="40dp"/>
                    
            <android.support.v7.widget.Toolbar />
        </android.support.design.widget.CollapsingToolbarLayout>
    </android.support.design.widget.AppBarLayout>
    
    <ru.nikartm.support.widget.CircularImageView
        android:id="@+id/iv_avatar"
        android:layout_height="150dp"
        android:layout_width="150dp"
        android:layout_marginTop="30dp"
        app:target_place="@id/target_place"
        app:layout_behavior="ru.nikartm.support.widget.CollapsingViewBehavior"
        android:layout_gravity="center_horizontal"
        android:elevation="5dp"
        android:src="@drawable/avatar"/>
            
</android.support.design.widget.CoordinatorLayout>
```