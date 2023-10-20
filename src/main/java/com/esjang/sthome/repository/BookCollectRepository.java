package com.esjang.sthome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esjang.sthome.domain.BookCollect;

@Repository
public interface BookCollectRepository extends JpaRepository<BookCollect, Integer> {

}
