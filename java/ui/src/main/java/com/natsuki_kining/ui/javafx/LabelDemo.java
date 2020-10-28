package com.natsuki_kining.ui.javafx;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * label控件
 *
 * @Author natsuki_kining
 * @Date 2020/5/4 18:08
 **/
public class LabelDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //JavaFX API为Label提供了三个构造方法
        //一个空Label
        Label label1 = new Label();
        //一个带文本元素的Label
        Label search = new Label("Search");
        Label label2 = search;
        //一个带文本和图标的Label
        //Image image = new Image(getClass().getResourceAsStream("labels.jpg"));
        String url = "https://portrait.gitee.com/uploads/avatars/user/417/1253772_natsuki_kining_1578947444.png!avatar30";
        Image image = new Image(url);
        Label label3 = new Label("Search", new ImageView(image));

        label1.setText("label1");
        label1.setGraphic(new ImageView(image));
        label1.setTextFill(Color.web("#0076a3"));


        primaryStage.show();
    }

}
