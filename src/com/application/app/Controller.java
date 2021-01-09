package com.application.app;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import com.guserinterfaces.config.DB;
import com.gusersinterfaces.controllers.EmployeController;
import com.gusersinterfaces.models.Employe;
import com.gusersinterfaces.models.Employes;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class Controller implements Initializable {
	ArrayList<String> typesArrayList = new ArrayList<String>();
	@FXML
	private TextField idEmploye;
	@FXML
	private TextField fnameId;
	@FXML
	private TextField lnameId;
	@FXML
	private TextField ageId;
	@FXML
	private DatePicker desId;
	@FXML
	private ComboBox<String> typeId;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	@FXML
	private TableView<Employes> tableId;
	@FXML
	private TableColumn<Employe, Integer> idCol;
	@FXML
	private TableColumn<Employe, String> nomCol;
	@FXML
	private TableColumn<Employe, String> prenomCol;
	@FXML
	private TableColumn<Employe, Integer> ageCol;
	@FXML
	private TableColumn<Employe, String> desCol;
	@FXML
	private TableColumn<Employe, String> typeCol;
	@FXML
	private TableColumn<Employe, Double> salaryCol;
	private  int idSelected; 
	EmployeController eController;
	DB crud;
	ObservableList<Employes> employeList;
	ObservableList<String> typeList = FXCollections.observableArrayList("vendeur", "representeur", "producteur",
			"Manutentionaire");

	public Controller() {
		eController = new EmployeController();
		crud = new DB();
		employeList = FXCollections.observableArrayList();
	}

	public void showEmploye() throws SQLException {
		ResultSet rs = crud.selectAll("employe");
		while (rs.next()) {
			int id = rs.getInt("id");
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String age = rs.getString("age");
			String date = rs.getString("DES");
			String type = rs.getString("type");
			Double salary = rs.getDouble("salary");
			Employes employes = new Employes(id, nom, prenom, age, date, type, salary);
			employeList.add(employes);
		}

	}

	public void initEmploye(URL url, ResourceBundle resourceBundle) {
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
		desCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
	}

	public void addEmploye() throws SQLException {
		tableId.setItems(employeList);
		initEmploye(null, null);
		typeId.setItems(typeList);

	}

	public void addInputEmploye() throws SQLException {
		String sql1 = "SELECT * FROM employe ORDER BY ID DESC LIMIT 1";
		Statement stmt = crud.configuration.connected().createStatement();
		ResultSet res = stmt.executeQuery(sql1);
		int id = 0;
		while (res.next()) {
			id = res.getInt("id");
		}
		id++;
		String nom = fnameId.getText();
		String prenom = lnameId.getText();
		String age = ageId.getText();
		String date = desId.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String type = typeId.getSelectionModel().getSelectedItem();
		String sql = "INSERT INTO employe SET nom ='" + nom + "', prenom='" + prenom + "', age=" + age + ", des='"
				+ date + "', type='" + type + "', salary=" + eController.calculerSalaire(type) + "";
		Double salary = eController.calculerSalaire(type);
		crud.createAndUpdateAndDelete(sql);

		Employes employes = new Employes(id, nom, prenom, age, date, type, salary);
		employeList.add(employes);
		fnameId.clear();
		lnameId.clear();
		ageId.clear();

	}

	public void updateEmploye() throws SQLException {
		
		int idDb = Integer.parseInt(idEmploye.getText());
		String nom = fnameId.getText();
		String prenom = lnameId.getText();
		String age = ageId.getText();
		String date = desId.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String type = typeId.getSelectionModel().getSelectedItem();
		String sql = "UPDATE employe SET nom ='" + nom + "', prenom='" + prenom + "', age=" + age + ", des='" + date
				+ "', type='" + type + "', salary=" + eController.calculerSalaire(type) + "  WHERE id=" + idDb;
		crud.createAndUpdateAndDelete(sql);
		Double salary = eController.calculerSalaire(type);
		Employes employes = new Employes(idDb, nom, prenom, age, date, type, salary);
		employeList.set(idSelected, employes);
		fnameId.clear();
		lnameId.clear();
		ageId.clear();
		System.out.println("Id of array is " + idSelected);
		System.out.println("Size of array is " + employeList.size());
	}

	public void deleteEmploye() throws SQLException {
		int idDb = Integer.parseInt(idEmploye.getText());
		String sql = "DELETE FROM employe WHERE id="+idDb;
		crud.createAndUpdateAndDelete(sql);
		employeList.remove(idSelected);
		fnameId.clear();
		lnameId.clear();
		ageId.clear();
	}

	public static final LocalDate LOCAL_DATE(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(dateString, formatter);
		return localDate;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			idEmploye.setVisible(false);
			showEmploye();
			addEmploye();
			tableId.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					idSelected = tableId.getSelectionModel().getSelectedIndex();
					System.out.println(idSelected);
					int idE = tableId.getSelectionModel().getSelectedItem().getId();
					idEmploye.setText(String.valueOf(idE));
					fnameId.setText(tableId.getSelectionModel().getSelectedItem().getNom());
					lnameId.setText(tableId.getSelectionModel().getSelectedItem().getPrenom());
					ageId.setText(tableId.getSelectionModel().getSelectedItem().getAge());
					desId.setValue(LOCAL_DATE(tableId.getSelectionModel().getSelectedItem().getDate()));
					System.out.println(tableId.getSelectionModel().getSelectedItem().getType());
					if (tableId.getSelectionModel().getSelectedItem().getType().equals("vendeur")) {
						typeId.getSelectionModel().select(0);
					} else if (tableId.getSelectionModel().getSelectedItem().getType().equals("representeur")) {
						typeId.getSelectionModel().select(1);

					} else if (tableId.getSelectionModel().getSelectedItem().getType().equals("producteur")) {
						typeId.getSelectionModel().select(2);

					} else if (tableId.getSelectionModel().getSelectedItem().getType().equals("Manutentionaire")) {
						typeId.getSelectionModel().select(3);

					}
				}

			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
