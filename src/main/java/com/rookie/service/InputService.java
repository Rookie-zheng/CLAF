package com.rookie.service;

import com.rookie.po.Input;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface InputService {

    Input saveInput(Input input);

    public Input getInput(Long id);

    public Page<Input> listInput(Pageable pageable);

    public List<Input> listInput();

    void deleteType(Long id);

    Input getTypeByRemark(String remark);

    Input updateType(Long id, Input input);

    Input updateInput(Long id, Input input);

    Float remainInputSum();
}
