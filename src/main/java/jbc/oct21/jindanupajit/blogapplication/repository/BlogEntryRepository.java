package jbc.oct21.jindanupajit.blogapplication.repository;

import jbc.oct21.jindanupajit.blogapplication.model.BlogEntry;
import org.springframework.data.repository.CrudRepository;

public interface BlogEntryRepository extends CrudRepository<BlogEntry, Long> {
}
