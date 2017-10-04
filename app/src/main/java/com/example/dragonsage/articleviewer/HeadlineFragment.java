package com.example.dragonsage.articleviewer;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by DragonSage on 10/4/2017.
 */

public class HeadlineFragment extends ListFragment {

    onHeadlineSelectedListener callback;

    public interface onHeadlineSelectedListener{

        void onArticleSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        //make sure the container Activity has implemented the interface
        // if not throw an exception so we can fix it

        try {
            callback = (onHeadlineSelectedListener) activity;
        }catch (ClassCastException e){

            throw new ClassCastException(activity.toString() +
                    "Must Implement onHeadlineSelectedListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int layout = android.R.layout.simple_list_item_activated_1;
        String[] data = Ipsum.Headlines;

        setListAdapter(new ArrayAdapter<String>(getActivity(), layout,data));
    }

    @Override
    public void onStart() {
        super.onStart();

        //When in two pane layout, set the lightView to highlight the list item
        //instead of just simply blinking

        Fragment f =getFragmentManager().findFragmentById(R.id.article_fragment);
        ListView v = getListView();
        if (f != null && v != null){

            v.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        //Notify the parent of the selected item
        callback.onArticleSelected(position);

        //Again set the item to highlighted in a two pane layout
        l.setItemChecked(position,true);
    }
}
