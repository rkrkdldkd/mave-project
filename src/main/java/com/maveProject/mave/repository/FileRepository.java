package com.maveProject.mave.repository;

import com.maveProject.mave.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
