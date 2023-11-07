package org.hbrs.se1.ws23.uebung2;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;

import java.util.ArrayList;
import java.util.List;

public class Container {


    // Einzige Instanz der Klasse, die privat und statisch ist
    private static Container container;
    // Liste um Member-Objekte zu speichern
    private List<Member> members;
    private PersistenceStrategy<Member> persistenceStrategy;

    // private Konstruktor verhindert die Instanziierung von aussen
    private Container() {
        members = new ArrayList<>();
    }

    // statische Methode, um auf die einzige Instanz zuzugreifen
    public static Container getInstance() {

        if (container == null) {
            container = new Container();
        }
        return container;
    }


    public void setPersistenceStrategy(PersistenceStrategy<Member> strategy) {
        this.persistenceStrategy = strategy;
    }

    public void store() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.NoStrategyIsSet,
                    "Keine Persistenz-Strategie vorhanden!"
            );
        }
        persistenceStrategy.save(members);
    }


    public void load() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.NoStrategyIsSet,
                    "Keine Persistenz-Strategie vorhanden!"
            );
        }
        members = persistenceStrategy.load();
    }



    public void addMember(Member member) throws ContainerException {
        for (Member m : members) {
            if (m.getID().equals(member.getID())) {
                throw new ContainerException(member.getID());
            }
        }
        members.add(member);
    }


    public String deleteMember(Integer id) {
        for (Member m : members) {
            if (m.getID().equals(id)) {
                members.remove(m);
                return "Member mit der ID [" + id + "] wurde erfolgreich entfernt.";
            }
        }
        return "Kein Member mit der ID [" + id + "] vorhanden.";
    }

/*
    public void dump() {
        for (Member m : members) {
            System.out.println(m.toString());
        }
    }
*/

    public int size() {
        return members.size();
    }

    // Methode um den Zustand des Containers zu resetten
    public void clear() {
        members.clear();
    }

    public List<Member> getCurrentList() {
        return members;
    }

}