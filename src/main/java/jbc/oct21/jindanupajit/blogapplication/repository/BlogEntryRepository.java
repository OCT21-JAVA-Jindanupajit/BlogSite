package jbc.oct21.jindanupajit.blogapplication.repository;

import jbc.oct21.jindanupajit.blogapplication.model.BlogEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogEntryRepository extends CrudRepository<BlogEntry, Long> {
        List<BlogEntry> findAllByOrderByTimestampDesc();
        List<BlogEntry> findAllByCategoryIdOrderByTimestampDesc(long categoryId);
}
