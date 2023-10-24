package org.hbrs.se1.ws23.uebung2.test;
import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Container;
import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ContainerTest {

    private Container container;
    private ConcreteMember member_one;
    private ConcreteMember member_two;

    @BeforeEach
    void setUp() {
        container = new Container();
        member_one = new ConcreteMember(1);
        member_two = new ConcreteMember(2);
    }

    @Test
    void newTest() {
        Container container_test = new Container();
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
        container.addMember(member_one);
        // Capture the console output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        container.dump();

        // Reset the console output to its original state
        System.setOut(new PrintStream(System.out));

        String expectedOutput = "Member (ID = 1)";
        Assertions.assertTrue(output.toString().contains(expectedOutput),
                "The dump output should contain: " + expectedOutput);
    }

    @Test
    void addNullTest() throws ContainerException {
        member_two = new ConcreteMember(0);
        container.addMember(member_one);
        int result = container.size();
        Assertions.assertEquals(1, result);
    }


}