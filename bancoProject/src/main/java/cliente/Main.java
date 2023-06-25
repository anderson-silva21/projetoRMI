package cliente;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import interfaces.GestorWebServiceInterface;

public class Main extends Application {

    private GestorWebServiceInterface gerente;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new URL("http://localhost:8080/gestor?wsdl");
        QName qname = new QName("http://servidor/", "GestorWebServiceService");
        QName qnamedois = new QName("http://servidor/", "GestorWebServicePort");
        Service service = Service.create(url, qname);
        gerente = service.getPort(qnamedois, GestorWebServiceInterface.class);

        primaryStage.setTitle("Sistema Bancário");

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Sistema Bancário");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: blue;");

        Button criarContaButton = new Button("Criar Conta");
        criarContaButton.setOnAction(e -> criarConta(primaryStage, statusLabel));

        Button removerContaButton = new Button("Remover Conta");
        removerContaButton.setOnAction(e -> removerConta(primaryStage, statusLabel));

        Button depositarButton = new Button("Depositar Dinheiro");
        depositarButton.setOnAction(e -> depositar(primaryStage, statusLabel));

        Button sacarButton = new Button("Sacar Dinheiro");
        sacarButton.setOnAction(e -> sacar(primaryStage, statusLabel));

        Button transferirButton = new Button("Transferir Dinheiro");
        transferirButton.setOnAction(e -> transferir(primaryStage, statusLabel));

        Button sairButton = new Button("Sair");
        sairButton.setOnAction(e -> primaryStage.close());

        root.getChildren().addAll(titleLabel, statusLabel, criarContaButton, removerContaButton,
                depositarButton, sacarButton, transferirButton, sairButton);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void criarConta(Stage primaryStage, Label statusLabel) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Criar Conta");
        dialog.setHeaderText(null);

        ButtonType criarButton = new ButtonType("Criar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(criarButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome");
        TextField contaField = new TextField();
        contaField.setPromptText("Número da Conta");

        grid.add(new Label("Nome:"), 0, 0);
        grid.add(nomeField, 1, 0);
        grid.add(new Label("Número da Conta:"), 0, 1);
        grid.add(contaField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == criarButton) {
                return nomeField.getText() + ";" + contaField.getText();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            String[] values = result.split(";");
            String nome = values[0];
            int conta = Integer.parseInt(values[1]);

            if (gerente.criarConta(nome, conta)) {
                statusLabel.setText("Conta criada com sucesso.");
            } else {
                statusLabel.setText("Não existe conta com esse número.");
            }
        });
    }

    private void removerConta(Stage primaryStage, Label statusLabel) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Remover Conta");
        dialog.setHeaderText(null);
        dialog.setContentText("Informe o número da conta:");

        dialog.showAndWait().ifPresent(result -> {
            int conta = Integer.parseInt(result);

            if (gerente.removerConta(conta)) {
                statusLabel.setText("Conta removida com sucesso.");
            } else {
                statusLabel.setText("Não existe conta com esse número.");
            }
        });
    }

    private void depositar(Stage primaryStage, Label statusLabel) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Depositar Dinheiro");
        dialog.setHeaderText(null);

        ButtonType depositarButton = new ButtonType("Depositar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(depositarButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        TextField contaField = new TextField();
        contaField.setPromptText("Número da Conta");
        TextField valorField = new TextField();
        valorField.setPromptText("Valor");

        grid.add(new Label("Número da Conta:"), 0, 0);
        grid.add(contaField, 1, 0);
        grid.add(new Label("Valor:"), 0, 1);
        grid.add(valorField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == depositarButton) {
                return contaField.getText() + ";" + valorField.getText();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            String[] values = result.split(";");
            int conta = Integer.parseInt(values[0]);
            double valor = Double.parseDouble(values[1]);

            if (gerente.depositar(conta, valor)) {
                statusLabel.setText("Depósito com sucesso.");
            } else {
                statusLabel.setText("Não existe conta para depósito. Tente outra conta.");
            }
        });
    }

    private void sacar(Stage primaryStage, Label statusLabel) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Sacar Dinheiro");
        dialog.setHeaderText(null);

        ButtonType sacarButton = new ButtonType("Sacar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(sacarButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        TextField contaField = new TextField();
        contaField.setPromptText("Número da Conta");
        TextField valorField = new TextField();
        valorField.setPromptText("Valor");

        grid.add(new Label("Número da Conta:"), 0, 0);
        grid.add(contaField, 1, 0);
        grid.add(new Label("Valor:"), 0, 1);
        grid.add(valorField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == sacarButton) {
                return contaField.getText() + ";" + valorField.getText();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            String[] values = result.split(";");
            int conta = Integer.parseInt(values[0]);
            double valor = Double.parseDouble(values[1]);

            if (gerente.saque(conta, valor)) {
                statusLabel.setText("Saque com sucesso.");
            } else {
                statusLabel.setText("Não existe saldo para saque na conta. Tente outro valor.");
            }
        });
    }

    private void transferir(Stage primaryStage, Label statusLabel) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Transferir Dinheiro");
        dialog.setHeaderText(null);

        ButtonType transferirButton = new ButtonType("Transferir", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(transferirButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        TextField conta1Field = new TextField();
        conta1Field.setPromptText("Conta de Origem");
        TextField conta2Field = new TextField();
        conta2Field.setPromptText("Conta de Destino");
        TextField valorField = new TextField();
        valorField.setPromptText("Valor");

        grid.add(new Label("Conta de Origem:"), 0, 0);
        grid.add(conta1Field, 1, 0);
        grid.add(new Label("Conta de Destino:"), 0, 1);
        grid.add(conta2Field, 1, 1);
        grid.add(new Label("Valor:"), 0, 2);
        grid.add(valorField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == transferirButton) {
                return conta1Field.getText() + ";" + conta2Field.getText() + ";" + valorField.getText();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            String[] values = result.split(";");
            int conta1 = Integer.parseInt(values[0]);
            int conta2 = Integer.parseInt(values[1]);
            double valor = Double.parseDouble(values[2]);

            if (gerente.transferir(conta1, conta2, valor)) {
                statusLabel.setText("Transferência com sucesso.");
            } else {
                statusLabel.setText("Não existe saldo para transferência. Tente outro valor.");
            }
        });
    }
}
