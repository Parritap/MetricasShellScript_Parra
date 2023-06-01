package org.project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import org.project.data.Paths;
import org.project.utilities.Utilities;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Controller {


    @FXML
    private BubbleChart<Number, Number> bubbleChart;

    @FXML
    private BarChart<String, Integer> barChart;


    @FXML
    private Button btnUserTimes;

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
    private ScatterChart<Double, Integer> scatterChart;

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
    
    private void loadBar(String dataSet, String title, boolean filePath){
        invisibleAll();
        barChart.setVisible(true);
        barChart.setTitle(title);
        String data;
        data = dataSet;
        if (filePath) data = Utilities.readTxt(dataSet); //obtengo los datos del txt
        
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
    
    @FXML
    void loadScatterResponseTime(ActionEvent event) {
        loadScatter(Paths.responseTime, "Tiempos de respuesta");
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
    
    private void loadScatter(String path, String title) {
    	invisibleAll();
        scatterChart.setVisible(true);
        scatterChart.setTitle(title);
        String data = Utilities.readTxt(path); 
        loadDataToScatter(data); 
    }
    
    
    private void loadDataToScatter(String data) {
        scatterChart.getData().clear();

        Pattern pattern = Pattern.compile("\\[(.*?),([\\.0-9]+)]");
        Matcher matcher = pattern.matcher(data);
        XYChart.Series<Double, Integer> scatterChartData = new XYChart.Series<>();
        while (matcher.find()) {
            double value = Double.parseDouble(matcher.group(2));
            Integer key  = Integer.parseInt(matcher.group(1));
            XYChart.Series serie = new XYChart.Series();
            scatterChartData.getData().add(new XYChart.Data<Double, Integer>(value, key));

        }
        scatterChart.getData().add(scatterChartData);

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
        loadPie(Paths.DATA1, "Languages most popular");
    }
    
    
    @FXML
    void loadPieNotif(ActionEvent event) {
    	
    	HashMap<String, Integer> contados = new HashMap<>();
    	
    	Stream.of("WARNING", "INFO", "ERROR", "EMERGENCY").
    	forEach(notif-> {
    		contados.put(notif, 0);
    	getStats("("+notif+")", 1 ).stream().forEach(
    			e->Arrays.asList(e).stream()
    			.forEach(f->contados.compute(f, (k, v)->v+1)));
    	});
    	
    	String k_v = contados.entrySet().stream().map(
    			e-> "["+e.getKey()+","+e.getValue()+"]")
    	        .collect(Collectors.joining(" "));
    	
        loadPie(k_v, "Notificiaciones", false);
    }
    
    @FXML
    void loadChartErrores(ActionEvent event) {
    	
    	HashMap<String, Integer> contados = new HashMap<>();
    	
    	Stream.of("Jan", "Feb", "Mar", "Apr", "May", "Jun",
    			"Jul", "Aug", "Sep", "Oct", "Nov", "Dec").
    	forEach(month->contados.put(month, 0));
    	getStats("(\\w{3}).* Failed (publickey|password)", 1 ).stream().forEach(
    			e->Arrays.asList(e).stream()
    			.forEach(f->contados.compute(f, (k, v)->v+1)));
    	
    	String k_v = contados.entrySet().stream().map(
    			e-> "["+e.getKey()+","+e.getValue()+"]")
    	        .collect(Collectors.joining(" "));
    	
        loadBar(k_v, "Ingresos fallidos por mes", false);
        
    }
    
    private void loadPie(String dataSet, String title, boolean filePath){
        invisibleAll();
        System.out.println(dataSet);
        pieChart.setVisible(true);
        pieChart.setTitle(title);
        String data;
        data = dataSet;
        if (filePath) data = Utilities.readTxt(dataSet); //obtengo los datos del txt
        
        loadDataToPie(data); //agrego los datos al pie con el formato específico

    }
    
    private void loadPie(String dataSet, String title){
    	loadPie(dataSet, title, true);
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
        loadPie("", "");
        loadLine();
        loadBar();
        loadBubble();

        loadPie("", "");
    }
    
    
    
    public ArrayList<String[]> getStats(String regex, int groups) {
    	
    	
    	String syslog = Utilities.readTxt("var/log/syslog");
    	Pattern pattern = Pattern.compile(regex);
    	Matcher matcher = pattern.matcher(syslog);
    	ArrayList<String[]> result = new ArrayList<>();
    	while (matcher.find()) {
    		String[] temp = new String[groups]; 
    		IntStream.rangeClosed(1, groups).
    		forEach(i->temp[i-1]=matcher.group(i));
    		result.add(temp);
        }
    	
    	return result;
    	
    }
    /**
     * Invisible all charts
     */
    private void invisibleAll() {
        pieChart.setVisible(false);
        lineChart.setVisible(false);
        barChart.setVisible(false);
        bubbleChart.setVisible(false);
        scatterChart.setVisible(false);

    }


    public void loadUserUsageTimes(ActionEvent actionEvent) {
        invisibleAll();
        pieChart.setVisible(true);
        pieChart.setTitle("Porcentaje de uso de los usuarios en el ultimo mes");
        String data = Utilities.readTxt(Paths.userTimes);
        loadDataToPie(data); //agrego los datos al pie con el formato específico
    }

    public void loadTESTUserUsageTimes (ActionEvent actionEvent){
    	
    	HashMap<String, Integer> contados = new HashMap<>();
    	
    	Stream.of("parra", "anubis", "osma", "aleandro").
    	forEach(user-> {
    		contados.put(user, 0);
    	getStats("Accepted public key for ("+user+")", 1 ).stream().forEach(
    			e->Arrays.asList(e).stream()
    			.forEach(f->contados.compute(f, (k, v)->v+1)));
    	});
    	
    	String k_v = contados.entrySet().stream().map(
    			e-> "["+e.getKey()+","+e.getValue()+"]")
    	        .collect(Collectors.joining(" "));
    	
        loadPie(k_v, "ingresos al sistema", false);
        
        
    }
}
