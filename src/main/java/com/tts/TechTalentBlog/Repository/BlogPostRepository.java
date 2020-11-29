package com.tts.TechTalentBlog.Repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tts.TechTalentBlog.Domain.BlogPost;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long>{
	List<BlogPost> findByTitle(String title);
	List<BlogPost> findByAuthor(String author);
	List<BlogPost> findByBlogEntry(String blogEntry);
//	List<BlogPost> findByBlogEntry(String blogEntry);

}