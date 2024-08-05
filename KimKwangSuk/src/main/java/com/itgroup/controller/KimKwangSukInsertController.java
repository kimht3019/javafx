package com.itgroup.controller;

import com.itgroup.bean.KimKwangSuk;
import com.itgroup.dao.KimKwangSukDao;
import com.itgroup.utility.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;


public class KimKwangSukInsertController implements Initializable {
    // fxml 파일 내에서 "fxml + 변수이름"으로 명명했습니다.
    @FXML
    private TextField fxmlTitle;
    @FXML
    private TextField fxmlImage;
    @FXML
    private TextField fxmlGenre;
    @FXML
    private TextField fxmlLyrics;
    @FXML
    private TextField fxmlExplanation;

    KimKwangSukDao dao = null;
    KimKwangSuk bean = null; // 상품 1개를 의미하는 빈 클래스

    public void onKimKwangSukInsert(ActionEvent event) {
        // 기입한 상품 목록을 데이터 베이스에 추가합니다.
        // event 객체는 해당 이벤트를 발생시킨 객체입니다.
        System.out.println(event);

        boolean bool = validationCheck();
        if (bool == true) {
            int cnt = -1;
            cnt = insertDatabase();
            if (cnt == 1) { // 인서트 성공시
                Node source = (Node) event.getSource(); // 강등
                Stage stage = (Stage) source.getScene().getWindow(); // 강등
                stage.close(); // 현재 창을 닫습니다.
            }
        } else {
            System.out.println("등록 실패");
        }
    }

    private int insertDatabase() {
        // 1건의 데이터인 bean을 dao를 사용하여 데이터 베이스에 추가합니다.
        int cnt = -1; // 작업 실패
        cnt = dao.insertData(this.bean);
        if (cnt == -1) {
            String[] message = new String[]{"곡 등록", "곡 등록 실패", "곡 등록을 실패하였습니다."};
            Utility.showAlert(Alert.AlertType.ERROR, message);
        }
        return cnt;
    }

    private boolean validationCheck() {
        // 유효성 검사를 통과하면 true가 됩니다.
        String[] message = null;

        /*String title = fxmlTitle.getText().trim();
        if(title.length() <= 1 || title.length() >= 30){
            message = new String[]{"유효성 검사 : 제목", "길이 제한 위배", "제목은 1글자 이상 30글자 이하이어야 합니다."} ;
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        Label fxmlTitle = null;
        String genre = fxmlGenre.getText().trim();
        if(title.length() <= 2 || title.length() >= 16){
            message = new String[]{"유효성 검사 : 장르", "길이 제한 위배", "제조 회사명은 2글자 이상 16글자 이하이어야 합니다."} ;
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }*/


        String title = fxmlTitle.getText().trim();
        String image = fxmlImage.getText().trim();
        String genre = fxmlGenre.getText().trim();
        String lyrics = fxmlLyrics.getText().trim();
        String explanation = fxmlExplanation.getText().trim();


        // 유효성 검사가 통과되면 비로소 객체 생성합니다.
        this.bean = new KimKwangSuk();
        bean.setTitle(title);
        bean.setImage(image);
        bean.setGenre(genre);
        bean.setLyrics(lyrics);
        bean.setExplanation(explanation);
        // 사용자가 입력한 key인 한글 카테고리 이름을 value인 영문으로 변환시켜 셋팅합니다.


        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dao = new KimKwangSukDao();

        // 최초 시작시 콤보 박스의 0번째 항목 선택하기
        //fxmlGenre.getSelectionModel().select(0);
    }
}
