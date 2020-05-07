package com.route.node;

import com.ClientUIMain;
import com.controller.LoginController;
import com.controller.MessageController;
import com.controller.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.InputStream;

/**
 * @author Kevin
 * @Title: Node
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2019/1/21 15:52
 */
public class NodeRouter {
    /**
     * 去登录页面
     */
    public static void gotoLogin(){
        try {
            LoginController login = (LoginController) replaceSceneContent("/com/javafx/main/login_user.fxml", 500, 300);
            login.setController();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 去消息页面
     */
    public static void gotoMessage(){
        try {
            MessageController message = (MessageController) replaceSceneContent("/com/javafx/main/message.fxml", 735, 615);
            message.setController();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 去注册页面
     */
    public static void gotoRegisterUser() {
        try {
            RegisterController register = (RegisterController) replaceSceneContent("/com/javafx/main/register_user.fxml", 500, 300);
            register.setController();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Initializable replaceSceneContent(String fxml, int width, int height) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = ClientUIMain.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(ClientUIMain.class.getResource(fxml));
        Pane page;
        try {
            page = (Pane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, width, height);
        ClientUIMain.stage.setScene(scene);
        ClientUIMain.stage.sizeToScene();
        return (Initializable) loader.getController();
    }
}
