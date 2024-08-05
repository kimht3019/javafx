package com.itgroup.jdbc;

import com.itgroup.dao.KimKwangSukDao;
import com.itgroup.dao.KimKwangSukDao;

import java.util.Scanner;

public class SelectTotalCount {
    public static void main(String[] args) {
        // 전체 또는 카테고리별 상품의 갯수를 반환해 줍니다.
        Scanner scan = new Scanner(System.in);
        System.out.print("all, genre 중 1개 입력 : ");

        String category = scan.next();

        KimKwangSukDao dao = new KimKwangSukDao();
        int totalCount = dao.getTotalCount(category);

        if (category.equals("all")){
            System.out.println("전체 곡 개수 : " + totalCount);
        }else{
            String message = "카테고리 %s의 개수 : %d\n";
            System.out.printf(message, category, totalCount);
        }
    }
}
