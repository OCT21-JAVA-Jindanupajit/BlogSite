package jbc.oct21.jindanupajit.blogapplication.repository;

import jbc.oct21.jindanupajit.blogapplication.model.Category;
import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<Category, Long> {
}
