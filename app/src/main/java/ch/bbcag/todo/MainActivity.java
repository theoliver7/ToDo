package ch.bbcag.todo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

import ch.bbcag.todo.Database.ToDoList;
import ch.bbcag.todo.Database.ToDoListDAO;
import ch.bbcag.todo.Fragments.AufgabeAnsicht_Fragment;
import ch.bbcag.todo.Fragments.Aufgaben_erstellen_Fragment;
import ch.bbcag.todo.Fragments.Main_Fragment;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    ArrayAdapter toDoListen;
    EditText neueListennname;
    ArrayList<String> listItems = new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int clickCounter = 0;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        ;

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.burger);
    }


    public void addItems(View v) {
        listItems.add("Clicked : " + clickCounter++);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment myFragment = null;

        switch (position) {
            case 0:
                myFragment = new Main_Fragment();
                break;
            case 1:
                myFragment = new AufgabeAnsicht_Fragment();
                break;

        }


        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, myFragment)
                .commit();
    }


    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.plus_liste, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.plus_liste) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setTitle(getString(R.string.neuerlistenname));


            // Set an EditText view to get user input
            final EditText input = new EditText(this);
            alert.setView(input);

            alert.setPositiveButton(getString(R.string.erstellen), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    ToDoList newList = new ToDoList();
                    ToDoListDAO database = new ToDoListDAO(getApplicationContext());
                    newList.setListenname(input.getText().toString());

                    try {
                        database.open();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    database.listeerstellen(newList);
                    database.close();

                    Fragment myFragment = new Main_Fragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, myFragment)
                            .commit();
                    Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.neuListeerstellt), Toast.LENGTH_SHORT);
                    toast.show();


                }
            });
            alert.setNeutralButton(getString(R.string.fav), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Canceled.
                }
            });

            alert.setNegativeButton(getString(R.string.abbrechen), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    // Canceled.
                }
            });

            alert.show();
            return true;
        } else if (id == R.id.plus_aufgabe) {
            Fragment myFragment = null;
            myFragment = new Aufgaben_erstellen_Fragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, myFragment)
                    .commit();
            return true;
        } else if (id == R.id.favoriten) {
            TextView favoriten = (TextView) this.findViewById(R.id.Listenname);
            String liste = favoriten.getText().toString();

            NavigationDrawerFragment drawer = new NavigationDrawerFragment();
            ToDoListDAO db= new ToDoListDAO(getApplicationContext());

            drawer.addListstoFavs(liste, this.getApplicationContext(), this, item);

            Toast.makeText(this.getBaseContext(),getString(R.string.favorithinzugefuegt),
                    Toast.LENGTH_SHORT).show();


        } else if (id == R.id.listeloeschen) {
            ToDoListDAO db = new ToDoListDAO(getApplicationContext());
            TextView listennametextview = (TextView) this.findViewById(R.id.Listenname);
            String listenname = listennametextview.getText().toString();

            db.listeloeschen(listenname);

            Toast.makeText(getApplicationContext(), getString(R.string.listegeloescht),
                    Toast.LENGTH_LONG).show();


            Fragment myFragment = new Main_Fragment();
            // update the main content by replacing fragments
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, myFragment)
                    .commit();

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.main_layout, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
        }
    }


}
