package com.hezaijin.advance.widgets.view.ptr.load;

/**
 * 可以判断滑动到最底部的时候，设置footview为加载中
 *
 * @author HaoZhang
 * @date 2016/2/1.
 */
@Deprecated
public class PtrAdapter /*extends RecyclerView.Adapter<RecyclerView.ViewHolder> */{

   /* public static final int MODE_BOTH = 0;
    public static final int MODE_ONLY_PULLDOWN = -1;
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    private int mMode = MODE_BOTH;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private RecyclerView.LayoutManager mLayoutManager;

    public PtrAdapter(int mode, RecyclerView.Adapter adapter) {

        setAdapter(adapter);
    }

    public void setAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        if (null != adapter) {
            if (!(adapter instanceof RecyclerView.Adapter)) {
                throw new RuntimeException("A RecyclerView.Adapter is Need");
            }

            if (null != mAdapter) {
                notifyItemRangeRemoved(getHeadersCount(), mAdapter.getItemCount());
                mAdapter.unregisterAdapterDataObserver(mDataObserver);
            }
            mAdapter = adapter;
            mAdapter.registerAdapterDataObserver(mDataObserver);
            notifyItemRangeInserted(getHeadersCount(), mAdapter.getItemCount());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (null != mAdapter) {
            if (mHeaderViewTypes.contains(viewType)) {
                //currentPosition in mHeaderViews is (viewType - TYPE_HEADER_VIEW)
                return new RecyclerHeaderViewHolder(mHeaderViews.get(viewType - TYPE_HEADER_VIEW));
            } else if (mFooterViewTypes.contains(viewType)) {
                //currentPosition in mFooterViews is (viewType - headersCount - TYPE_FOOTER_VIEW)
                return new RecyclerHeaderViewHolder(mFooterViews.get(viewType - getHeadersCount()
                        - TYPE_FOOTER_VIEW));
            } else {
                return mAdapter.onCreateViewHolder(parent, viewType);
            }
        }
        return null;
    }

    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (null != mAdapter) {
            if (position >= getHeadersCount() && position < getHeadersCount() + mAdapter.getItemCount()) {
                mAdapter.onBindViewHolder(holder, position - getHeadersCount());
                if (null != mOnItemClickListener) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnItemClickListener.onItemClick(holder, position - getHeadersCount());
                        }
                    });
                }
                if (null != mOnItemLongClickListener) {
                    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return mOnItemLongClickListener.onItemLongCLick(holder, position - getHeadersCount());
                        }
                    });
                }
            } else {
                if (null != mLayoutManager && mLayoutManager instanceof StaggeredGridLayoutManager) {
                    StaggeredGridLayoutManager.LayoutParams params = new StaggeredGridLayoutManager.LayoutParams(
                            StaggeredGridLayoutManager.LayoutParams.MATCH_PARENT,
                            StaggeredGridLayoutManager.LayoutParams.MATCH_PARENT);
                    params.setFullSpan(true);
                    holder.itemView.setLayoutParams(params);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    private RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {

        @Override
        public void onChanged() {
            super.onChanged();
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            notifyItemRangeChanged(positionStart + getHeadersCount(), itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            notifyItemRangeInserted(positionStart + getHeadersCount(), itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            notifyItemRangeRemoved(positionStart + getHeadersCount(), itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            notifyItemRangeChanged(fromPosition + getHeadersCount(), toPosition + getHeadersCount() + itemCount);
        }
    };

    public interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemLongClickListener {
        boolean onItemLongCLick(RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }*/
}
