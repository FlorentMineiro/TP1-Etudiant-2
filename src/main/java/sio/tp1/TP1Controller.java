package sio.tp1;

import com.sun.source.tree.Tree;
import javafx.beans.Observable;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sio.tp1.Model.Message;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TP1Controller implements Initializable {

    @FXML
    private Button cmdEnvoyer;
    @FXML
    private Button cmdRecevoir;
    @FXML
    private AnchorPane apEnvoyer;
    @FXML
    private AnchorPane apRecevoir;
    @FXML
    private Label lblTitre;
    @FXML
    private ListView lstExpediteurs;
    @FXML
    private ListView lstDestinataires;
    @FXML
    private TextField txtMessage;
    @FXML
    private Button cmdEnvoyerMessage;

    private HashMap<String, ArrayList<Message>> maMessagerie;


    @FXML
    private ComboBox cboDestinataires;
    @FXML
    private TreeView tvMessages;


    @FXML
    public void menuClicked(Event event) {
        if(event.getSource() == cmdEnvoyer)
        {
            lblTitre.setText("TP1 : Messagerie / Envoyer");
            apEnvoyer.toFront();
        }
        else//if(event.getSource() == cmdRecevoir)
        {
            lblTitre.setText("TP1 : Messagerie / Recevoir");
            apRecevoir.toFront();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTitre.setText("TP1 : Messagerie / Envoyer");
        apEnvoyer.toFront();

        lstExpediteurs.getItems().addAll("Enzo","Noa","Lilou","Milo");
        lstDestinataires.getItems().addAll("Enzo","Noa","Lilou","Milo");
        cboDestinataires.getItems().addAll("Enzo","Noa","Lilou","Milo");
        cboDestinataires.getSelectionModel().selectFirst();
        maMessagerie = new HashMap<>();

    }

    @FXML
    public void cmdEnvoyerMessageClicked(Event event)
    {
        if (txtMessage.getText() == "" && lstDestinataires.getSelectionModel().getSelectedItem() == null && lstDestinataires.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur !");
            alert.setHeaderText("Veuillez compléter les informations");
            alert.showAndWait();
        } else if (txtMessage.getText() == "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur !");
            alert.setHeaderText("Veuillez saisir un message");
            alert.showAndWait();
        }else if (lstDestinataires.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur !");
            alert.setHeaderText("Veuillez saisir un destinataire");
            alert.showAndWait();
        }else if (lstExpediteurs.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur !");
            alert.setHeaderText("Veuillez saisir un expediteur");
            alert.showAndWait();
        } else {
            String expediteur = lstExpediteurs.getSelectionModel().getSelectedItem().toString();
            String destinataire = lstDestinataires.getSelectionModel().getSelectedItem().toString();
            String contenuMessage = txtMessage.getText();

            Message message = new Message(expediteur,destinataire,contenuMessage);

            if (!maMessagerie.containsKey(destinataire)){
                maMessagerie.put(destinataire,new ArrayList<>());
            }
            maMessagerie.get(destinataire).add(message);
            //cboDestinataires.getSelectionModel().select(destinataire);
            cboDestinatairesClicked(null);


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Messagerie");
            alert.setHeaderText("Message envoyé");
            alert.showAndWait();
        }
    }

    @FXML
    public void cboDestinatairesClicked(Event event) {
        TreeItem racine = new TreeItem<>("Tous les messages");

       TreeItem noeudMenu = new TreeItem<>("Pas de commentaire");
       //TreeItem noeudMessage = new TreeItem<>("Message n°");



        String destinataireChoisi = cboDestinataires.getSelectionModel().getSelectedItem().toString();
        ArrayList<Message> messagesEnvoye = maMessagerie.get(destinataireChoisi);



        if (!(messagesEnvoye.isEmpty()) && messagesEnvoye != null){
            int compteurMessage = 1;
            for (Message messageRecu : messagesEnvoye){
                TreeItem noeudMessage = new TreeItem<>("Message n°" + compteurMessage);

                TreeItem noeudExpediteur = new TreeItem<>("De => "+messageRecu.getExpediteur());
                TreeItem noeudContenuMessage = new TreeItem<>("Message => "+ messageRecu.getContenuDuMessage());
                noeudMessage.getChildren().add(noeudExpediteur);
                noeudMessage.getChildren().add(noeudContenuMessage);
                racine.getChildren().add(noeudMessage);
                compteurMessage++;

            }
        }

        tvMessages.setRoot(racine);
    }
}