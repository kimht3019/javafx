package com.itgroup.jdbc;

import com.itgroup.dao.KimKwangSukDao;
import com.itgroup.dao.KimKwangSukDao;

import java.util.Scanner;

public class DeleteMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("삭제할 곡 번호 : ");
        int pnum = scan.nextInt();

        KimKwangSukDao dao = new KimKwangSukDao();
        int cnt = -1;
        cnt = dao.deleteData(pnum);

        if (cnt == -1){
            System.out.println("곡 삭제에 실패하였습니다.");
        }else{
            System.out.println("곡 삭제에 성공하였습니다.");
        }

    }
}
