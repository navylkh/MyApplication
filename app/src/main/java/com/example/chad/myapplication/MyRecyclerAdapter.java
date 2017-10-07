package com.example.chad.myapplication;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by chad on 2017-10-02.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
	Context mContext;
    private ArrayList<MyData> mDataset;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImageView;
        public TextView mTextView;
		public CardView cardview;

        public ViewHolder(View view) {
            super(view);
            mImageView = (ImageView)view.findViewById(R.id.image);
            mTextView = (TextView)view.findViewById(R.id.textview);
			cardview=(CardView)itemView.findViewById(R.id.cardview);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyRecyclerAdapter(Context context, ArrayList<MyData> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    // Create new views ( invoced by the layout manager )
    // 필수로 Generate 되어야 하는 메소드 1 : 새로운 뷰 생성
    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //...
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // 필수로 Generate 되어야 하는 메소드 2 : ListView의 getView 부분을 담당하는 메소드
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

		final MyData item = mDataset.get(position);
        holder.mTextView.setText(item.text);
        holder.mImageView.setImageResource(item.img);
		holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, item.text, Toast.LENGTH_SHORT).show();
            }
        });

        setAnimation(holder.mImageView, position);
    }

    private void setAnimation(View viewToAnimation, int position) {
        // 새로 보여지는 뷰라면 애니메이션을 해줍니다.
        if(position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimation.startAnimation(animation);
            lastPosition = position;
        }
    }
    // Return the size of your dataset (invoked by the layout manager)
    // 필수로 Generate 되어야 하는 메소드 3
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

class MyData {
    public String text;
    public int img;
    public MyData(String text, int img) {
        this.text = text;
        this.img = img;
    }
}