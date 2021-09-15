package com.gbh.gbhapi.resource;

import com.gbh.gbhapi.model.Book;
import com.gbh.gbhapi.model.IBookResource;
import com.gbh.gbhapi.model.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookResourceImpl implements IBookResource {

    Book book = new Book();

    public BookResourceImpl() {
    }

    @Override
    public List<Book> listAll() {
        ArrayList<Book> books = new ArrayList<>();

        Connection cn = DbAccess.
                getDbAccess()
                .getConnection("SQL")
                .getConnection();
        try {
            ResultSet rs = DbAccess
                    .getDbAccess()
                    .getSelect(cn,
                            " SELECT idBook,title,author FROM [gbh].[dbo].[book] ");

            while (rs.next()) {
                book.setIdBook(rs.getInt("idBook"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                books.add(book);
            }
        } catch (SQLException e) {

            return null;
        }
        return books;
    }

    @Override
    public Book findById(Integer idBook) {
        try {
            ResultSet rs = DbAccess
                    .getDbAccess()
                    .getSelect(DbAccess.
                                    getDbAccess()
                                    .getConnection("SQL")
                                    .getConnection(),
                            String.format(" SELECT [idBook]\n" +
                                    "      ,[title]\n" +
                                    "      ,[author]\n" +
                                    "  FROM [dbo].[book] WHERE  [idBook] = %s", idBook));

            while (rs.next()) {
                book.setIdBook(rs.getInt("idBook"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
            }
            return book;
        } catch (SQLException e) {

            return null;
        }

    }

    @Override
    public Page findpagebyidbook(Integer idBook, Integer pageNumber) {
        Page page = new Page();
        try {
            ResultSet rs = DbAccess
                    .getDbAccess()
                    .getSelect(DbAccess.
                                    getDbAccess()
                                    .getConnection("SQL")
                                    .getConnection(),
                            String.format("SELECT [idPage]\n" +
                                            "      ,[idBook]\n" +
                                            "      ,[bodyContent]\n" +
                                            "      ,[pageNumber]\n" +
                                            "  FROM [dbo].[page] WHERE  [idBook] = %s AND [pageNumber] = %s",
                                    idBook,
                                    pageNumber));


            while (rs.next()) {
                page.setIdPage(rs.getInt("idPage"));
                page.setIdBook(rs.getInt("idBook"));
                page.setBodyContent(rs.getString("bodyContent"));
                page.setPageNumber(rs.getInt("pageNumber"));
            }
            return page;
        } catch (SQLException e) {
            return null;
        }

    }
}
