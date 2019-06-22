package com.example.studyroomsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

// 강의실 개수(INT 값)의 배열을 하나 두고
// 인텐트로 넘어오는 Building position값으로 해당 강의실 개수 만큼 카드뷰 출력
public class StudyRoomActivity extends AppCompatActivity {

    private RecyclerView.Adapter mRecycleAdapter;
    private RecyclerView.LayoutManager mRecycleLayoutManager;
    private RecyclerView mRecyclerView;
    private ArrayList<StudyRoomCardData> mDataset;

    public static int BuildingPosition;
    public static int StartPosition = 0;

    static int[] StudyRoomCount = new int[]{
            // building1
            12,
            // building2
            5,
            // building3
            5,
            // building4
            5,
            // building5
            5
    };
    static String[] BuildingName = new String[]{
            // building1
            "제도관#6202",
            "제도관#6203",
            "제도관#6208",
            "제도관#6210",
            "제도관#6302-1",
            "제도관#6303",
            "제도관#6408(전산실1)",
            "제도관#6409(전산실2)",
            "제도관#6409-1(전산실3)",
            "제도관#6514",
            "제도관#6515",
            "제도관#6516",
            // building2
            "기계관#1",
            "기계관#2",
            "기계관#3",
            "기계관#4",
            "기계관#5",
            // building3
            "항공관#1",
            "항공관#2",
            "항공관#3",
            "항공관#4",
            "항공관#5",
            // building4
            "재료관#1",
            "재료관#2",
            "재료관#3",
            "재료관#4",
            "재료관#5",
            // building5
            "건설관#1",
            "건설관#2",
            "건설관#3",
            "건설관#4",
            "건설관#5"
    };
    static int[] StudyRoomImgset = new int[]{
            // building1
            R.drawable.l1,
            R.drawable.l1,
            R.drawable.l3,
            R.drawable.l3,
            R.drawable.l2,
            R.drawable.l1,
            R.drawable.l1,
            R.drawable.l3,
            R.drawable.l3,
            R.drawable.l2,
            R.drawable.l1,
            R.drawable.l1,

            // building2
            R.drawable.l3,
            R.drawable.l2,
            R.drawable.l1,
            R.drawable.l1,
            R.drawable.l1,

            // building3
            R.drawable.l2,
            R.drawable.l2,
            R.drawable.l2,
            R.drawable.l1,
            R.drawable.l1,

            // building4
            R.drawable.l3,
            R.drawable.l3,
            R.drawable.l3,
            R.drawable.l3,
            R.drawable.l1,

            // building5
            R.drawable.l1,
            R.drawable.l1,
            R.drawable.l2,
            R.drawable.l3,
            R.drawable.l3,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studyroom);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecycleLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mRecycleLayoutManager);

        mDataset = new ArrayList<>();
        mRecycleAdapter = new StudyRoomRecyclerAdapter(mDataset);
        mRecyclerView.setAdapter(mRecycleAdapter);

        Intent in = getIntent();
        BuildingPosition = in.getIntExtra("BuildingPosition",0);

        // 이미지 시작위치 구하기
        for(int studyRoomCounter = 0; studyRoomCounter < BuildingPosition; studyRoomCounter++){
            StartPosition += StudyRoomCount[studyRoomCounter];
        }
        // 카드뷰 추가
        for(int studyRoomCounter = 0; studyRoomCounter < StudyRoomCount[BuildingPosition]; studyRoomCounter++){
            mDataset.add(new StudyRoomCardData(BuildingName[StartPosition + studyRoomCounter], StudyRoomImgset[StartPosition + studyRoomCounter]));
        }

        mRecyclerView.setAdapter(mRecycleAdapter);
        StartPosition = 0;
    }
}