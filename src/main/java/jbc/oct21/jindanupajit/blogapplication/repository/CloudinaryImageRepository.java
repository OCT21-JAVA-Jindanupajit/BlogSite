package jbc.oct21.jindanupajit.blogapplication.repository;

import jbc.oct21.jindanupajit.blogapplication.model.CloudinaryImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudinaryImageRepository extends CrudRepository<CloudinaryImage, Long> {
}
