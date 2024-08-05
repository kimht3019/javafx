package com.itgroup.jdbc;

import com.itgroup.bean.KimKwangSuk;
import com.itgroup.dao.KimKwangSukDao;

import java.util.List;

public class SelectAllMain {
    public static void main(String[] args) {
        // 모든 상품 조회하기
        KimKwangSukDao dao = new KimKwangSukDao();
        List<KimKwangSuk> allProduct = dao.selectAll();
        System.out.println(allProduct.size());

        for (KimKwangSuk bean:allProduct){
            ShowData.printBean(bean);

        }

    }
}
