package com.service.aichi.repository.interfaces;

import com.service.aichi.entity.Customer;
import com.service.aichi.entity.RecycleBin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecycleBinRepository extends JpaRepository<RecycleBin, Customer> {

}
