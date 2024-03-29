package com.example.dome2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by IT-CTY on 2018/4/25.
 */

public class Fragment1 extends Fragment {
    private TextView textView;
    private Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //textView=(TextView)getActivity().findViewById(R.id.textView1);
        button=(Button)getActivity().findViewById(R.id.toy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"Fragment1",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),ZhoubianActivity.class);
                startActivity( intent);
            }
        });
        button=(Button)getActivity().findViewById(R.id.cdk);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"Fragment1",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),Cdk.class);
                startActivity( intent);
            }
        });
        button=(Button)getActivity().findViewById(R.id.skin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"Fragment1",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),PifuActivity.class);
                startActivity( intent);
            }
        });
        button=(Button)getActivity().findViewById(R.id.player);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"Fragment1",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),PlayerActivity.class);
                startActivity( intent);
            }
        });

    }
}
