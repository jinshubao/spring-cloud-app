package com.jean.auto.repository;

import com.jean.auto.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiRepository extends BaseRepository<Api, Long> {

    List<Api> findByModuleId(Long id);

}
