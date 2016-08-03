package controller;

import com.yulei.demo.model.Image;
import com.yulei.demo.model.News;
import com.yulei.demo.model.ViewFinder;
import com.yulei.demo.repository.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.yulei.demo.BaseEntity.UNDELETED;


/**
 * Created by lei.yu on 2016/4/21.
 */
@Controller
public class AdminController {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private ImportantRepository importantRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ViewFinderRepository viewFinderRepository;

    /**
     * 进入系统前台首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/home")
    public String home(Model model) {
        List<News> noticesList = newsRepository.findTopTen(UNDELETED, 30L);
        List<News> importantList = newsRepository.findTopTen(UNDELETED, 13L);
        List<News> activityList = newsRepository.findTopTen(UNDELETED, 27L);
        List<Image> carouselList = imageRepository.findCarouselTopFive();
        List<Image> imageList = imageRepository.findImageTopTen();
        model.addAttribute("noticeList", noticesList);
        model.addAttribute("activityList", activityList);
        model.addAttribute("importantList", importantList);
        model.addAttribute("carouselList", carouselList);
        model.addAttribute("imageList", imageList);
        return "index";
    }

    /**
     * 进入系统后台首页
     *
     * @return
     */
    @RequestMapping(value = "/ahome")
    public String ax() {
        return "/adminAx/index";
    }

    /**
     * 进入添加新闻页面
     *
     * @return
     */
    @RequestMapping(value = "/addNews")
    public String addNo(Model model) {
        List<ViewFinder> firstTypeList = viewFinderRepository.findAllByParentIdAndDeleted(0L, UNDELETED);
        model.addAttribute("firstTypeList", firstTypeList);
        return "/admin/addNews";
    }

    /**
     * 进入上传新闻页面
     *
     * @return
     */
    @RequestMapping(value = "/upNews")
    public String upNews(Model model) {
        List<ViewFinder> firstTypeList = viewFinderRepository.findAllByParentIdAndDeleted(0L, UNDELETED);
        model.addAttribute("firstTypeList", firstTypeList);
        return "/admin/uploadNews";
    }

    /**
     * 进入上传通知公告页面
     *
     * @return
     */
    @RequestMapping(value = "/upNo")
    public String upNo() {
        return "/admin/uploadNotice";
    }

    /**
     * 进入上传学生活动页面
     *
     * @return
     */
    @RequestMapping(value = "/upAc")
    public String upAc() {
        return "/admin/uploadActivity";
    }

    /**
     * 进入上传学院新闻页面
     *
     * @return
     */
    @RequestMapping(value = "/upIm")
    public String upIm() {
        return "/admin/uploadImportant";
    }

    /**
     * 进入用户列表页面
     *
     * @return
     */
    @RequestMapping(value = "/userL")
    public String userL() {
        return "/admin/userList";
    }

    /**
     * 进入添加用户页面
     *
     * @return
     */
    @RequiresPermissions("user:addUser")
    @RequestMapping(value = "/addUserHtml")
    public String addUserHtml(Model model) {
        model.addAttribute("sectorList", sectorRepository.findAllByDeleted(UNDELETED));
        return "/admin/addUserHtml";
    }

    /**
     * 进入添加角色页面
     *
     * @return
     */
    @RequiresPermissions("role:addRole")
    @RequestMapping(value = "/addRoleHtml")
    public String addRoleHtml() {
        return "/admin/addRole";
    }

    /**
     * 进入角色列表页面
     *
     * @return
     */
    @RequiresPermissions("role:look")
    @RequestMapping(value = "/roleListHtml")
    public String roleList() {
        return "/admin/roleList";
    }

    @RequiresPermissions("sector:addSector")
    @RequestMapping("addSectorHtml")
    public String toAddSector() {
        return "/admin/addSector";
    }
}