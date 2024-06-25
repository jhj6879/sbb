package com.example.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor  // 애너테이션의 생성자 방식 questionRepository 객체 주입
@Controller
public class QuestoinController {

    private final QuestionService questionService;

    @GetMapping("/list")
//    @ResponseBody
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate() {
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(@RequestParam(value = "subject") String subject,
                                 @RequestParam(value = "content") String content) {
        this.questionService.create(subject,content);
        return "redirect:/question/list"; // 질문 저장후 질문 목록으로 이동
    }
}
