package com.itgroup.jdbc;

import com.itgroup.bean.KimKwangSuk;
import com.itgroup.dao.KimKwangSukDao;
import com.itgroup.utility.Paging;

import java.util.List;
import java.util.Scanner;

public class SelectPagination {
    public static void main(String[] args) {
        // 검색 모드와 페이지 네이션 기능을 구현합니다.

        // 화면이없어서 어쩔 수 없이 치는 부분.(출력)
        Scanner scan = new Scanner(System.in);
        System.out.print("몇 페이지 볼꺼니?");
        String pageNumber = scan.next();

        System.out.print("페이지 당 몇 건씩 볼꺼니?");
        String pageSize = scan.next();  //10


        System.out.print("all, genre 중 1개 입력 : ");
        String mode = scan.next(); // bread     검색 모드(무엇을 검색할 것인가?)


        KimKwangSukDao dao = new KimKwangSukDao();
        int totalCount = dao.getTotalCount(mode); //23

        String url = "prList.jsp";
        String keyword = "";
        Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword);
        pageInfo.displayInformation();

        List<KimKwangSuk> productList = dao.getPaginationData(pageInfo);

        for (KimKwangSuk bean:productList){
            ShowData.printBean(bean);
        }
    }
}




















