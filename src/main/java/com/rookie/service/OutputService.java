package com.rookie.service;

import com.rookie.po.Input;
import com.rookie.po.Output;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface OutputService {

    Output saveOutput(Output output);

    public Output getOutput(Long id);

    public Page<Output> listOutput(Pageable pageable);

    public List<Output> listOutput();

    void deleteOutput(Long id);

    Output getTypeByRemark(String remark);


    Output updateOutput(Long id, Output output);

    Float remainOutputSum();

    Float remainAllSum();
}
