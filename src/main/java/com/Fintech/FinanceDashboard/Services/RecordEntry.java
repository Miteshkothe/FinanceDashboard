package com.Fintech.FinanceDashboard.Services;

import com.Fintech.FinanceDashboard.Entity.Records;
import com.Fintech.FinanceDashboard.Repo.RecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecordEntry {
    @Autowired
    private RecordRepo recordRepo;
    public void savenewrecord(Records records){
        records.setDate(LocalDateTime.now());
        recordRepo.save(records);
    }public void saverecord(Records records){
        recordRepo.save(records);
    }
    public List<Records> getAll(){
        List<Records> list=recordRepo.findAll();
        return list;
    }public Records findById(Long id){
        return recordRepo.findById(id).orElseThrow();
    }public void deleteRecord(Records records){
        recordRepo.delete(records);
    }public Long getIncome(){
        return recordRepo.getTotalIncome();
    }public Long getExpence(){
        return recordRepo.getTotalExpense();
    }public List<Object[]> getCategoryWiseTotal(){
        return recordRepo.getCategorySummary();
    }public List<Object[]> getRecentActivity(){
        return recordRepo.recentActivites();
    }public List<Object[]> getMonthlyTrend(){
        return recordRepo.monthlyAnalysis();
    }

}
