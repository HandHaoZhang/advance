package com.hezaijin.advance.widgets.view.ptr.manager;

import android.view.View;

import com.hezaijin.advance.widgets.view.ptr.load.PtrListeners;

/**
 * @author HaoZhang
 * @date 2016/2/17.
 */
public abstract class PtrBaseManager {
    /**
     * 拓展scroll listener
     */
    protected PtrListeners.OnScrollListener onScrollListener;

    /**
     * 上拉加载的监听回调
     */
    protected PtrListeners.OnLoadMoreListener onLoadMoreListener;

    protected PtrListeners.OnItemClickListener onItemClickListener;

    protected PtrListeners.OnFootClickListener onFootClickListener;

    public abstract PtrBaseManager setHeaderView(View view);

    public abstract PtrBaseManager setFooterView(IPtrBaseFooter view);

    public abstract PtrBaseManager onLoadMorePrepare();

    public abstract PtrBaseManager onLoadMoreBackground();

    public abstract PtrBaseManager onLoadMoreCompleted();

    public abstract PtrBaseManager setOnScrollListener(PtrListeners.OnScrollListener listener);

    public abstract PtrBaseManager setOnLoadMoreListener(PtrListeners.OnLoadMoreListener listener);

    public abstract PtrBaseManager setOnPtrItemClickListener(PtrListeners.OnItemClickListener listener);

    public abstract PtrBaseManager setOnFootClickListener(PtrListeners.OnFootClickListener listener);

}
