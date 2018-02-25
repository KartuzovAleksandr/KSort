package com.ksort.views;

import java.util.Arrays;      // для использования встроенной сортировки
import java.util.Collections; // для обратной сортировки reverseOrder()
import java.util.Random;      // для генерации случайных чисел
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.ksort.KSort;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PrimaryPresenter {

    @FXML
    private View primary;
    @FXML
    private TextField edit1;
    @FXML
    private Button button1;
    @FXML
    private Label label1;
    @FXML
    private Button button2;
    @FXML
    private Label label2;
    @FXML
    private RadioButton RadioButton1;
    @FXML
    private ToggleGroup SortOrder;
    @FXML
    private RadioButton RadioButton2;

    public void initialize() {
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(KSort.MENU_LAYER)));
                appBar.setTitleText("Сортировка массива");              
            }
        });
        edit1.setFocusTraversable(false); // убрать фокус по умолчанию
    }

    Integer m[]; // объявляем пустой массив
    Random r=new Random(); // создаем класс для генерации случайных чисел
    
    @FXML
    private void handleButton1Action(ActionEvent event) { 
        String s=edit1.getText(); // введенный размер (текст)
        if (s.isEmpty()) return;  // если строка пуста - выход
        int n=Integer.parseInt(s); // размерность массива, введенная в поле edit1
        m=new Integer[n]; // создаем массив размерности n
        for (int i=0; i<n; i++)  // заполняем массив случайными числами
            m[i]=r.nextInt(Integer.parseInt(SecondaryPresenter.s)); // случайное целое от 0 до числа, введенного в настройках
        label1.setText(Arrays.toString(m)); // вывод исходного массива в тестовую метку label1
        button2.setDisable(false); // активизируем кнопку сортировки - массив есть !!!
    }
    
    @FXML
    private void handleButton2Action(ActionEvent event) { 
        if (RadioButton1.isSelected()) // кнопка RadioButton1 выбрана (отмечена)
            Arrays.sort(m); // сортируем массив m по возрастанию
        else // состояние кнопки RadioButton2 не проверяем, т.к. они противоположны по значению (объединены в ToggleGroup)
            Arrays.sort(m, Collections.reverseOrder()); // по убыванию
        label2.setText(Arrays.toString(m)); // вывод результируещего массива в тестовую метку label2
    }    
   
}
