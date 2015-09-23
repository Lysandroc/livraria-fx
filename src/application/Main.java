package application;
	


import java.io.IOException;
import java.util.ArrayList;

import br.com.casadocodigo.livraria.produtos.Produto;
import dao.ProdutoDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

@SuppressWarnings({"unchecked","rawtypes"})
public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		TableColumn columnNome = new TableColumn<>("Nome");
		columnNome.setMaxWidth(230);
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn columnDescricao = new TableColumn<>("Descriçao");
		columnDescricao.setMaxWidth(230);
		columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		
		TableColumn columnValor = new TableColumn<>("Valor");
		columnValor.setMaxWidth(230);
		columnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		
		TableColumn columnIsbn = new TableColumn<>("ISBN");
		columnIsbn.setMaxWidth(230);
		columnIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		
		ArrayList<Produto> produtos = new ProdutoDAO().lista();
		TableView tableView = new TableView<>(FXCollections.observableArrayList(produtos));
		tableView.getColumns().addAll(columnNome, columnDescricao, columnValor, columnIsbn);
		
		tableView.setPrefWidth(680);
		VBox vbox = new VBox(tableView);
		vbox.setPadding(new Insets(70,0,0,10));
		
		Group group = new Group();
		Scene scene = new Scene(group, 700, 510);
		
		Label label = new Label("Listagem de livros");
		label.setFont(Font.font("Lucida Grande", FontPosture.REGULAR, 30));
		label.setPadding(new Insets(20, 0, 10, 10));
		
		Label progresso = new Label();
		progresso.setLayoutX(485);
		progresso.setLayoutY(30);
		
		Button button = new Button("Exportar CSV");
		button.setLayoutX(590);
		button.setLayoutY(25);
		
//		button.setOnAction(new EventHandler<ActionEvent>() {
//	        @Override
//	        public void handle(ActionEvent event) {
//          	exportarEmCSV(produtos);
//	        }
//	    });
		
//		button.setOnAction(event -> { 
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					dormePorVinteSegundos();
//					exportarEmCSV(FXCollections.observableArrayList(produtos));
//					
//				}
//			}).start();
//		});
		
		button.setOnAction(event -> { 
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					dormePorVinteSegundos();
					exportarEmCSV(FXCollections.observableArrayList(produtos));
					return null;
				}			
			};
			
			task.setOnRunning(new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent e) {
					progresso.setText("Exportando...");
				}
			});
			
			task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent event) {
					progresso.setText("Concluido.");
				}
			});
			new Thread(task).start();
		});
		
		group.getChildren().addAll(label, vbox, button, progresso);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Sistema da livraria com java FX");
		primaryStage.show();
	}
	
	public static void main(String[] args)  {
		launch(args);
	}
	
	public void dormePorVinteSegundos() {
		try {
			Thread.sleep(20000);
		} catch(InterruptedException e) {
			System.out.println("Ops, um erro ocorreu: "+e);
		} 
	}
	public void exportarEmCSV(ObservableList<Produto> produtos) {
		try {
        	new Exportador().paraCSV(produtos);
        } catch(IOException io) {
        	System.out.println("Erro ao exportar: "+ io);
        }
	}
}
