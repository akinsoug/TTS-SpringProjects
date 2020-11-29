package com.tts.TechTalentBlog.Controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tts.TechTalentBlog.Domain.BlogPost;
import com.tts.TechTalentBlog.Repository.BlogPostRepository;

@Controller
public class BlogPostController {

	@Autowired
	private BlogPostRepository blogPostRepository;
	private static List<BlogPost> posts = new ArrayList<>();
	
	private BlogPost blogPost;
	
	/**
	 * 
	 * @param blogPost
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/")
	public String index(BlogPost blogPost, Model model) {
		model.addAttribute("posts", posts);
		 posts.removeAll(posts);
		 blogPost.setCount(0);
		    for (BlogPost post : blogPostRepository.findAll()) {
		    	posts.add(post);
		    	blogPost.setCount();
		    }
		    model.addAttribute("posts", posts);
			model.addAttribute("BlogPostCount", blogPost.getCount());
			System.out.println("\n\t BlogPostCount \t\t\t " + blogPost.getCount());
		return "blogpost/indexblogpost";
	}
	
	
	/**
	 * 
	 * @param blogPost
	 * @return
	 */
	@GetMapping(value = "/blogposts/new")
	public String newBlog (BlogPost blogPost) {
	    return "blogpost/new";
	}
	
	@PostMapping(value = "/blogposts")
	public String addNewBlogPost(BlogPost blogPost, Model model/*, Long id*/) {
	    blogPostRepository.save(blogPost);
	    posts.add(blogPost);
//	    model.addAttribute("id", blogPost.getId());
	    model.addAttribute("title", blogPost.getTitle());
	    model.addAttribute("author", blogPost.getAuthor());
	    model.addAttribute("blogEntry", blogPost.getBlogEntry());
	    model.addAttribute("id", blogPost.getId());
	    return "blogpost/resultblogpost";
	}
	

	/**
	 * Optional<BlogPost>
	 * if (post.isPresent())
	 * post.get()
	 * 
	 * @param id
	 * @param blogPost
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/blogposts/{id}", method = RequestMethod.GET)
    public String editPostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
        Optional<BlogPost> post = blogPostRepository.findById(id);
        if (post.isPresent()) {
            BlogPost actualPost = post.get();
            model.addAttribute("blogPost", actualPost);
        }
        return "blogpost/edit";
    }
	
	@RequestMapping(value = "/blogposts/update/{id}")
    public String updateExistingPost(@PathVariable Long id, BlogPost blogPost, Model model) {
        Optional<BlogPost> post = blogPostRepository.findById(id);
        if (post.isPresent()) {
            BlogPost actualPost = post.get();
            actualPost.setTitle(blogPost.getTitle());
            actualPost.setAuthor(blogPost.getAuthor());
            actualPost.setBlogEntry(blogPost.getBlogEntry());
            blogPostRepository.save(actualPost);
            model.addAttribute("blogPost", actualPost);
        }
        return "redirect:/";
    }
	

	
	/**
	 * 
	 * @param id
	 * @param blogPost
	 * @return
	 */
	 @RequestMapping(value = "/blogposts/delete/{id}", method = RequestMethod.POST)
	    public String deletePostWithIdNick(@PathVariable Long id, BlogPost blogPost) {
	    		blogPostRepository.deleteById(id);
	        return "redirect:/";
	    }
	
//	@RequestMapping(value = "/blogposts/{id}", method = RequestMethod.DELETE)
//	public String deletePostWithId(@PathVariable Long id, BlogPost blogPost) {
//		if (blogPostRepository.existsById(id)) {
//			blogPostRepository.deleteById(id);
//		}
//	    return "redirect:/";
//	}
//	
/*
	
	@RequestMapping(value = "/blogposts/{id}", method = RequestMethod.DELETE)
	public String deletePostWithId(@PathVariable("id") Long id, BlogPost blogPost) {
	    blogPostRepository.deleteById(id);
	    return "blogpost/indexblogpost";
	}
*/
	
	
//	@RequestMapping(value = "blogposts/delete/{id}")
//    public String deletePostById(@PathVariable Long id, BlogPost blogPost) {
//        blogPostRepository.deleteById(id);
//        return "blogpost/delete";
//    }

	@GetMapping(value = "/blogposts/indexblogpost")
	public String indexxxxxxxxxx(BlogPost blogPost, Model model) {
		model.addAttribute("posts", posts);
		return "blogpost/indexblogpost";
	}
	
//	@RequestMapping(value = "/blogposts/{id}")
//	public String deletePostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
//	    model.addAttribute("id", blogPostRepository.findById(id));
//	    blogPostRepository.deleteById(id);
//	    return "redirect:/";
//	}
	
	
	
//	/**
//	 * 
//	 * @param blogPost
//	 * @param model
//	 * @return
//	 */
//	@PostMapping(value = "/blogposts")
//	public String addNewBlogPost(BlogPost blogPost, Model model) {
//	    blogPostRepository.save(blogPost);
//	    posts.add(blogPost);
//	    model.addAttribute("title", blogPost.getTitle());
//	    model.addAttribute("author", blogPost.getAuthor());
//	    model.addAttribute("blogEntry", blogPost.getBlogEntry());
//	    return "blogpost/resultblogpost";
//	}
//	

	
//	/**
//	 * deletePostWithId
//	 * @param id
//	 * @param blogPost
//	 * @return
//	 */
//	@RequestMapping(value = "/blogposts/{id}", method = RequestMethod.DELETE)
//	public String deletePostWithId(@PathVariable Long id, BlogPost blogPost) {
//	    blogPostRepository.deleteById(id);
//	    return "blogpost/indexblogpost";
//	}
	
	//------------------------------------------------------------------------------------------------

//	private BlogPost blogPost;
//
	@PostMapping(value = "/")
	public String addNewBlogPostBefore(BlogPost blogPost, Model model) {
		blogPostRepository.save(blogPost);
		posts.add(blogPost);
		model.addAttribute("title", blogPost.getTitle());
		model.addAttribute("author", blogPost.getAuthor());
		model.addAttribute("blogEntry", blogPost.getBlogEntry());
		return "blogpost/resultblogpost";
	}

//	/**
//	 * index
//	 * @param blogPost
//	 * @param model
//	 * @return
//	 */
//	@GetMapping(value = "/blogposts/indexblogpost")
//	public String index(BlogPost blogPost, Model model) {
//		model.addAttribute("posts", blogPost.getTitle());
//		return "blogpost/indexblogpost";
//	}
	
//        @GetMapping(value="/")
//        public String indexBefore(BlogPost blogPost) {
//        	return "blogpost/indexblogpost";
//        }

//        private BlogPost blogPost;
//        
//        @PostMapping(value = "/")
//        public String addNewBlogPost(BlogPost blogPost, Model model) {
//    	blogPostRepository.save(new BlogPost(blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry()));
//    	model.addAttribute("title", blogPost.getTitle());
//    	model.addAttribute("author", blogPost.getAuthor());
//    	model.addAttribute("blogEntry", blogPost.getBlogEntry());
//    	return "blogpost/resultblogpost";
//        }

} // end

//jdbc:h2:file:~/spring-boot-h2-db