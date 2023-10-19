package com.ordermicroservice.repository;


import com.ordermicroservice.model.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<MyOrder,Long> {
}
