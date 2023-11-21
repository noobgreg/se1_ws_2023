package org.hbrs.se1.ws23.uebung3.persistence;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    private String location = "objects.ser";

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void save(List<E> member) throws PersistenceException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(location));
            oos.writeObject(member);
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,
                    "Could not save the members: " + e.getMessage());
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,
                            "Could not close the output stream: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public List<E> load() throws PersistenceException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(location));
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                return (List<E>) obj;
            } else {
                throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,
                        "Data read is not a List.");
            }
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,
                    "Could not load the members: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,
                    "Class not found during load operations:  " + e.getMessage());
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,
                            "Could not close the input stream: " + e.getMessage());
                }
            }
        }
    }
}
