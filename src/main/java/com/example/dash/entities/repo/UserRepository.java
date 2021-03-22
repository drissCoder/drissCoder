/**
 *
 */
package com.example.dash.entities.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.dash.entities.User;


/**
 * @author pavan.solapure
 *
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>
{

	Page<User> findAll(Pageable pageable);
}
