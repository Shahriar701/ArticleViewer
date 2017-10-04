package com.example.dragonsage.articleviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements HeadlineFragment.onHeadlineSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if the activity is using the layout version
        //with the frame layout if so we have to add the fragment
        //(it wont be done automatically)
        if (findViewById(R.id.container) != null){
            //However if were being restored from a previous state
            //then dont do anything
            if (savedInstanceState != null){
                return;
            }

            //create an instance for the headline fragment
            HeadlineFragment headlineFragment = new HeadlineFragment();

            //In case this activity was started with special instruction from an intent
            //pass the intents extra to the fragment as arguments
            headlineFragment.setArguments(getIntent().getExtras());

            //Ask the fragment manager to add it to the frameLayout
            getFragmentManager().beginTransaction().
                    add(R.id.container,headlineFragment).commit();

        }
    }


    @Override
    public void onArticleSelected(int position) {

        //Capture the article fragment from the activity's duel-panel

        ArticleFragment articleFragment = (ArticleFragment) getFragmentManager().findFragmentById(R.id.article_fragment);

        //if we dont find one we must not be in two pane mode
        //lets swap the fragments instead

        if (articleFragment != null){

            //we must be in two pane layout
            articleFragment.updateArticleView(position);

        }else {
            //we must be in one pane layout

            //create fragment and give an argument for the selected article right away
            ArticleFragment swapFragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION,position);
            swapFragment.setArguments(args);

            //now that the fragment are prepared, swap it

            getFragmentManager().beginTransaction()
                    .replace(R.id.container, swapFragment)
                    .addToBackStack(null).commit();
        }
    }
}
