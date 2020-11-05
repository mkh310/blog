package cn.mkh.blog;

import cn.mkh.blog.web.dao.BlogDao;
import cn.mkh.blog.web.domain.Blog;
import cn.mkh.blog.web.service.BlogAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class BlogApplicationTests {


    @Autowired
    BlogDao dao;
    @Autowired
    BlogAdminService blogAdminService;

    @Test
    void contextLoads() {
        Map<String, List<Blog>> blogsArchives = blogAdminService.findBlogsArchives();
        for (String s : blogsArchives.keySet()) {
            List<Blog> blogs = blogsArchives.get(s);
            System.out.println(s+"-----------------------");
            for (Blog blog : blogs) {
                System.out.println(blog);
            }
        }
    }

}
