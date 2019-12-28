package com.rookie.service.impl;

import com.rookie.dao.InputRepository;
import com.rookie.dao.RecordRepository;
import com.rookie.po.Input;
import com.rookie.po.Record;
import com.rookie.service.InputService;
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
import java.util.Optional;

@Service
public class InputServiceImpl implements InputService {

    @Autowired
    private InputRepository inputRepository;
    @Autowired
    private RecordRepository recordRepository;

    @Transactional
    @Override
    public Input saveInput(Input input) {
        Record record = new Record();
        record.setCreateTime(new Date());
        record.setInputSum(input.getPrice());
        record.setInputTime(input.getCreateTime());
        record.setSum(inputRepository.sumInputPrice() + input.getPrice() - inputRepository.sumOutputPrice());
        recordRepository.save(record);
        return inputRepository.save(input);
    }
    @Transactional
    @Override
    public Input getInput(Long id) {
        return inputRepository.getOne(id);
    }
    @Transactional
    @Override
    public Page<Input> listInput(Pageable pageable) {
        return inputRepository.findAll(pageable);
    }

    @Override
    public List<Input> listInput() {
        return inputRepository.findAll();
    }

    @Override
    public void deleteType(Long id) {
        inputRepository.delete(id);
    }

    @Override
    public Input getTypeByRemark(String remark) {
        return inputRepository.findByRemark(remark);
    }

    @Override
    public Input updateType(Long id, Input input) {
        Input t = inputRepository.findOne(id);
        if(t == null){
            try {
                throw new NotFoundException("不存在该类型");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        BeanUtils.copyProperties(input, t);
        Record record = new Record();
        record.setCreateTime(new Date());
        record.setInputSum(input.getPrice());
        record.setInputTime(t.getCreateTime());
        record.setSum(inputRepository.sumInputPrice() + input.getPrice() - inputRepository.sumOutputPrice());
        recordRepository.save(record);
        return inputRepository.save(t);
    }

    @Override
    public Input updateInput(Long id, Input input) {
        Input t = inputRepository.findOne(id);
        if(t == null){
            try {
                throw new NotFoundException("不存在该类型");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        BeanUtils.copyProperties(input, t);
        Record record = new Record();
        record.setCreateTime(new Date());
        record.setInputSum(inputRepository.sumInputPrice());
        record.setOutputSum(inputRepository.sumOutputPrice());
        record.setInputTime(t.getCreateTime());
        record.setSum(inputRepository.sumInputPrice() - inputRepository.sumOutputPrice());
        recordRepository.save(record);
        return inputRepository.save(t);
    }

    @Override
    public Float remainInputSum() {
        return inputRepository.sumInputPrice();
    }
}
