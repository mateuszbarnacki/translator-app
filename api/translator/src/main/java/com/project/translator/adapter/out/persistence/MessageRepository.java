package com.project.translator.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
