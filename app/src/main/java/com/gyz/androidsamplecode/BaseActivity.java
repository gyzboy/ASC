package com.gyz.androidsamplecode;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends Activity {

    private List<ResolveInfo> entrys = new ArrayList<>();
    private PackageManager pm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(getCategory());

        pm = getPackageManager();
        // 每个ResolveInfo对象包含了一个组件的信息，这些组件可以是Activity、Service或BroadcastReceiver
        entrys = pm.queryIntentActivities(mainIntent, 0);
        RecyclerView list = new RecyclerView(this);
        LinearLayoutManager llm = new LinearLayoutManager(BaseActivity.this);
        list.setLayoutManager(llm);
        list.addItemDecoration(mItemDecoration);
        list.setAdapter(new BaseActivity.MyAdapter());
        setContentView(list);
    }

    public abstract String getCategory();

    RecyclerView.ItemDecoration mItemDecoration = new RecyclerView.ItemDecoration() {

        //在itemview绘制之后绘制,所以itemDecoration可见
        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
            Paint paint = new Paint();
            paint.setColor(Color.RED);

            for (int i = 0; i < parent.getLayoutManager().getChildCount(); i++) {
                final View child = parent.getChildAt(i);

                float left = child.getLeft() + (child.getRight() - child.getLeft()) / 4;
                float top = child.getBottom();
                float right = left + (child.getRight() - child.getLeft()) / 2;
                float bottom = top + 5;

                c.drawRect(left, top, right, bottom, paint);
            }
        }

        //在itemview绘制之前绘制,所以有可能在itemview的下面,导致itemDecoration不可见
        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);


            outRect.top = 5;
            outRect.bottom = 5;

            //itemView.height = itemView.getInsHeight + top + bottom;
        }
    };

    class MyAdapter extends RecyclerView.Adapter<BaseActivity.MyAdapter.Holder>{
        @NonNull
        @Override
        public BaseActivity.MyAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView tv = new TextView(BaseActivity.this);
            tv.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
            tv.setPadding(20,0,0,0);
            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setTextSize(16);
            return new BaseActivity.MyAdapter.Holder(tv);
        }

        @Override
        public void onBindViewHolder(@NonNull BaseActivity.MyAdapter.Holder holder, int position) {
            TextView tv = (TextView) holder.itemView;
            final ResolveInfo resolveInfo = entrys.get(position);
            tv.setText(resolveInfo.loadLabel(pm));
            tv.setOnClickListener(v -> {
                Intent intent = new Intent();
                intent.setClassName(BaseActivity.this,resolveInfo.activityInfo.name);
                startActivity(intent);
            });

        }

        @Override
        public int getItemCount() {
            return entrys.size();
        }

        public class Holder extends RecyclerView.ViewHolder {
            public Holder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}