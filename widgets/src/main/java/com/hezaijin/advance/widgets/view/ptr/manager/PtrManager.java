package com.hezaijin.advance.widgets.view.ptr.manager;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * @author HaoZhang
 * @date 2016/2/17.
 */
public class PtrManager {


    public PtrManager() {
    }

    public static PtrBaseManager bind(ViewGroup group){
        if (group instanceof ListView){

            return new PtrListViewManager((ListView)group);
        }else if (group instanceof RecyclerView){

            return new PtrRecyclerViewManager();
        }
        return null;
    }



}
