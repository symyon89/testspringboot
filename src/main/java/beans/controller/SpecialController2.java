package beans.controller;

import beans.controller.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("special2")
public class SpecialController2 {

    @GetMapping("/fileDownload")
    public ResponseEntity<byte[]> fileDownload() throws IOException {
        byte[] bytes = Files.readAllBytes(Path.of("src", "main", "resources", "xxx.pdf"));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=file.pdf");
        return ResponseEntity.ok().headers(headers).contentLength(bytes.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(bytes);
    }

    @Autowired
    TaxService taxService;

    @GetMapping("/calculateTax")
    public String calculateTax() {
        return taxService.calculateTax();
    }
}
