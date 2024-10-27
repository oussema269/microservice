package Amine;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Service

public class BlogService implements IBlogService {
    @Value("${file.upload-dir}")
    private String uploadDir;
    @Autowired
    BlogRepository blogRepository;
    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public List<Blog> getAllAprovedBlogs() {
        Boolean status = true;
        List<Blog> approvedBlog = blogRepository.findBlogByStatusIs(status);
        return approvedBlog;
    }

    @Override
    public List<Blog> getAllUnaprovedBlogs() {
        Boolean status = false;
        List<Blog> approvedBlog = blogRepository.findBlogByStatusIs(status);
        return approvedBlog;
    }

    @Override
    public Blog modifierBlog(Blog blog, String id) {
        Blog newblog = blogRepository.findBlogByBlogCode(id);
        newblog.setTitreBlog(blog.getTitreBlog());
        newblog.setDateBlog(LocalDate.now());
        newblog.setPhoto(blog.getPhoto());
        newblog.setDomaine(blog.getDomaine());
        return blogRepository.save(newblog);
    }

    @Override
    public void deleteBlog(String id) {

        blogRepository.deleteById(id);
    }

    @Override
    public Blog detailsBlog(String id) {
        return blogRepository.findBlogByBlogCode(id);
    }

    @Override
    public Blog addOnlyBlog(Blog blog) {
        Calendar cal = Calendar.getInstance();
        blog.setDateBlog(LocalDate.now());
        blog.setStatus(false);

        //sendAddedBlogEmail(user2);
        return blogRepository.save(blog);
    }

    @Override
    public Blog ApproveBlog(String id) {
        Blog blogToApprove = blogRepository.findBlogByBlogCode(id);

        blogToApprove.setStatus(true);
        //sendApprovedEmail(user);
        return blogRepository.save(blogToApprove);
    }

    @Override
    public List<Blog> ApproveAllBlogs() {
        List<Blog> blogsToApprove = blogRepository.findAll();

        for (Blog blog : blogsToApprove) {
            if (!blog.getStatus()) {
                blog.setStatus(true);
                blogRepository.save(blog);
                //sendApprovedEmail(user);
            }
        }

        return blogsToApprove;
    }


    @Override
    public Blog storeFile(MultipartFile file, String blogCode) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String newFileName = generateNewFileName(originalFileName);

        Path uploadPath = Paths.get(uploadDir);

        try {
            if (Files.notExists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(newFileName);
            Files.copy(file.getInputStream(), filePath);

            Blog blog = blogRepository.findBlogByBlogCode(blogCode);
            blog.setPhoto(newFileName);
            return blogRepository.save(blog);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + newFileName, e);
        }
    }
@Override
public String generateNewFileName(String originalFileName) {
        // You can customize this method to generate a unique file name.
        // For example, appending a timestamp or using a UUID.
        String timestamp = String.valueOf(System.currentTimeMillis());
        return timestamp + "_" + originalFileName;
    }


    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found: " + fileName, e);
        }
    }
}

