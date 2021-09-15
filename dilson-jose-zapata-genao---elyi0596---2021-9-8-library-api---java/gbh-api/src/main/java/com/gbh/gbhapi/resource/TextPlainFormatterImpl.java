package com.gbh.gbhapi.resource;

import com.gbh.gbhapi.model.IFormatterPage;
import com.gbh.gbhapi.model.Page;


public class TextPlainFormatterImpl implements IFormatterPage {
    @Override
    public String getFormatPage(Page p) {
        StringBuilder output = new StringBuilder();
        if (p != null && !p.getBodyContent().isEmpty()) {
            output.append("\nPage id ").append((p.getIdPage())).append("\r\n\r\n");
            output.append("\nPage number ").append((p.getPageNumber())).append("\r\n\r\n");
            output.append(p.getBodyContent());
            output.append("\r\n\r\n\r\n");
        }
        return output.toString();
    }
}
