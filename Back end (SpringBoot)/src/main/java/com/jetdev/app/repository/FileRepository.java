package com.jetdev.app.repository;

import com.jetdev.app.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

    public File findByName(String name);
}
