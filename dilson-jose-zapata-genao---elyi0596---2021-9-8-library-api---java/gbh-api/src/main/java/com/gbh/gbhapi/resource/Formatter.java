package com.gbh.gbhapi.resource;

import com.gbh.gbhapi.model.IFormatterPage;
import com.gbh.gbhapi.model.Page;

import java.util.List;

public class Formatter {


    public IFormatterPage getFormatter(String KeyFormatter) {
        switch (KeyFormatter) {
            case "html":
                return new HtmlFormatterImpl();
            case "text":
                return new TextPlainFormatterImpl();
            default:
                return null;
        }

    }


}
