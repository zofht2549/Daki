package com.daki.db.repository;

import com.daki.db.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
