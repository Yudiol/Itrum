package com.yudiol.SpringDataJDBC.dao;

import com.yudiol.SpringDataJDBC.model.Book;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class BookDao {

    private static final String URL = "jdbc:postgresql://localhost:5432/springJDBCDB?useUnicode=yes&characterEncoding=UTF-8";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    private static Connection connection;

    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Book findByBookId(Long bookId) {
        Book book = new Book();
        ResultSet result = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE book_id = ?");
        ) {
            statement.setLong(1, bookId);
            result = statement.executeQuery();
            result.next();
            book.setBookId(result.getLong("book_id"));
            book.setTitle(result.getString("title"));
            book.setAuthor(result.getString("author"));
            book.setPublicationYear(result.getInt("publication_year"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return book;
    }

    public void save(Book book) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO books (title,author,publication_year) VALUES(?,?,?)")) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getPublicationYear());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Long bookId, Book book) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE books SET title = ?, author = ?, publication_year = ? WHERE book_id = ?")) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getPublicationYear());
            statement.setLong(4, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByBookId(Long bookId) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE book_id = ?")) {
            statement.setLong(1, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
