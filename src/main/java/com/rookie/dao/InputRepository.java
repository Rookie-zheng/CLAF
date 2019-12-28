package com.rookie.dao;

import com.rookie.po.Input;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface InputRepository extends JpaRepository<Input, Long> {
    @Transactional
    @Modifying
    @Query("delete from Input where id = ?1")
    void delete(Long id);

    Input findByRemark(String remark);

    @Query("select i from Input i where id = ?1")
    Input findOne(Long id);

    @Query("select sum(i.price) from Input i")
    Float sumInputPrice();
    @Query("select sum(o.price) from Output o")
    Float sumOutputPrice();

}
