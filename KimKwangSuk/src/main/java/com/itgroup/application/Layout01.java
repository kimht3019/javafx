package com.itgroup.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Layout01 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        HBox container = new HBox();
        // Insets 클래스 : 컨테이너 내부의 요소와 경계 사이의 간격(여백)을 정의해주는 클래스
        container.setPadding(new Insets(20));

        //텍스트 필드랑 확인창 사이를 띄운다.
        container.setSpacing(30);

        String myStyle = "-fx-background-color:green;-fx-opacity:10.1;";
        container.setStyle(myStyle);

        // 글자 입력이 가능한 한줄짜리 입력 상자
        TextField textField = new TextField();
        textField.setPrefWidth(200);


        Button button = new Button();
        button.setText("확인");
        button.setPrefWidth(60);


        button.setOnAction((event)->{
            //콘솔창에 내용이 나오게 하는 부분
            String text = textField.getText();
            System.out.println(text + "~~하하하");
            Platform.exit();
        });



        container.getChildren().add(textField);
        container.getChildren().add(button);

        Scene scene = new Scene(container, 330, 120);

        stage.setScene(scene);
        stage.setTitle("레이아웃 01");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
