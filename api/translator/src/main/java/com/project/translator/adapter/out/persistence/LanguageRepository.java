package com.project.translator.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {
}
