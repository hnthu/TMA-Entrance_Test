package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.TableService;

@RestController
@PreAuthorize("hasAnyRole(\"ROLE_USER\",\"ROLE_ADMIN\")")
public class TableController {
    private TableService tableService;

    @Autowired
    public void setQTableService(TableService tableService) {
        this.tableService = tableService;
    }

    @RequestMapping(value ="/deleteAllRecords", method = RequestMethod.POST)
    public ResponseEntity<?> deleteQuestion(@RequestParam("tableName") String tableName, @RequestParam("selectedIds") int[] selectedIds){
        this.tableService.deleteAllRecord(tableName, selectedIds);
        return new ResponseEntity("Deleted Successfully", new HttpHeaders(), HttpStatus.OK);
    }
}
