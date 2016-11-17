package levelp.resume;

import levelp.model.Resume;
import levelp.model.ResumeRepository;
import levelp.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class ResumeController {
    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/addResume", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute(new Resume());
        return "resume/addResume";
    }

    @RequestMapping(value = "/addResume", method = RequestMethod.POST)
    public String addResume(@Valid @ModelAttribute Resume resume,
                            Errors errors, RedirectAttributes ra,
                            Principal principal, ModelMap map) {
        if (errors.hasErrors()) {
            return "resume/addResume";
        }
        System.out.println("resume.getTitle() = " + resume.getTitle());
        resume.user = userRepository.findByEmail(principal.getName());
        resumeRepository.save(resume);
        map.addAttribute("query", "Запрос");
        return "resume/search";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(ModelMap model) {
        model.addAttribute("query", "Запрос");
        return "resume/search";
    }

    @RequestMapping(value = "showAllResume",
            method = RequestMethod.GET)
    public String showAllResume(ModelMap model) {
        model.addAttribute("query", "Запрос");
        return "resume/showAllResume";
    }

    @RequestMapping(value = "showAllResume",
            method = RequestMethod.POST)
    public String showAllResumeAction(@RequestParam("query")
                                      String query,
                                      ModelMap model) {
        List<Resume> list = resumeRepository.findByEmail(query);
        model.addAttribute("query", "Запрос");
        model.addAttribute("list", list);
        return "resume/showAllResume";
    }
}