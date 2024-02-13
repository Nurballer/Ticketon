package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Person;
import com.company.repositories.interfaces.IPersonRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PersonRepository implements IPersonRepository {
    private final IDB db;  // Dependency Injection

    public PersonRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createPerson(Person Person) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO Persons(name,age,email) VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, Person.getName());
            st.setInt(2, Person.getAge());
            st.setString(3, Person.getEmail());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public Person getPerson(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,name,age,email FROM Persons WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Person(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }

        return null;
    }

    @Override
    public List<Person> getAllPersons() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,name,age,email FROM Persons";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Person> Persons = new LinkedList<>();
            while (rs.next()) {
                Person Person = new Person(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email"));

                Persons.add(Person);
            }

            return Persons;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }

        return null;
    }
}
