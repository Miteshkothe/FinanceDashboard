package com.Fintech.FinanceDashboard.Controller;

import com.Fintech.FinanceDashboard.Entity.Records;
import com.Fintech.FinanceDashboard.Services.RecordEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/record")
@PreAuthorize("hasRole('ADMIN')")
public class RecordController {
    @Autowired
    private RecordEntry recordEntry;
    @PostMapping("/newrecord")
    public ResponseEntity<String> createRecord(@RequestBody Records records){
        try{
            recordEntry.savenewrecord(records);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Records>> getAll(){
        try{
            List<Records> list=recordEntry.getAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<Records> updateRecord(@PathVariable Long id,@RequestBody Records records){
        Records old=recordEntry.findById(id);
        if(old!=null){
            if(records.getAmount()!=null&&!records.getAmount().equals(old.getAmount()))old.setAmount(records.getAmount());
            if(records.getType()!=null&&!records.getType().equals(old.getType()))old.setType(records.getType());
            if(records.getCategory()!=null&&!records.getCategory().equals(old.getCategory()))old.setCategory(records.getCategory());
            if(records.getNote()!=null&&!records.getNote().equals(old.getNote()))old.setNote(records.getNote());
            recordEntry.saverecord(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id){
        Records records =recordEntry.findById(id);
        if(records!=null){
            recordEntry.deleteRecord(records);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }@GetMapping("/getAll/{type}")
    public ResponseEntity<List<Records>> getByType(@PathVariable String type){
        List<Records> list=recordEntry.getAll();
        if(list.size()>0) {
            List<Records> newList=list.stream()
                    .filter(records -> records.getType().equals(type))
                    .toList();
            return new ResponseEntity<>(newList,HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/getAll/{category}")
    public ResponseEntity<List<Records>> getByCategory(@PathVariable String category){
        List<Records> list=recordEntry.getAll();
        if(list.size()>0) {
            List<Records> newList=list.stream()
                    .filter(records -> records.getCategory().equals(category))
                    .toList();
            return new ResponseEntity<>(newList,HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
