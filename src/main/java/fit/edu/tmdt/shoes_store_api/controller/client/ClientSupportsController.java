package fit.edu.tmdt.shoes_store_api.controller.client;

import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDTO;
import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDataType;
import fit.edu.tmdt.shoes_store_api.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static fit.edu.tmdt.shoes_store_api.dto.Support.SupportDataType.GENDER;

@RestController
@RequestMapping("${api.prefix}")
public class ClientSupportsController {
    @Autowired
    SupportService supportService;

    @GetMapping(value = "/client-api/gender", produces = "application/json")
    public ResponseEntity<List<SupportDTO>> getGender() {
        try {
            List<SupportDTO> data = supportService.getSupportData(GENDER);
            return ResponseEntity.ok(data);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
