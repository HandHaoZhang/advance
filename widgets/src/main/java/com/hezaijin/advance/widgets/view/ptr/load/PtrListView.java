package com.hezaijin.advance.widgets.view.ptr.load;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * @author HaoZhang
 * @date 2016/2/15.
 */
public class PtrListView extends ListView implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener {
    private static final String TAG = "PtrListView";

    private static final int STATUS_READY = 0;
    private static final int STATUS_RUNNING = 1;
    private static final int STATUS_COMPLETED = -1;
    private int mStatus = STATUS_READY;

    private OnScrollListener onScrollListener;
    private OnLoadMoreListener onLoadMoreListener;
    private OnItemClickListener onItemClickListener;
    private OnFootClickListener onFootClickListener;

    long lastScrollDate = 0;
    private Thread loadThread;
    private LoadMoreRunnable loadRunnalbe;

    private View footer;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public PtrListView(Context context) {
        this(context, null, 0);
    }

    public PtrListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PtrListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initFootView();
        this.setOnItemClickListener(this);
        this.setOnScrollListener(PtrListView.this);
    }

    private void initFootView() {
        footer = new PtrFootView(getContext());
        footer.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mStatus == STATUS_RUNNING && (position == getCount() - 1) && null != onFootClickListener) {
            onFootClickListener.onFootClickListener(view);
        } else if (null != onItemClickListener) {
            onItemClickListener.onItemClick(parent, view, position, id);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (null != onScrollListener) {
            onScrollListener.onScrollStateChanged(view, scrollState);
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (null != onScrollListener) {
            onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
            if (onScrollListener.onDispatchScorllListener()) {
                return;
            }
        }

        if (null == onLoadMoreListener) return;

        if (mStatus != STATUS_RUNNING
                && this.getChildAt(0) != null
                && this.getLastVisiblePosition() == this.getAdapter().getCount() - 1
                && this.getChildAt(this.getChildCount() - 1).getBottom() <= this.getHeight()) {
            long time = System.currentTimeMillis();
            if ((time - lastScrollDate) > 200) {
                lastScrollDate = time;
                readyLoadMore();
                startLoadMore();
            }
        }
    }

    public PtrListView setOnScrollListener(OnScrollListener listener) {
        this.onScrollListener = listener;
        return this;
    }

    public PtrListView setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.onLoadMoreListener = listener;
        return this;
    }

    public PtrListView setOnPtrItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
        return this;
    }

    public PtrListView setOnFootClickListener(OnFootClickListener listener) {
        this.onFootClickListener = listener;
        return this;
    }

    public interface OnScrollListener {
        /**
         * 返回true代表自己拓展ScrollListener,ptrlistview 不处理scrolllistener，也就不处理loadmore
         *
         * @return
         */
        public boolean onDispatchScorllListener();

        public void onScrollStateChanged(AbsListView view, int scrollState);

        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);

    }

    public interface OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }

    public interface OnLoadMoreListener {
        public void onLoadMoreStart();

        /**
         * 处理加载事件，在子线程
         */
        public void onLoadMoreRunning();

        /**
         * 加载完毕，在主线程。注意adapter刷新。
         */
        public void onLoadMoreCompleted();
    }

    public interface OnFootClickListener {
        public void onFootClickListener(View foot);
    }

    public void cancleLoading() {
    }


    private void completeLoadMore() {
        onLoadMoreListener.onLoadMoreCompleted();
        ((PtrFootView) footer).stopRefresh();
        this.removeFooterView(footer);
        mStatus = STATUS_READY;
    }

    private void readyLoadMore() {
        mStatus = STATUS_RUNNING;
        this.addFooterView(footer);
        ((PtrFootView) footer).startRefresh();
    }

    private void startLoadMore() {
        terminateThreads();
        loadRunnalbe = new LoadMoreRunnable();
        loadThread = new Thread(loadRunnalbe);
        loadThread.start();
    }

    private void terminateThreads() {

        if (loadThread != null) {
            loadRunnalbe.terminate();
            // No need to join, since we don't really terminate the thread. We just demand
            // it to post its result runnable into the gui main loop.
        }
    }

    private class LoadMoreRunnable implements Runnable {
        boolean runs = true;

        void terminate() {
            runs = false;
        }

        @Override
        public void run() {
            onLoadMoreListener.onLoadMoreRunning();

            if (runs) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        completeLoadMore();
                    }
                });
            }
        }
    }
}
