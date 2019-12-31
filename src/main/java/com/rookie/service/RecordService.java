package com.rookie.service;

import com.rookie.po.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface RecordService {

    public Page<Record> listRecord(Pageable pageable);

    public List<Record> listRecord();

    void deleteRecord(Long id);

    Map<String,List<Record>> archiveRecord();

    Long countRecord();
}
