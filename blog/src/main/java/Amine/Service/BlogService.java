package Amine.Service;


import Amine.Entite.*;
import Amine.Entite.Blog;
import Amine.Repository.BlogRepository;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service

public class BlogService implements IBlogService {
    @Value("${file.upload-dir}")
    private String uploadDir;
    @Autowired
    BlogRepository blogRepository;
    private  UserService userService;
    private  InteractionsService interactionsService;
    @Override
    public List<BlogDTO> getAllBlogs() {

        List<BlogDTO> blogDTOS = new ArrayList<>();
        List<Blog> blogs= blogRepository.findAll();

        // Using a forEach loop to convert each Blog to BlogDTO
        blogs.forEach(blog -> {
            BlogDTO blogDTO = new BlogDTO();

            // Assuming BlogDTO has similar fields to Blog; copy them over
            blogDTO.setBlogCode(blog.getBlogCode());
            blogDTO.setTitreBlog(blog.getTitreBlog());
            blogDTO.setDateBlog(blog.getDateBlog());
            blogDTO.setDomaine(blog.getDomaine());
            blogDTO.setContenu(blog.getContenu());
            blogDTO.setStatus(blog.getStatus());
            blogDTO.setPhoto(blog.getPhoto());
            UserDTO userDTO = userService.getUserDTOById(blog.getIduser());
            blogDTO.setUser(userDTO);
            List<InteractionsDTO> interactionsDTOs = new ArrayList<>();
            blog.getIdinteractions().forEach(interactionId -> {
                InteractionsDTO interactionDTO = interactionsService.getInteractionsDTOById(interactionId);
                interactionsDTOs.add(interactionDTO);
            });
            blogDTO.setInteractions(interactionsDTOs);

            blogDTOS.add(blogDTO);
        });

        return blogDTOS;
    }

    @Override
    public List<BlogDTO> getAllAprovedBlogs() {
        Boolean status = true;

        // Fetch the approved blogs
        List<Blog> approvedBlogs = blogRepository.findBlogByStatusIs(status);

        // Initialize a list to hold the BlogDTO objects
        List<BlogDTO> approvedBlogDTOs = new ArrayList<>();

        // Iterate over the approved blogs and convert them to BlogDTOs
        approvedBlogs.forEach(blog -> {
            BlogDTO blogDTO = new BlogDTO();

            blogDTO.setBlogCode(blog.getBlogCode());
            blogDTO.setTitreBlog(blog.getTitreBlog());
            blogDTO.setDateBlog(blog.getDateBlog());
            blogDTO.setDomaine(blog.getDomaine());
            blogDTO.setContenu(blog.getContenu());
            blogDTO.setStatus(blog.getStatus());
            blogDTO.setPhoto(blog.getPhoto());

            // Map `iduser` to `UserDTO`
            UserDTO userDTO = userService.getUserDTOById(blog.getIduser());
            blogDTO.setUser(userDTO);

            // Map `idinteractions` to a list of `InteractionsDTO`
            List<InteractionsDTO> interactionsDTOs = new ArrayList<>();
            blog.getIdinteractions().forEach(interactionId -> {
                InteractionsDTO interactionDTO = interactionsService.getInteractionsDTOById(interactionId);
                interactionsDTOs.add(interactionDTO);
            });
            blogDTO.setInteractions(interactionsDTOs);

            // Add the converted BlogDTO to the list
            approvedBlogDTOs.add(blogDTO);
        });

        return approvedBlogDTOs; // Return the list of BlogDTOs
    }


