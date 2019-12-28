package com.rookie.dao;

import com.rookie.po.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query("select sum(o.price) from Output o")
    Float sumOutputPrice();

    @Query("select sum(i.price) from Input i")
    Float sumInputPrice();

    @Transactional
    @Modifying
    @Query("delete from Record where id = ?1")
    void delete(Long id);

    @Query("select function('date_format',b.createTime,'%Y') as year from Record b group by function('date_format',b.createTime,'%Y') order by year desc ")
    List<String> findGroupYear();
    @Query("select b from Record b where function('date_format',b.createTime,'%Y') = ?1")
    List<Record> findByYear(String year);
}
