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

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;


public class KimKwangSukUpdateController implements Initializable {
    // fxml 파일 내에서 "fxml + 변수이름"으로 명명했습니다.
    @FXML
    private TextField fxmlTracknumber;
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

    private KimKwangSuk oldbean = null; // 수정될 행의 정보
    private KimKwangSuk newbean = null; // 수정할 행(객체)


    public void setBean(KimKwangSuk bean) {
        this.oldbean = bean ;

        fillPreviousData();

        fxmlTracknumber.setVisible(false);
    }

    private void fillPreviousData() {
        fxmlTracknumber.setText(String.valueOf(this.oldbean.getTracknumber()));
        fxmlTitle.setText(this.oldbean.getTitle());
        fxmlImage.setText(this.oldbean.getImage());
        fxmlGenre.setText(this.oldbean.getGenre());
        fxmlLyrics.setText(this.oldbean.getLyrics());
        fxmlExplanation.setText(this.oldbean.getExplanation());
    }


    public void onKimKwangSukUpdate(ActionEvent event) {
        // 먼저, 유효성 검사를 진행합니다.
        boolean bool = validationCheck();

        // 사용자가 변경한 내역을 데이터베이스에 업데이트 시킵니다.
        if(bool == true){
            KimKwangSukDao dao = new KimKwangSukDao() ;
            int cnt = -1 ; // -1이면 실패
            cnt = dao.updateData(this.newbean);

            if(cnt == -1){
                System.out.println("수정 실패");

            }else{ // 수정이 되었으므로 창을 닫습니다.
                Node source = (Node)event.getSource();
                Stage stage = (Stage)source.getScene().getWindow();
                stage.close();
            }
        }else{
            System.out.println("유효성 검사를 통과하지 못했습니다.");
        }

    }

    private boolean validationCheck() {
        // 유효성 검사를 통과하면 true가 됩니다.
        String[] message = null;

        int idx = Integer.valueOf(fxmlTracknumber.getText().trim());

        String title = fxmlTitle.getText().trim();
        if(title.length() <= 1 || title.length() >= 30){
            message = new String[]{"유효성 검사 : 제목", "길이 제한 위배", "제목은 1글자 이상 30글자 이하이어야 합니다."} ;
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        Label fxmlTitle = null;
        String genre = fxmlGenre.getText().trim();
        if(title.length() <= 2 || title.length() >= 16){
            message = new String[]{"유효성 검사 : 장르", "길이 제한 위배", "장르는 2글자 이상 16글자 이하이어야 합니다."} ;
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String image = fxmlImage.getText().trim() ;
        System.out.println(image);

        if(image == null || image.length() < 5){
            message = new String[]{"유효성 검사 : 이미지", "필수 입력 체크", "1번 이미지는 필수 입력 사항니다."} ;
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        boolean bool = false;
        // startsWith()와 endsWith()
        bool = image.endsWith(".jpg") || image.endsWith(".png") ;
        if(!bool){
            message = new String[]{"유효성 검사 : 이미지", "확장자 점검", "이미지의 확장자는 '.jpg' 또는 '.png' 이하이어야 합니다."} ;
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String lyrics = fxmlLyrics.getText().trim();
        if(title.length() <= 1){
            message = new String[]{"유효성 검사 : 장르", "길이 제한 위배", "장르는 1글자 이상 이어야 합니다."} ;
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }

        String explanation = fxmlExplanation.getText().trim();
        if(title.length() <= 1){
            message = new String[]{"유효성 검사 : 장르", "길이 제한 위배", "장르는 1글자 이상 이어야 합니다."} ;
            Utility.showAlert(Alert.AlertType.WARNING, message);
            return false;
        }



        // 유효성 검사가 통과되면 비로소 객체 생성합니다.
        this.newbean = new KimKwangSuk();
        newbean.setTracknumber(idx);
        newbean.setTitle(title);
        newbean.setImage(image);
        newbean.setGenre(genre);
        newbean.setLyrics(lyrics);
        newbean.setExplanation(explanation);


        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
