package org.hbrs.se1.ws23.solutions.uebung2;

import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Container;
import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;

public class Client {

    public static void main(String[] args) throws ContainerException, PersistenceException {
        Container container = Container.getInstance();
        ConcreteMember member = new ConcreteMember(34);
        container.addMember(member);
        container.store();

    }

}

