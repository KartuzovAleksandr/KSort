package com.ksort.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.ksort.KSort;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SecondaryPresenter {

    public static String s="100"; // max предел для генератора случайных чисел
    @FXML
    private View secondary;
    @FXML
    private TextField edit2;
    @FXML
    private Button button3;
    @FXML
    private Label label3;
    
    public void initialize() {
        secondary.setShowTransitionFactory(BounceInRightTransition::new);
        secondary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(KSort.MENU_LAYER)));
                appBar.setTitleText("Настройки");
            }
        });
        edit2.setFocusTraversable(false);    // убрать фокус по умолчанию
        label3.setText(label3.getText()+s);  // добавим максимальный предел
    }

    @FXML
    private void handleButton3Action(ActionEvent event) {
        if (edit2.getText().isEmpty()) return;  // если строка пуста - выход
        s=edit2.getText();
        label3.setText("Случайные числа генерируются от 0 до "+s);  // добавим максимальный предел        
    }
}
