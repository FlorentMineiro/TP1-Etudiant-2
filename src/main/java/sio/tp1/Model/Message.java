package sio.tp1.Model;

public class Message
{
    private String expediteur;
    private String destinataire;
    private String contenuDuMessage;

    public Message(String expediteur, String destinataire, String contenuDuMessage) {
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.contenuDuMessage = contenuDuMessage;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public String getContenuDuMessage() {
        return contenuDuMessage;
    }
}
