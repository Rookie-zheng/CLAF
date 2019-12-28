package com.rookie.util;

import com.rookie.dao.InputRepository;
import com.rookie.dao.OutputRepository;
import com.rookie.po.Input;
import com.rookie.po.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RemainingSum {
    @Autowired
    private InputRepository inputRepository;
    @Autowired
    private OutputRepository outputRepository;

    public BigDecimal remainingSumAll(){
        float inputSum = inputRepository.sumInputPrice();
        float outputSum = outputRepository.sumOutputPrice();
        String res = String.format("%.3f", inputSum - outputSum);
        Float f = Float.parseFloat(res);
        BigDecimal b = new BigDecimal(f);
        return b;
    }
}
