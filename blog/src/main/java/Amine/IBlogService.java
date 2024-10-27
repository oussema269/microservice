package Amine;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBlogService {
    List<Blog> getAllBlogs();
    List<Blog> getAllAprovedBlogs();
    List<Blog> getAllUnaprovedBlogs();
    Blog modifierBlog(Blog blog, String id);
    void deleteBlog(String id);
    Blog detailsBlog(String id);
    Blog addOnlyBlog(Blog blog);
    Blog ApproveBlog(String id );
    List<Blog> ApproveAllBlogs();
    Blog storeFile(MultipartFile file, String blogCode);

    String generateNewFileName(String originalFileName);

    Resource loadFileAsResource(String fileName);


}
