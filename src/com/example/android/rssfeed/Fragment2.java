package com.example.android.rssfeed;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 8/5/13
 * Time: 1:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class Fragment2 extends Fragment{
    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState ){
        return inflater.inflate( R.layout.frag2,
                container, false );
    }
}