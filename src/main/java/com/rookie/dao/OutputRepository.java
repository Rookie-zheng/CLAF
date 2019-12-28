package com.rookie.dao;

import com.rookie.po.Output;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OutputRepository extends JpaRepository<Output, Long> {
    @Transactional
    @Modifying
    @Query("delete from Output where id = ?1")
    void delete(Long id);

    Output findByRemark(String remark);

    @Query("select o from Output o where id = ?1")
    Output findOne(Long id);

    @Query("select sum(o.price) from Output o")
    Float sumOutputPrice();

    @Query("select sum(i.price) from Input i")
    Float sumInputPrice();
}
