package com.itgroup.jdbc;

import com.itgroup.bean.KimKwangSuk;
import com.itgroup.bean.KimKwangSuk;

public class ShowData {
    public static void printBean(KimKwangSuk bean) {



        int tracknumber = bean.getTracknumber();
        String title = bean.getTitle() ;
        String image = bean.getImage() ;
        String genre = bean.getGenre();
        String lyrics = bean.getLyrics();
        String explanation = bean.getExplanation();



        System.out.println("곡 번호 : " + tracknumber);
        System.out.println("제목 : " + title);
        System.out.println("이미지 : " + image);
        System.out.println("장르 : " + genre);
        System.out.println("가사 : " + lyrics);
        System.out.println("해석 : " + explanation);
        System.out.println("======================");
    }
}
