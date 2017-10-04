package com.example.dragonsage.articleviewer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by DragonSage on 10/4/2017.
 */

public class ArticleFragment extends Fragment{

    private int currentPosition = -1;
    final static String ARG_POSITION = "position";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState != null){
            //Rotation and preserve previous result
            currentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        //inflate the view for this fragment
        View myFragmentView = inflater.inflate(R.layout.article_fragment,container,false);
        return myFragmentView;
    }

    public void updateArticleView(int position){
        View v = getView();
        TextView article = (TextView) v.findViewById(R.id.article);
        String[] data = Ipsum.Articles;
        article.setText(data[position]);
        currentPosition = position;
    }

    @Override
    public void onStart() {
        super.onStart();

        //During start up, we should check if there are arguments
        //passed to this fragment, we know the layout has already been
        // applied to the fragment so we can safely call the methode
        //that sets the article test

        Bundle args = getArguments();
        if (args != null){
            //set the article based on the arguments passed in
            updateArticleView(args.getInt(ARG_POSITION));
        }else if (currentPosition != -1){

            //set the article based on the saved instance state defined during onCreate view
            updateArticleView(currentPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //save the current selection for the later use of the fragment
        outState.putInt(ARG_POSITION,currentPosition);
    }
}

