package com.rookie.service.impl;

import com.rookie.dao.OutputRepository;
import com.rookie.dao.RecordRepository;
import com.rookie.po.Output;
import com.rookie.po.Record;
import com.rookie.service.OutputService;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OutputServiceImpl implements OutputService {

    @Autowired
    private OutputRepository outputRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Transactional
    @Override
    public Output saveOutput(Output output) {
        Record record = new Record();
        record.setCreateTime(new Date());
        record.setOutputSum(output.getPrice());
        record.setOutputTime(output.getCreateTime());
        record.setSum(outputRepository.sumInputPrice() - outputRepository.sumOutputPrice() - output.getPrice());
        recordRepository.save(record);
        return outputRepository.save(output);
    }
    @Transactional
    @Override
    public Output getOutput(Long id) {
        return outputRepository.getOne(id);
    }
    @Transactional
    @Override
    public Page<Output> listOutput(Pageable pageable) {
        return outputRepository.findAll(pageable);
    }

    @Override
    public List<Output> listOutput() {
        return outputRepository.findAll();
    }

    @Override
    public void deleteOutput(Long id) {
        outputRepository.delete(id);
    }

    @Override
    public Output getTypeByRemark(String remark) {
        return outputRepository.findByRemark(remark);
    }



    @Override
    public Output updateOutput(Long id, Output output) {
        Output t = outputRepository.findOne(id);
        if(t == null){
            try {
                throw new NotFoundException("不存在该类型");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        BeanUtils.copyProperties(output, t);
        Record record = new Record();
        record.setCreateTime(new Date());
        record.setOutputSum(t.getPrice());
        record.setOutputSum(outputRepository.sumOutputPrice());
        record.setOutputTime(t.getCreateTime());
        record.setSum(outputRepository.sumInputPrice() - outputRepository.sumOutputPrice() - output.getPrice());
        recordRepository.save(record);
        return outputRepository.save(t);
    }

    @Override
    public Float remainOutputSum() {
        return outputRepository.sumOutputPrice();
    }

    @Override
    public Float remainAllSum() {
        return outputRepository.sumInputPrice() - outputRepository.sumOutputPrice();
    }


}
