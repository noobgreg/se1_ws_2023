package org.hbrs.se1.ws23.uebung3.persistence.test;

import org.hbrs.se1.ws23.uebung3.persistence.*;
import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Container;
import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung2.Member;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContainerTestNew {

    private Container container;
    private PersistenceStrategy<Member> mongoStrategy;
    private PersistenceStrategy<Member> streamStrategy;

    @BeforeAll
    void setup() {
        container = Container.getInstance();
        mongoStrategy = new PersistenceStrategyMongoDB<>();
        streamStrategy = new PersistenceStrategyStream<>();
    }

    @AfterEach
    void tearDown() {
        container.setPersistenceStrategy(null);
        container.clear(); // clear the members if this method is available
    }


    @Test
    void testNoStrategySet() {
        PersistenceException exception = assertThrows(PersistenceException.class, container::store);
        assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet, exception.getExceptionTypeType());
    }

    @Test
    void testMongoDBStrategy() {
        container.setPersistenceStrategy(mongoStrategy);
        assertThrows(UnsupportedOperationException.class, container::store);
    }

    @Test
    void testFaultyFileLocation() {
        ((PersistenceStrategyStream<Member>) streamStrategy).setLocation("/invalid/directory/objects.ser");
        container.setPersistenceStrategy(streamStrategy);
        assertThrows(PersistenceException.class, container::store);
    }

    @Test
    void testRoundTrip() throws ContainerException, PersistenceException {
        ((PersistenceStrategyStream<Member>) streamStrategy).setLocation("objects.ser");
        container.setPersistenceStrategy(streamStrategy);

        Member member = new ConcreteMember(42);
        container.addMember(member);
        assertEquals(1, container.getCurrentList().size());

        container.setPersistenceStrategy(streamStrategy);
        container.store();

        container.deleteMember(member.getID());
        assertEquals(0, container.getCurrentList().size());

        container.load();
        List<Member> members = container.getCurrentList();
        assertNotNull(members);
        assertFalse(members.isEmpty());
        assertEquals(1, members.size());

        // Convert the loaded member to a string and compare with the original member's string
        // Convert the member to a string using its toString method
        String memberAsString = member.toString();
        String loadedMemberAsString = members.get(0).toString();
        assertEquals(memberAsString, loadedMemberAsString);
    }
}

