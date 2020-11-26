package com.example.everybooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

// 읽을 책 목록
public class ToReadFragment extends Fragment
{
    private View view;
    Intent intent;
    
    // 뷰 요소 선언
    FloatingActionButton floatingActionButton_add_book;
    LinearLayout linearLayout_to_read_book;
    ImageView imageView_img;
    TextView textView_title;
    TextView textView_date;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // 화면 생성
        view = inflater.inflate(R.layout.fragment_to_read, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.to_read_book_list);

        // 임시로 리스트에 데이터 넣기
        ArrayList<Book> bookList = new ArrayList<>();

        bookList.add(new Book(1,"첫번째책","2020.11.26"));
        bookList.add(new Book(2,"두번째책","2020.11.27"));
        bookList.add(new Book(3,"세번째책","2020.11.28"));
        bookList.add(new Book(4,"네번째책","2020.11.29"));
        recyclerView.setHasFixedSize(true);
        adapter = new ToReadBookAdapter(bookList);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        recyclerView.setAdapter(adapter);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 뷰 요소 초기화
        floatingActionButton_add_book = view.findViewById(R.id.add_book);
        linearLayout_to_read_book = view.findViewById(R.id.to_read_book);
        imageView_img = view.findViewById(R.id.img);
        textView_title = view.findViewById(R.id.title);
        textView_date = view.findViewById(R.id.content);


        // 각 요소를 클릭하면 수행할 동작 지정해두기
        View.OnClickListener click = new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.add_book:
                        // 책 추가 클릭하면 책 정보 추가화면으로 전환
                        intent = new Intent(getActivity(), CreateBookInfoActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };

        // 각 요소가 클릭되면
        floatingActionButton_add_book.setOnClickListener(click);

    }


}
