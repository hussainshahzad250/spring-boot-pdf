package com.sas.controller;

import com.sas.service.PdfGenerateService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class PdfGenerateController {

    private final PdfGenerateService pdfGenerateService;

    @GetMapping("/downloadReceipt")
    public void downloadReceipt(HttpServletResponse response) throws IOException {
        ByteArrayInputStream exportedData = pdfGenerateService.generatePdf();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=receipt.pdf");
        IOUtils.copy(exportedData, response.getOutputStream());
    }

    @GetMapping("/downloadQuotation")
    public void downloadQuotation(HttpServletResponse response) throws IOException {
        pdfGenerateService.quotationData(response);
    }
}
