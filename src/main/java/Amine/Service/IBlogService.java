package Amine.Service;


import Amine.Entite.Blog;
import Amine.Entite.BlogDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBlogService {
    List<BlogDTO> getAllBlogs();
    List<BlogDTO> getAllAprovedBlogs();
    List<BlogDTO> getAllUnaprovedBlogs();
    Blog modifierBlog(BlogDTO blog, String id);
    void deleteBlog(String id);
    Blog detailsBlog(String id);
    Blog addOnlyBlog(BlogDTO blog);
    Blog ApproveBlog(String id );
    List<BlogDTO> ApproveAllBlogs();
    Blog storeFile(MultipartFile file, String blogCode);

    String generateNewFileName(String originalFileName);

    Resource loadFileAsResource(String fileName);


}
