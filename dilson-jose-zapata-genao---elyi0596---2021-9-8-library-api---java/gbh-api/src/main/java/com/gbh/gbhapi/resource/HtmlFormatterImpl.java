package com.gbh.gbhapi.resource;

import com.gbh.gbhapi.model.IFormatterPage;
import com.gbh.gbhapi.model.Page;

import java.util.List;

public class HtmlFormatterImpl implements IFormatterPage {
    @Override
    public String getFormatPage(Page p) {
        StringBuilder output = new StringBuilder();

        if (p != null && !p.getBodyContent().isEmpty()) {
            output.append("<html><header><title>p.getIdPage()</title></head>");
            output.append("<body>");
            output.append("<b>Page number ").append(p.getPageNumber()).append("</b><br />");
            output.append("<p>").append(p.getBodyContent()).append("</p>");
            output.append("<br >");
        }
        return output.toString();
    }
}
