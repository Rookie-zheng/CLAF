package com.rookie.service.impl;

import com.rookie.dao.RecordRepository;
import com.rookie.po.Record;
import com.rookie.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public Page<Record> listRecord(Pageable pageable) {
        return recordRepository.findAll(pageable);
    }

    @Override
    public List<Record> listRecord() {
        return recordRepository.findAll();
    }

    @Override
    public void deleteRecord(Long id) {
        recordRepository.delete(id);
    }

    @Override
    public Map<String, List<Record>> archiveBlog() {
        List<String> years = recordRepository.findGroupYear();
        Map<String, List<Record>> map = new HashMap<>();
        for (String year : years){
            map.put(year, recordRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countRecord() {
        return recordRepository.count();
    }
}
