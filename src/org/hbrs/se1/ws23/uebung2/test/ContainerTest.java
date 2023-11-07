package org.hbrs.se1.ws23.uebung2.test;
import org.hbrs.se1.ws23.uebung2.*;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyMongoDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

class ContainerTest {

    private Container container;
    private ConcreteMember member_one;
    private ConcreteMember member_two;
    private List<Member> list = null;
    private MemberView view;
    private ByteArrayOutputStream output;

    @BeforeEach
    void setUp() {
        container = Container.getInstance();
        container.clear();
        member_one = new ConcreteMember(1);
        member_two = new ConcreteMember(2);
        view = new MemberView();
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    void newTest() {
        Container container_test = Container.getInstance();
        Assertions.assertNotNull(container_test);
    }

    @Test
    void addMemberTest() throws ContainerException {
        container.addMember(member_one);
        Assertions.assertEquals(1, container.size());
        container.addMember(member_two);
        Assertions.assertEquals(2, container.size());
    }

    @Test
    void addMemberExceptionTest() throws ContainerException {
        member_one = new ConcreteMember(1);
        member_two = new ConcreteMember(1);

        try {
            container.addMember(member_one);
            container.addMember(member_two); // This should throw an exception
            Assertions.fail("An exception should be thrown when adding a member with a duplicate ID.");
        } catch (ContainerException e) {
            // Check if the exception message is as expected
            Assertions.assertEquals("Das Member-Objekt mit der ID [1] ist bereits vorhanden!", e.getMessage());
        }
    }

    @Test
    void posDeleteMemberTest() throws ContainerException {
        container.addMember(member_one);
        Assertions.assertEquals(1, container.size());
        container.addMember(member_two);
        Assertions.assertEquals(2, container.size());
        container.deleteMember(1);
        Assertions.assertEquals(1, container.size());
        container.deleteMember(2);
        Assertions.assertEquals(0, container.size());
    }

    @Test
    void negDeleteMemberTest() throws ContainerException {
        container.addMember(member_one);
        Assertions.assertEquals(1, container.size());
        container.addMember(member_two);
        Assertions.assertEquals(2, container.size());
        String result = container.deleteMember(3);
        Assertions.assertEquals("Kein Member mit der ID [3] vorhanden.", result);
    }


    @Test
    void sizeTest() throws ContainerException {
        container.addMember(member_one);
        container.addMember(member_two);
        int result = container.size();
        Assertions.assertEquals(2, result);
    }

    @Test
    void dumpTest() throws ContainerException {
        container.addMember(new ConcreteMember(1));
        List<Member> list = container.getCurrentList();

        view.dump(list);

        // Convert output stream to a string
        String outputString = output.toString();

        // Reset the console output to its original state
        System.setOut(System.out);

        String expectedOutput = "Member (ID = 1)";
        Assertions.assertTrue(outputString.contains(expectedOutput),
                "The dump output should contain: " + expectedOutput);
    }

    @Test
    void addNullTest() throws ContainerException {
        member_two = new ConcreteMember(0);
        container.addMember(member_one);
        int result = container.size();
        Assertions.assertEquals(1, result);
    }

    @Test
    void testStoreWithoutStrategy() {
        // Use the singleton instance of Container
        Assertions.assertThrows(PersistenceException.class, container::store);
    }

    @Test
    void testLoadWithoutStrategy() {
        // Use the singleton instance of Container
        Assertions.assertThrows(PersistenceException.class, container::load);
    }

    @Test
    void testMongoDBExceptions() throws UnsupportedOperationException {
        PersistenceStrategyMongoDB persistenceStrategyMongoDB = new PersistenceStrategyMongoDB();
        try {
            persistenceStrategyMongoDB.save(list);
            Assertions.fail("An exception should be thrown because the method save is not implemented");
        } catch (UnsupportedOperationException e) {
            // Check if the exception message is as expected
            Assertions.assertEquals("Not implemented!", e.getMessage());
        }

        try {
            persistenceStrategyMongoDB.load();
            Assertions.fail("An exception should be thrown because the method load is not implemented");
        } catch (UnsupportedOperationException e) {
            // Check if the exception message is as expected
            Assertions.assertEquals("Not implemented!", e.getMessage());
        }

    }


}