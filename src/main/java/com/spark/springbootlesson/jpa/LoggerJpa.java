package com.spark.springbootlesson.jpa;

import com.spark.springbootlesson.entity.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author initiald0824
 * @date 2019/7/2 11:36
 */
public interface LoggerJpa extends JpaRepository<LoggerEntity, Long> {
}
