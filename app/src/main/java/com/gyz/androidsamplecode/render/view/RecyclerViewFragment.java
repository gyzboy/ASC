package com.gyz.androidsamplecode.render.view;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gyz.androidsamplecode.BaseFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private final ArrayList<String> data = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = new RecyclerView(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(10, 10, 10, 10);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    System.out.println("gy123 cacheView | " + getCachedViewHolders(recyclerView));
                    System.out.println("gy123 attachedView | " + getAttachedViewHolders(recyclerView));
                }
            }
        });
        for (int i = 0; i < 10; i++) {
            data.add("item -" + i);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((ViewGroup) view).addView(recyclerView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void handleClick(View v) {

    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(getContext());
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
            textView.setBackgroundColor(Color.RED);
            MyViewHolder holder = new MyViewHolder(textView);
            System.out.println("gy123 createHolder | " + holder);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            System.out.println("gy123 bindViewHolder | " + holder + " | " + position);
            ((TextView) holder.itemView).setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static List<RecyclerView.ViewHolder> getAttachedViewHolders(RecyclerView recyclerView) {
        try {
            Object recycler = getRecycler(recyclerView);
            ArrayList<RecyclerView.ViewHolder> cachedViews = getAttachedViews(recycler);
            return (cachedViews != null) ? new ArrayList<>(cachedViews) : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static List<RecyclerView.ViewHolder> getCachedViewHolders(RecyclerView recyclerView) {
        try {
            Object recycler = getRecycler(recyclerView);
            ArrayList<RecyclerView.ViewHolder> cachedViews = getCachedViews(recycler);
            return (cachedViews != null) ? new ArrayList<>(cachedViews) : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // 通过反射获取 Recycler 实例
    private static Object getRecycler(RecyclerView recyclerView) throws Exception {
        Field recyclerField = RecyclerView.class.getDeclaredField("mRecycler");
        recyclerField.setAccessible(true);
        return recyclerField.get(recyclerView);
    }

    // 获取 mCachedViews 字段
    private static ArrayList<RecyclerView.ViewHolder> getCachedViews(Object recycler) throws Exception {
        Field cachedViewsField = recycler.getClass().getDeclaredField("mCachedViews");
        cachedViewsField.setAccessible(true);
        return (ArrayList<RecyclerView.ViewHolder>) cachedViewsField.get(recycler);
    }

    // 获取 mCachedViews 字段
    private static ArrayList<RecyclerView.ViewHolder> getAttachedViews(Object recycler) throws Exception {
        Field cachedViewsField = recycler.getClass().getDeclaredField("mAttachedScrap");
        cachedViewsField.setAccessible(true);
        return (ArrayList<RecyclerView.ViewHolder>) cachedViewsField.get(recycler);
    }
}
