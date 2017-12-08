package com.jean.user.repository;

import com.jean.user.api.entity.Api;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRepository extends BaseRepository<Api, Long> {

}
