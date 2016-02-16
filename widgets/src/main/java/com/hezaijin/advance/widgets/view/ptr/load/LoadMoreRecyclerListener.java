package com.hezaijin.advance.widgets.view.ptr.load;

/**
 * Created by Syehunter on 2015/11/21.
 */
public class LoadMoreRecyclerListener /*extends RecyclerView.OnScrollListener*/ {
   /* private static final String TAG = "LoadMoreRecyclerListener" ;
    private Context mContext;
    private RecyclerMode mode;

    private RefreshRecyclerViewAdapter mAdapter;

    public int firstVisibleItemPosition;
    private int lastVisibleItemPosition;

    private int[] mPositions;
    private int mScrollState;
    private OnLoadMoreListener mOnLoadMoreListener;
    private OnBothRefreshListener mOnBothRefreshListener;
    private RefreshLoadingLayout mFooterLoadingLayout;

    *//**
     * 是否是正则加载状态
     *//*
    private boolean isLoading = false;
    *//**
     * 通过滚动方向判断是否允许上拉加载
     *//*
    public boolean isLoadingMoreEnabled = true;
    *//**
     * 加载更多之前RecyclerView的item数量
     *//*
    private int mOldItemCount;

    private boolean hasCompleted = false;

    public LoadMoreRecyclerListener(Context context, RecyclerMode mode) {
        this.mContext = context;
        this.mode = mode;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        Log.d(TAG, "onScrolled() called with: " + "recyclerView = [" + recyclerView + "], dx = [" + dx + "], dy = [" + dy + "]");
        hasCompleted = false;

        RecyclerView.LayoutManager mLayoutManager = recyclerView.getLayoutManager();

        //初始化firstVisibleItemPosition和lastVisibleItemPosition
        if (null != mLayoutManager) {
            if (mLayoutManager instanceof LinearLayoutManager) {
                firstVisibleItemPosition = ((LinearLayoutManager) mLayoutManager)
                        .findFirstVisibleItemPosition();
                lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager)
                        .findLastVisibleItemPosition();
            } else if (mLayoutManager instanceof GridLayoutManager) {
                firstVisibleItemPosition = ((GridLayoutManager) mLayoutManager)
                        .findFirstVisibleItemPosition();
                lastVisibleItemPosition = ((GridLayoutManager) mLayoutManager)
                        .findLastVisibleItemPosition();
            } else if (mLayoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager mStaggeredGridLayoutManager =
                        (StaggeredGridLayoutManager) mLayoutManager;
                if (null == mPositions) {
                    mPositions = new int[mStaggeredGridLayoutManager.getSpanCount()];
                }
                mStaggeredGridLayoutManager.findFirstVisibleItemPositions(mPositions);
                mStaggeredGridLayoutManager.findLastVisibleItemPositions(mPositions);
                firstVisibleItemPosition = getFirst(mPositions);
                lastVisibleItemPosition = getLast(mPositions);
            } else {
                throw new IllegalArgumentException(
                        "The layoutManager must be one of LinearLayoutManager, " +
                                "GridLayoutManager or StaggeredGridLayoutManager");
            }
            if ((lastVisibleItemPosition == recyclerView.getAdapter().getItemCount()-1)){
                //拟定在这里发出加载更多的请求(通知adapter更新状态即是 showfootanim ) 这里会加载很多次，所以要在刷新的时候判断，如果是正在加载中，就不再处理这次请求
                Log.d(TAG,"show foot ");
            }
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        Log.d(TAG, "onScrollStateChanged() called with: " + "recyclerView = [" + recyclerView + "], newState = [" + newState + "]");
        if (RecyclerMode.BOTH != mode && RecyclerMode.BOTTOM != mode) {
            return;
        }

        if (null == recyclerView.getAdapter()
                || !(recyclerView.getAdapter() instanceof RefreshRecyclerViewAdapter)) {
            return;
        }

        mAdapter = (RefreshRecyclerViewAdapter) recyclerView.getAdapter();

        mScrollState = newState;
        RecyclerView.LayoutManager mLayoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = mLayoutManager.getChildCount();
        int totalItemCount = mLayoutManager.getItemCount();

        if (visibleItemCount > 0
                *//*&& mScrollState == RecyclerView.SCROLL_STATE_IDLE*//*
                && lastVisibleItemPosition >= totalItemCount - 1
               && isLoadingMoreEnabled){

            if (isLoading) {
                return;
            }

            if (hasCompleted) {
                hasCompleted = !hasCompleted;
                return;
            }

            if (RecyclerMode.BOTH == mode) {
                if (null != mOnBothRefreshListener) {
                    addFooterLoadinLayout(recyclerView);
                    mOnBothRefreshListener.onLoadMore();
                    return;
                }
            } else if (RecyclerMode.BOTTOM == mode) {
                if (null != mOnLoadMoreListener) {
                    addFooterLoadinLayout(recyclerView);
                    mOnLoadMoreListener.onLoadMore();
                    return;
                }
            }

        }
    }

    *//**
     * 添加LoadMore布局
     *//*
    private void addFooterLoadinLayout(RecyclerView recyclerView) {
        isLoading = true;
        if (null == mFooterLoadingLayout) {
            mFooterLoadingLayout = new RotateLoadingLayout(mContext, RecyclerMode.BOTTOM);
        }
        mAdapter.addFooterView(mFooterLoadingLayout);
        mOldItemCount = mAdapter.getItemCount();
        recyclerView.smoothScrollToPosition(mOldItemCount - 1);
        mFooterLoadingLayout.onRefresh();
        mFooterLoadingLayout.setVisibility(View.VISIBLE);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    public void setOnBothRefreshListener(OnBothRefreshListener onBothRefreshListener) {
        this.mOnBothRefreshListener = onBothRefreshListener;
    }

    *//**
     * StaggeredGridLayoutManager firstVisibleItemPosition
     *
     * @param mPositions
     * @return
     *//*
    private int getFirst(int[] mPositions) {
        int first = mPositions[0];
        for (int value : mPositions) {
            if (value < first) {
                first = value;
            }
        }
        return first;
    }

    *//**
     * StaggeredGridLayoutManager lastVisibleItemPosition
     *
     * @param mPositions
     * @return
     *//*
    private int getLast(int[] mPositions) {
        int last = mPositions[0];
        for (int value : mPositions) {
            if (value > last) {
                last = value;
            }
        }
        return last;
    }

    public void setMode(RecyclerMode mode) {
        this.mode = mode;
    }

    public void onRefreshComplete() {
        if (null != mAdapter && mAdapter.getFootersCount() > 0) {
            isLoading = false;
            hasCompleted = true;
//            mAdapter.removeFooter(mFooterLoadingLayout);
        }
    }*/

}
