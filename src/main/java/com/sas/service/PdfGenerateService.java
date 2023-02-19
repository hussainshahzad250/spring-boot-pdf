package com.sas.service;

import com.lowagie.text.DocumentException;
import com.sas.converter.CustomerAssembler;
import com.sas.converter.ItemAssembler;
import com.sas.utils.PdfUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PdfGenerateService {

    @Value("${pdf.directory}")
    private String pdfPath;

    @Autowired
    private PdfUtils pdfUtils;

    @Autowired
    private ItemAssembler itemAssembler;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private CustomerAssembler customerAssembler;


    public ByteArrayInputStream generatePdf() throws IOException {
        return pdfUtils.generatePdf("receipt", getReceiptData());
    }

    private Map<String, Object> getReceiptData() {
        Map<String, Object> data = new HashMap<>();
        data.put("customer", customerAssembler.getCustomer());
        data.put("items", itemAssembler.getItems());
        return data;
    }

    public void quotationData(HttpServletResponse response) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("customer", customerAssembler.getCustomer());
        data.put("items", itemAssembler.getItems());
//        return generatePdfFile("quotation", data, "quotation.pdf");
        ByteArrayInputStream receipt = pdfUtils.generatePdf("quotation", data);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=quotation.pdf");
        IOUtils.copy(receipt, response.getOutputStream());
    }

    public String generatePdfFile(String templateName, Map<String, Object> data, String pdfFileName) throws IOException {
        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine.process(templateName, context);
        String path = pdfPath + pdfFileName;
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(fileOutputStream, false);
            renderer.finishPDF();
            return path;
        } catch (DocumentException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}