package com.project.translator.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface TagRepository extends JpaRepository<TagEntity, Long> {
}
