package com.natsuki_kining.ui.javafx.demo01;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 窗体切换
 *
 * @Author natsuki_kining
 * @Date 2020/5/4 20:29
 **/
public class FormSwitch extends Application {

    private Stage stage;
    private boolean answer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        // 窗口点击叉号关闭询问
        stage.setOnCloseRequest(event -> {
            event.consume();  // 消除默认事件
            handleClose();
        });

        // 布局
        Button button = new Button("关闭窗口");

        // 鼠标点击关闭窗口
        button.setOnMouseClicked(event -> handleClose());

        VBox vBox = new VBox();
        vBox.getChildren().add(button);
        Scene scene = new Scene(vBox, 400, 400);

        stage.setScene(scene);
        stage.show();
    }

    public void handleClose() {
        // 接收窗体返回值
        boolean ret = display("关闭窗口", "是否关闭窗口？");
        System.out.println(ret);
        if (ret) {
            stage.close();
        }

    }

    /**
     * @param title 标题
     * @param msg   消息
     */
    private boolean display(String title, String msg) {
        // 创建舞台
        Stage stage = new Stage();

        // 设置显示模式
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);

        // 创建控件
        Button buttonYes = new Button("是");
        buttonYes.setOnMouseClicked(event -> {
            answer = true;
            stage.close();
        });

        Button buttonNo = new Button("否");
        buttonNo.setOnMouseClicked(event -> {
            answer = false;
            stage.close();
        });

        Label label = new Label(msg);

        // 创建布局
        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, buttonYes, buttonNo);
        vBox.setAlignment(Pos.CENTER); // 布局居中显示

        // 创建场景
        Scene scene = new Scene(vBox, 200, 200);

        // 显示舞台
        stage.setScene(scene);
//        stage.show();
        stage.showAndWait();  // 等待窗体关闭才继续

        // 窗体返回值
        return answer;
    }
}
