package com.itgroup.jdbc;

import com.itgroup.bean.KimKwangSuk;
import com.itgroup.dao.KimKwangSukDao;

import java.util.Scanner;

public class UpdateMain {
    public static void main(String[] args) {
        // 특정 상품에 대한 정보를 수정합니다.
        KimKwangSukDao dao = new KimKwangSukDao();
        KimKwangSuk bean = new KimKwangSuk();

        Scanner scan = new Scanner(System.in);
        System.out.println("곡 번호 : ");
        int tracknumber = scan.nextInt();

        System.out.println("곡 제목 : ");
        String title = scan.next();

        bean.setImage("aa.png");
        bean.setGenre("장르");
        bean.setLyrics("가사");
        bean.setExplanation("정보");

        int cnt = -1; // -1을 실패한 경우라고 가정합니다.
        cnt = dao.updateData(bean);

        if (cnt==-1){
            System.out.println("곡 수정에 실패하였습니다.");
        }else{
            System.out.println("곡 수정에 성공하였습니다.");
        }
    }
}
