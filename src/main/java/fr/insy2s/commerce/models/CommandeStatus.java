package fr.insy2s.commerce.models;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum CommandeStatus {

    COMMANDE,
    PRET,
    LIVRE
}
