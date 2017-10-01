package com.example.chad.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 출력할 데잍를 문자열로 선언합니다.
        ArrayList<String> MyContents = new ArrayList<String>();
        MyContents.add("Custom ListView");
        MyContents.add("Webview Layout");
        MyContents.add("3번 내용");
        MyContents.add("4번 내용");

        // 아래는 어댑터 준비 과정입니다.
        // 어댑터란? 데이터는 어댑터를 거쳐서 리스트뷰에 표시됩니다.
        // 다이렉트로 리스트뷰에 표시되지 않습니다.
        // 사용자가 정의한 데이터를 Layout에 표시하기 위해 어댑터 클래스를 상속받아 필요한 함수를 채워넣습니다.

        // 어댑터를 쓰는 이유 :
        // 텍스트 뷰를 쓸 때는 어댑터 없이 그냥 텍스트를 집어넣는데 왜 리스트뷰에는 어댑터를 거쳐서 집어넣는가?
        // 데이터의 종류에 따라서 다양한 종류의 어댑터를 입맛에 맞게 선정할 수 있기 때문이다.
        // 예를 들어 배열이 있으면 순서대로 집어넣어준다. 라는 리스트뷰도 있을 것이다.
        // 데이터를 어떤 순서로 어떻게 집어넣을지에 대해서 다양한 케이스가 있으므로 이에 적용시키기 위하여 어댑터가 있다.
        // 데이터의 특성에 따라 다양한 어댑터를 사용하도록 합시다.

        // 기본적인 어댑터(가장 단순한 형태)는 아래와 같이 쓰면 된다.
        ArrayAdapter<String> MyArrayAdapter;
        MyArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MyContents);

        // 리스트뷰 변수와 실제 위젯 주소와의 연결
        ListView MyListView = (ListView)findViewById(R.id.MyListView);
        MyListView.setAdapter(MyArrayAdapter);

        // 리스트뷰 속성 (없어도 구현 가능! 빼도 된다!)
        // 항목을 선택하는 모드
        MyListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // 항목 사이의 구분선 지정
        MyListView.setDivider(new ColorDrawable(Color.BLUE));
        // 구분선의 높이 지정
        MyListView.setDividerHeight(2);

        MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {
                ListView listView = (ListView) parent;

                String item = (String) listView.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();

                switch(position) {
                    case 0 : {
                        Intent intent = new Intent(MainActivity.this, ListActivity.class);
                        startActivity(intent);
                    }
                        break;
                    case 1 : {
                        Intent intent = new Intent(MainActivity.this, WebviewLayoutActivity.class);
                        startActivity(intent);
                    }
                        break;

                }
            }
        }); // 리스트뷰 항목을 선택했을때, Toast 창 띄우는 이벤트 생성

    }

}
