package org.project.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.project.data.Paths;
import org.project.utilities.Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {


    @FXML
    private BubbleChart<Number, Number> bubbleChart;

    @FXML
    private BarChart<String, Integer> barChart;


    @FXML
    private Button btnGraph1;

    @FXML
    private Button btnGraph2;

    @FXML
    private Button btnGraph3;

    @FXML
    private Button btnGraph4;

    @FXML
    private LineChart<String, Integer> lineChart;

    @FXML
    private PieChart pieChart;

    @FXML
    void loadBar(ActionEvent event) {
        loadBar();
    }

    /**
     * Cargo los datos al gráfico de barras
     */
    private void loadBar(){
        invisibleAll();
        barChart.setVisible(true);
        barChart.setTitle("Languages most popular");
        String data = Utilities.readTxt(Paths.DATA1); //obtengo los datos del txt
        loadDataToBar(data); //agrego los datos al pie con el formato específico
    }

    private void loadDataToBar(String data) {

        barChart.getData().clear();
        Pattern pattern = Pattern.compile("\\[(.*?),([0-9]+)]");
        Matcher matcher = pattern.matcher(data);
        XYChart.Series<String, Integer> barChartData = new XYChart.Series<>();

        while (matcher.find()) {
            String key = matcher.group(1);
            int value = Integer.parseInt(matcher.group(2));
            barChartData.getData().add(new XYChart.Data<>(key+" ", value));
        }

        barChart.getData().add(barChartData);


    }

    @FXML
    void loadBubble(ActionEvent event) {
        loadBubble();
    }

    /**
     * Carga los datos al gráfico de burbujas
     */
    private void loadBubble() {
        invisibleAll();
        bubbleChart.setVisible(true);
        bubbleChart.setTitle("Languages most popular");
        String data = Utilities.readTxt(Paths.DATA1); //obtengo los datos del txt
        loadDataToBubble(data); //agrego los datos al pie con el formato específico
    }

    private void loadDataToBubble(String data) {
        bubbleChart.getData().clear();

        Pattern pattern = Pattern.compile("\\[(.*?),([0-9]+)]");
        Matcher matcher = pattern.matcher(data);
        XYChart.Series<String, Integer> lineChartData = new XYChart.Series<>();

        while (matcher.find()) {
            String key = matcher.group(1);
            int value = Integer.parseInt(matcher.group(2));

            XYChart.Series serie = new XYChart.Series();
            serie.setName(key);
            int random = (int) (Math.random()*1000);
            serie.getData().add(new XYChart.Data<>(random, random, value));

            bubbleChart.getData().add(serie);


        }


    }

    @FXML
    void loadLine(ActionEvent event) {
        loadLine();
    }

    /**
     * Carga los datos al grafico de líneas
     */
    private void loadLine(){
        invisibleAll();
        lineChart.setVisible(true);
        lineChart.setTitle("Languages most popular");
        String data = Utilities.readTxt(Paths.DATA1); //obtengo los datos del txt
        loadDataToLine(data); //agrego los datos al pie con el formato específico

    }

    /**
     * Busca los patrones en los datos y los muestra
     * @param data
     */
    private void loadDataToLine(String data) {
        lineChart.getData().clear();
        Pattern pattern = Pattern.compile("\\[(.*?),([0-9]+)]");
        Matcher matcher = pattern.matcher(data);
        XYChart.Series<String, Integer> lineChartData = new XYChart.Series<>();

        while (matcher.find()) {
            String key = matcher.group(1);
            int value = Integer.parseInt(matcher.group(2));
            lineChartData.getData().add(new XYChart.Data<>(key+" ", value));
        }

        lineChart.getData().add(lineChartData);

    }

    @FXML
    void loadPie(ActionEvent event) {
        loadPie();
    }

    private void loadPie(){
        invisibleAll();
        pieChart.setVisible(true);
        pieChart.setTitle("Languages most popular");
        String data = Utilities.readTxt(Paths.DATA1); //obtengo los datos del txt
        loadDataToPie(data); //agrego los datos al pie con el formato específico

    }

    /**
     * Load the data to UI
     * @param data to show
     */
    private void loadDataToPie(String data) {
        pieChart.getData().clear();
        Pattern pattern = Pattern.compile("\\[(.*?),([0-9]+)]");
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String key = matcher.group(1);
            int value = Integer.parseInt(matcher.group(2));
            pieChart.getData().add(new PieChart.Data(key, value));
        }

    }

    /**
     * Se ejecuta al correr el programa
     */
    @FXML
    public void initialize() {
        invisibleAll();
        loadPie();
        loadLine();
        loadBar();
        loadBubble();

        loadPie();
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
