package vn.codegym.module6_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.module6_exam.entity.Guest;

public interface GuestRepository extends JpaRepository<Guest, Integer> {
}
