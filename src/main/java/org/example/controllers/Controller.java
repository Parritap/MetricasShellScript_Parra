package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class Controller {


    @FXML
    private BubbleChart<?, ?> bubbleChart;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private TableView<?> tblPie;

    @FXML
    private Button btnGraph1;

    @FXML
    private Button btnGraph2;

    @FXML
    private Button btnGraph3;

    @FXML
    private Button btnGraph4;

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private PieChart pieChart;



    @FXML
    void loadBar(ActionEvent event) {

    }

    @FXML
    void loadBubble(ActionEvent event) {

    }

    @FXML
    void loadLine(ActionEvent event) {

    }

    @FXML
    void loadPie(ActionEvent event) {
        loadPie();
    }

    private void loadPie(){
        invisibleAll();
        pieChart.setVisible(true);
        tblPie.setVisible(true);

        
    }

    @FXML
    public void initialize() {
        invisibleAll();

        pieChart.getData().add(new PieChart.Data("Java", 50));
        pieChart.getData().add(new PieChart.Data("C++", 20));
        pieChart.getData().add(new PieChart.Data("Python", 60));
        pieChart.setVisible(true);


    }

    /**
     * Invisible all charts
     */
    private void invisibleAll() {
        pieChart.setVisible(false);
        lineChart.setVisible(false);
        barChart.setVisible(false);
        bubbleChart.setVisible(false);
    }


}
