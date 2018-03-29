package com.bugull.testlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class MyAdapter extends BaseAdapter implements View.OnClickListener {
    private List<Hospital> mDatas;
    private Context mContext;
    private int  mScreenWidth;
    private View mView;
    private ListView mListView;
    private OnContentClickListener mContentClickListener;
    private boolean mIsJustShow;

    public MyAdapter(List<Hospital> mDatas, Context mContext, int mScreenWidth, ListView mListView, boolean mIsJustShow) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.mScreenWidth = mScreenWidth;
        this.mListView = mListView;
        this.mIsJustShow = mIsJustShow;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private boolean isClose = true;


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item,null);
            holder = new ViewHolder();
            // 初始化holder
            holder = new ViewHolder();
            holder.hSView = (HorizontalScrollView) convertView.findViewById(R.id.hsv);
            holder.mButtonLayout = convertView.findViewById(R.id.ll_action);
            holder.mContentLayout = convertView.findViewById(R.id.ll_content);
            holder.mHospitalTv = (TextView) convertView.findViewById(R.id.textHospital);
            holder.mTypeTv = (TextView) convertView.findViewById(R.id.textDepartment);
            ViewGroup.LayoutParams lp = holder.mContentLayout.getLayoutParams();
            lp.width = mScreenWidth;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mButtonLayout.setTag(position);
        holder.mContentLayout.setTag(position);
        // 设置监听事件
        if (!mIsJustShow){
            convertView.setOnTouchListener(new View.OnTouchListener() {
                private int  y;
                private int  x;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            if(mView!=null){
                                ViewHolder viewHolder = (ViewHolder) mView.getTag();
                                viewHolder.hSView.smoothScrollTo(0,0);
                            }
                            x = (int) event.getX();
                            y = (int) event.getY();
                            break;
                        case MotionEvent.ACTION_HOVER_MOVE:
                            int x3 = (int) event.getX();
                            int y3 = (int) event.getY();
                            int dY = Math.abs(y - y3);
                            int dx = Math.abs(x - x3);
                            if (dx>dY&&dx>20) {
//                    		((ViewGroup) v).requestDisallowInterceptTouchEvent(true);
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            ViewHolder viewHolder = (ViewHolder) v.getTag();
                             mView = v;
                            // 获得HorizontalScrollView滑动的水平方向值.
                            int scrollX = viewHolder.hSView.getScrollX();
                            // 获得操作区域的长度
                            int actionW = viewHolder.mButtonLayout.getWidth();
                            // 注意使用smoothScrollTo,这样效果看起来比较圆滑,不生硬
                            // 如果水平方向的移动值<操作区域的长度的一半,就复原
                            if(isClose){
                                if (scrollX < (actionW / 5))
                                {	isClose = true;
                                    viewHolder.hSView.smoothScrollTo(0, 0);
                                }else{
                                    isClose = false;
                                    viewHolder.hSView.smoothScrollTo(actionW, 0);
                                    notifyDataSetChanged();
                                }
                            }else{
                                if (scrollX < (actionW*4.0/5.0))
                                {	isClose = true;
                                    viewHolder.hSView.smoothScrollTo(0, 0);
                                }
                                else// 否则的话显示操作区域
                                {isClose = false;
                                    viewHolder.hSView.smoothScrollTo(actionW, 0);
                                    notifyDataSetChanged();

                                }
                            }
                            return true;
                    }
                    return false;
                }
            });
            if (holder.hSView.getScrollX() != 0) {
                holder.hSView.scrollTo(0, 0);
            }
        }else{
            holder.mButtonLayout.setVisibility(View.GONE);
        }
        holder.mHospitalTv.setText(mDatas.get(position).getHospotial());
        holder.mTypeTv.setText(mDatas.get(position).getDepartment());
        holder.mContentLayout.setOnClickListener(this);
        holder.mButtonLayout.setOnClickListener(this);
        return convertView;
    }
    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        Hospital hospital = mDatas.get(position);
        switch (v.getId()){
            case R.id.ll_content:
                if(isClose){
                    mContentClickListener.setContentListener(hospital,1);
                }
                break;
            case R.id.ll_action:
                mContentClickListener.setContentListener(hospital,2);
                break;
        }
    }
    static class ViewHolder {
        public HorizontalScrollView hSView;
        public View mContentLayout;//用于显示内容的Layout
        public TextView mHospitalTv;
        private TextView mTypeTv;
        public View mButtonLayout; //隐藏的布局，即那些按钮
    }
    public void setContentListener(OnContentClickListener onContentClickListener){
        mContentClickListener = onContentClickListener;
    }
    public List<Hospital> getList(){
        return mDatas;
    }
    public void remove(Hospital hospital){
        mDatas.remove(hospital);
    }
    public interface  OnContentClickListener{
        void setContentListener(Hospital hospital,int type);
    }
}
