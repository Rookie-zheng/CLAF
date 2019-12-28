package com.rookie.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
/**
 * @author rookie-zheng
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float inputSum;
    private float outputSum;
    private float sum;
    @Temporal(TemporalType.TIMESTAMP)
    private Date inputTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date outputTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
