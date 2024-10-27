package Amine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = "http://localhost:4200")
public class BlogRestController {
    @Autowired
    private IBlogService iBlogService;


    @PostMapping("/addBlog")
    public Blog AddBlog(@RequestBody Blog blog){
        return iBlogService.addOnlyBlog(blog);
    }

    @GetMapping("/getAllUnapprovedBlogs")
    public List<Blog> showAllUnapprovedBlogs(){
        return iBlogService.getAllUnaprovedBlogs();
    } @GetMapping("/getAllBlogs")
    public List<Blog> showAllBlogs(){
        return iBlogService.getAllBlogs();
    }
    @GetMapping("/getAprovedBlogs")
    public List<Blog> showAllAprovedBlogs(){
        return iBlogService.getAllAprovedBlogs();
    }
    @PutMapping("/approveBlog/{id}")
    public Blog approveBlog(@PathVariable("id") String id){
        return iBlogService.ApproveBlog(id);
    }
    @PutMapping("/approveAll")
    public List<Blog> approveAllBlogs(){
        return iBlogService.ApproveAllBlogs();
    }
    @GetMapping("/getDetailsBlog/{id}")
    public Blog detailsBlog(@PathVariable("id") String id){
        return iBlogService.detailsBlog(id);
    }
    @PutMapping("/modifierBlog/{id}")
    public Blog modifierBlog(@RequestBody Blog blog, @PathVariable ("id") String id){
        return iBlogService.modifierBlog(blog,id);
    }
    @DeleteMapping("deleteBlog/{id}")
    public String deleteBlog(@PathVariable ("id") String id){
        iBlogService.deleteBlog(id);
        return "Blog Deleted";
    }
    //upload image
    @PostMapping("/upload/{id}")
    public Blog handleFileUpload(@RequestParam("photo") MultipartFile file,@PathVariable("id") String blogCode) {

        return iBlogService.storeFile(file,blogCode);
    }
    //affichage image
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName) {
        Resource resource = iBlogService.loadFileAsResource(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}

