package com.itgroup.jdbc;

import com.itgroup.bean.KimKwangSuk;
import com.itgroup.bean.KimKwangSuk;
import com.itgroup.dao.KimKwangSukDao;
import com.itgroup.dao.KimKwangSukDao;

import java.util.Scanner;

public class InsertMain {
    public static void main(String[] args) {
        // 관리자가 상품 1개를 등록합니다.
        KimKwangSukDao dao = new KimKwangSukDao();
        KimKwangSuk bean = new KimKwangSuk();

        Scanner scan = new Scanner(System.in);
        System.out.println("곡 제목 : ");
        String title = scan.next();

        // bean.setPnum(0); // 시퀀스로 대체 예정
        bean.setTitle(title);
        bean.setImage("xx.png");
        bean.setGenre("genre");
        bean.setLyrics("lyrics");
        bean.setExplanation("explanation");




        int cnt = -1; // -1을 실패한 경우라고 가정합니다.
        cnt = dao.insertData(bean);

        if (cnt==-1){
            System.out.println("곡 등록에 실패하였습니다.");
        }else{
            System.out.println("곡 등록에 성공하였습니다.");
        }
    }
}
