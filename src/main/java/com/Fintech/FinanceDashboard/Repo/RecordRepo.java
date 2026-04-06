package com.Fintech.FinanceDashboard.Repo;

import com.Fintech.FinanceDashboard.Entity.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepo extends JpaRepository<Records,Long> {
    @Query("SELECT SUM(r.amount) FROM Records r WHERE r.type = 'INCOME'")
    Long getTotalIncome();
    @Query("SELECT SUM(r.amount) FROM Records r WHERE r.type = 'EXPENSE'")
    Long getTotalExpense();
    @Query("SELECT r.category, SUM(r.amount) FROM Records r GROUP BY r.category")
    List<Object[]> getCategorySummary();
    @Query("SELECT EXTRACT(MONTH FROM r.date), r.type, SUM(r.amount) FROM Records r GROUP BY EXTRACT(MONTH FROM r.date), r.type ORDER BY EXTRACT(MONTH FROM r.date)")
    List<Object[]> monthlyAnalysis();
    @Query(value = "SELECT * FROM records WHERE date >= NOW() - INTERVAL '7 days' ORDER BY date DESC", nativeQuery = true)
    List<Object[]> recentActivites();
}
