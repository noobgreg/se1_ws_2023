package org.hbrs.se1.ws23.uebung2;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private List<Member> members = new ArrayList<>();

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

    public void dump() {
        for (Member m : members) {
            System.out.println(m.toString());
        }
    }

    public int size() {
        return members.size();
    }

}