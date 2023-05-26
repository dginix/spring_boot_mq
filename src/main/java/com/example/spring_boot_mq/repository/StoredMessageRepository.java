package com.example.spring_boot_mq.repository;

import com.example.spring_boot_mq.model.StoredMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoredMessageRepository extends JpaRepository<StoredMessage, Long> {

}
