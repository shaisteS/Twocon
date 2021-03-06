package winep.ir.twocon.Presenter.SubGroupPage;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.lzyzsd.randomcolor.RandomColor;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.twocon.DataModel.Group;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.DividerItemDecorationRecyclerView;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 10/4/2016.
 */
public class SubGroupActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
    private NavigationView navigationView;
    private RecyclerView recyclerVieSubGroups;
    private FloatingActionsMenu floatingActionsMenu;
    private FrameLayout frameLayout;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_group_activity);
        context=this;
        setTitle(getIntent().getExtras().get("groupName").toString());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerVieSubGroups =(RecyclerView)findViewById(R.id.recycler_view_sub_groups);
        recyclerVieSubGroups.setLayoutManager(new LinearLayoutManager(context));
        recyclerVieSubGroups.addItemDecoration(new DividerItemDecorationRecyclerView(5));
        SubGroupRecyclerViewAdapter adapter=new SubGroupRecyclerViewAdapter(context,createSubGroup());
        recyclerVieSubGroups.setAdapter(adapter);

        floatingActionsMenu=(FloatingActionsMenu)findViewById(R.id.sub_group_fab_add);
        frameLayout=(FrameLayout)findViewById(R.id.containerFloatingActionMenu);
        floatingActionsMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                frameLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.background_after_click_FAM));
            }

            @Override
            public void onMenuCollapsed() {
                frameLayout.setBackgroundColor(Color.TRANSPARENT);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.title_back_menu, menu);
        return true;
    }
        @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        Utilities.getInstance().setSettingLanguage(newBase);

    }


    private ArrayList<Group> createSubGroup(){
        ArrayList<Group> groups=new ArrayList<>();
        //group:1
        RandomColor rnd1 = new RandomColor();
        int color1 = rnd1.randomColor();
        Group group1=new Group(0);
        group1.setTitle("فیزیولوژی");
        group1.setDescription("توضیحاتی در مورد این بخش");
        group1.setColor(color1);
        groups.add(group1);
        //group:2
        RandomColor rnd2 = new RandomColor();
        int color2 = rnd2.randomColor();
        Group group2=new Group(1);
        group2.setTitle("دهان و دندان");
        group2.setDescription("توضیحاتی در مورد این بخش");
        group2.setColor(color2);
        groups.add(group2);
        return groups;
    }
}