    @Override
    public List<BlogDTO> getAllUnaprovedBlogs() {
        Boolean status = false;

        // Fetch the unapproved blogs
        List<Blog> unapprovedBlogs = blogRepository.findBlogByStatusIs(status);

        // Initialize a list to hold the BlogDTO objects
        List<BlogDTO> unapprovedBlogDTOs = new ArrayList<>();

        // Iterate over the unapproved blogs and convert them to BlogDTOs
        unapprovedBlogs.forEach(blog -> {
            BlogDTO blogDTO = new BlogDTO();

            blogDTO.setBlogCode(blog.getBlogCode());
            blogDTO.setTitreBlog(blog.getTitreBlog());
            blogDTO.setDateBlog(blog.getDateBlog());
            blogDTO.setDomaine(blog.getDomaine());
            blogDTO.setContenu(blog.getContenu());
            blogDTO.setStatus(blog.getStatus());
            blogDTO.setPhoto(blog.getPhoto());

            // Map `iduser` to `UserDTO`
            UserDTO userDTO = userService.getUserDTOById(blog.getIduser());
            blogDTO.setUser(userDTO);

            // Map `idinteractions` to a list of `InteractionsDTO`
            List<InteractionsDTO> interactionsDTOs = new ArrayList<>();
            blog.getIdinteractions().forEach(interactionId -> {
                InteractionsDTO interactionDTO = interactionsService.getInteractionsDTOById(interactionId);
                interactionsDTOs.add(interactionDTO);
            });
            blogDTO.setInteractions(interactionsDTOs);

            // Add the converted BlogDTO to the list
            unapprovedBlogDTOs.add(blogDTO);
        });

        return unapprovedBlogDTOs; // Return the list of BlogDTOs
    }


    @Override
    public Blog modifierBlog(BlogDTO blog, String id) {
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
    public Blog addOnlyBlog(BlogDTO blogDTO) {
        // Create a new Blog entity from BlogDTO
        Blog blog = new Blog();

        // Set the properties from BlogDTO to Blog
        blog.setBlogCode(blogDTO.getBlogCode());
        blog.setTitreBlog(blogDTO.getTitreBlog());
        blog.setDateBlog(LocalDate.now()); // Set current date as dateBlog
        blog.setDomaine(blogDTO.getDomaine());
        blog.setContenu(blogDTO.getContenu());
        blog.setStatus(false); // Set status as false for unapproved blog
        blog.setPhoto(blogDTO.getPhoto());
        blog.setIduser(blogDTO.getUser().getId()); // Assuming the UserDTO has an `id` field

        // If the blogDTO contains interactions, map them to the Blog entity as well
        List<String> interactions = new ArrayList<>();
        blogDTO.getInteractions().forEach(interactionDTO -> {
            interactions.add(interactionDTO.getId()); // Assuming InteractionsDTO has an `id` field
        });
        blog.setIdinteractions(interactions); // Set the list of interaction IDs

        // Save the Blog entity to the database
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
    public List<BlogDTO> ApproveAllBlogs() {
        List<Blog> blogsToApprove = blogRepository.findAll(); // Fetch all blogs

        List<BlogDTO> approvedBlogDTOs = new ArrayList<>(); // Initialize a list to hold the BlogDTOs

        for (Blog blog : blogsToApprove) {
            if (!blog.getStatus()) { // If blog is unapproved
                blog.setStatus(true); // Approve the blog
                blogRepository.save(blog); // Save the approved blog

                // Optionally, send approval email
                // sendApprovedEmail(user);

                // Convert the Blog to BlogDTO
                BlogDTO blogDTO = new BlogDTO();
                blogDTO.setBlogCode(blog.getBlogCode());
                blogDTO.setTitreBlog(blog.getTitreBlog());
                blogDTO.setDateBlog(blog.getDateBlog());
                blogDTO.setDomaine(blog.getDomaine());
                blogDTO.setContenu(blog.getContenu());
                blogDTO.setStatus(blog.getStatus());
                blogDTO.setPhoto(blog.getPhoto());

                // Map `iduser` to `UserDTO`
                UserDTO userDTO = userService.getUserDTOById(blog.getIduser());
                blogDTO.setUser(userDTO);

                // Map `idinteractions` to a list of `InteractionsDTO`
                List<InteractionsDTO> interactionsDTOs = new ArrayList<>();
                blog.getIdinteractions().forEach(interactionId -> {
                    InteractionsDTO interactionDTO = interactionsService.getInteractionsDTOById(interactionId);
                    interactionsDTOs.add(interactionDTO);
                });
                blogDTO.setInteractions(interactionsDTOs);

                // Add the converted BlogDTO to the list
                approvedBlogDTOs.add(blogDTO);
            }
        }

        return approvedBlogDTOs; // Return the list of approved BlogDTOs
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

