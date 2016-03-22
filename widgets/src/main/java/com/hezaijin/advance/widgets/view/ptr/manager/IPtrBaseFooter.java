package com.hezaijin.advance.widgets.view.ptr.manager;

import android.view.View;

/**
 * 自定义FooterView有2中方式。一种类似PtrFooterView,一种
 *
 * @author HaoZhang
 * @date 2016/2/18.
 */
public interface IPtrBaseFooter {

    public abstract View onGetContentView();

    public abstract void onLoadMorePrepare();

    public abstract void onLoadMoreBackground();

    public abstract void onLoadMoreCompleted();

}
