package org.hbrs.se1.ws23.uebung3.persistence;

import org.hbrs.se1.ws23.uebung2.*;
import org.hbrs.se1.ws23.uebung2.Container;
import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung2.Member;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;

import java.util.List;


public class Client {

    public static void main(String[] args) throws ContainerException, PersistenceException {

        Container container = Container.getInstance();

        ConcreteMember member_1 = new ConcreteMember(34);
        ConcreteMember member_2= new ConcreteMember(79);

        container.addMember(member_1);
        container.addMember(member_2);

        List<Member> members = container.getCurrentList();

        MemberView view = new MemberView();
        view.dump(members);



    }

}

