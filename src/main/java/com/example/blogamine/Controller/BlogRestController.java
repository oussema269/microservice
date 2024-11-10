package com.example.blogamine.Controller;

import com.example.blogamine.Entite.*;
import com.example.blogamine.Entite.BlogDTO;
import com.example.blogamine.Service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogRestController {

    @Autowired
    private IBlogService iBlogService;

    // Add a new blog
    @PostMapping("/addBlog")
    public Blog addBlog(@RequestBody BlogDTO blogDTO) {
        return iBlogService.addOnlyBlog(blogDTO);
    }

    // Get all unapproved blogs
    @GetMapping("/getAllUnapprovedBlogs")
    public List<BlogDTO> showAllUnapprovedBlogs() {
        return iBlogService.getAllUnaprovedBlogs();
    }

    // Get all blogs
    @GetMapping("/getAllBlogs")
    public List<BlogDTO> showAllBlogs() {
        return iBlogService.getAllBlogs();
    }

    // Get all approved blogs
    @GetMapping("/getApprovedBlogs")
    public List<BlogDTO> showAllApprovedBlogs() {
        return iBlogService.getAllAprovedBlogs();
    }

    // Approve a blog by its ID
    @PutMapping("/approveBlog/{id}")
    public Blog approveBlog(@PathVariable("id") String id) {
        return iBlogService.ApproveBlog(id);
    }

    // Approve all unapproved blogs
    @PutMapping("/approveAll")
    public List<BlogDTO> approveAllBlogs() {
        return iBlogService.ApproveAllBlogs();
    }

    // Get blog details by ID
    @GetMapping("/getDetailsBlog/{id}")
    public Blog getBlogDetails(@PathVariable("id") String id) {
        return iBlogService.detailsBlog(id);
    }

    // Modify an existing blog by ID
    @PutMapping("/modifyBlog/{id}")
    public Blog modifyBlog(@RequestBody BlogDTO blogDTO, @PathVariable("id") String id) {
        return iBlogService.modifierBlog(blogDTO, id);
    }

    // Delete a blog by ID
    @DeleteMapping("/deleteBlog/{id}")
    public String deleteBlog(@PathVariable("id") String id) {
        iBlogService.deleteBlog(id);
        return "Blog Deleted";
    }

    // Upload image for the blog
    @PostMapping("/upload/{id}")
    public Blog handleFileUpload(@RequestParam("photo") MultipartFile file, @PathVariable("id") String blogCode) {
        return iBlogService.storeFile(file, blogCode);
    }

    // Download image for the blog
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName) {
        Resource resource = iBlogService.loadFileAsResource(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
