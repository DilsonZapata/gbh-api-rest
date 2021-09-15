package com.gbh.gbhapi.api;

import com.gbh.gbhapi.model.Book;
import com.gbh.gbhapi.model.IBookResource;
import com.gbh.gbhapi.model.IFormatterPage;
import com.gbh.gbhapi.model.Page;
import com.gbh.gbhapi.resource.DbAccess;
import com.gbh.gbhapi.resource.Formatter;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Path("/book")
public class BookApi {

    @Inject
    private IBookResource service;


    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response getBook() {
        List<Book> books = service.listAll();
        if (books != null) {
            return Response.ok().entity(books).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found ").build();
        }

    }


    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response getBookById(@PathParam("id") Integer id) {
        Book book = service.findById(id);
        if (book != null) {
            return Response.ok().entity(book).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for UUID: " + id).build();
        }
    }


    @Path("/{id}/page/{idPage}/{formatter}")
    @GET
    @Produces({MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
    public Response getBookPage(@PathParam("id") Integer id, @PathParam("idPage") Integer idPage, @PathParam("formatter") String formatter) {

        Page page = service.findpagebyidbook(id, idPage);
        if (page.getIdPage() > 0) {
            return Response.ok().entity(new Formatter().getFormatter(formatter).getFormatPage(page)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for UUID: " + id).build();
        }
    }


}