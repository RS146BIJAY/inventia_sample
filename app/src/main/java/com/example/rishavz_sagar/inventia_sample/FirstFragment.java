package com.example.rishavz_sagar.inventia_sample;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    Context context;
    int height,minHeight;
    public static int co=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context=getActivity();

        //Getting Maximum Window Height
        WindowManager manager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dimension=new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dimension);
        height=dimension.heightPixels;


        View v=inflater.inflate(R.layout.fragment_first, container, false);
        RecyclerView recv= (RecyclerView) v.findViewById(R.id.recv);
        recv.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Item_con> list=new ArrayList<>();
        list.add(new Item_con("Song 1",R.drawable.defimage));
        list.add(new Item_con("Song 2",R.drawable.img1));

        recv.setAdapter(new RecyclerAdapter(list));
        return v;
    }

    class RecyclerAdapter extends RecyclerView.Adapter<VH>
    {

        private ArrayList<Item_con> list;

        public RecyclerAdapter(ArrayList<Item_con> list)
        {
            this.list=list;
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_element,parent,false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(VH holder, final int position) {
            holder.pic.setImageResource(list.get(position).icon_id);
            Animation anim= AnimationUtils.loadAnimation(getActivity(),R.anim.grow_in);
            holder.arrow.startAnimation(anim);
            holder.tv.setText(list.get(position).text);
            holder.cv.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Fragment fragment=new Second_fragment();
                            Bundle arg=new Bundle();
                            arg.putInt("position",position);
                            fragment.setArguments(arg);
                            co=0;
                            getFragmentManager().beginTransaction().setCustomAnimations(R.animator.open_translate, R.animator.close_scale).replace(R.id.container,fragment).commit();
                        }
                    }
            );
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class VH extends RecyclerView.ViewHolder{

        CardView cv;
        ImageView pic,arrow;
        TextView tv;

        public VH(View itemView) {
            super(itemView);
            cv= (CardView) itemView.findViewById(R.id.cv1);
            pic= (ImageView) itemView.findViewById(R.id.photot);
            tv= (TextView) itemView.findViewById(R.id.title);
            arrow= (ImageView) itemView.findViewById(R.id.arr);
        }

    }

}
