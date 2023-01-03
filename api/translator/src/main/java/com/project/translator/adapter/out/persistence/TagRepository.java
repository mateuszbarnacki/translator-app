package com.project.translator.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface TagRepository extends JpaRepository<TagEntity, Long> {
    List<TagEntity> findAll();

    Optional<TagEntity> findByTag(String tag);
}
