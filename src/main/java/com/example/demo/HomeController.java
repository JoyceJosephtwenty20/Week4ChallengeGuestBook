package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
public class HomeController {
    public ArrayList<Guestbook> allPosts = new ArrayList<>();
    public ArrayList<Guestbook> singlePost = new ArrayList<>();

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("guestbook", new Guestbook());
        model.addAttribute("message", "Add a new Birthday post");
        return "addPost";
    }

    @PostMapping("/listAll")
    public String listAll(@ModelAttribute Guestbook guestbook, Model model) {

        allPosts.add(guestbook);
        return "redirect:/showAllPosts";
    }



    @RequestMapping("/showAllPosts")
    public String showAll(Model model) {

        model.addAttribute("guestbooks", allPosts);
        model.addAttribute("allmessage" , "All birthday posts");
        return "listAllPosts";
    }

    @RequestMapping("/view/{id}")
    public String viewSingleList(@PathVariable("id") long id, Model model) {

        for (Guestbook onebook : allPosts) {
            if (onebook.getId() == id) {
                singlePost.clear();
                singlePost.add(onebook);
                break;
            }
        }

        return "redirect:/viewPost";
    }


    @RequestMapping("/viewPost")
    public String viewSingle(Model model) {

        model.addAttribute("singlebook", singlePost);
        model.addAttribute("message1", "Here is your post you can make changes here using the update or delete button");
        return "viewPost";

    }

    @RequestMapping("/update/{id}")
    public String updateList(@PathVariable("id") long id, Model model) {

        Guestbook guestbook = new Guestbook();
        for (Guestbook one : allPosts) {
            if (one.getId() == id) {
                guestbook = one;
                allPosts.remove(one);
                break;
            }
        }

        model.addAttribute("guestbook", guestbook);
        model.addAttribute("message", "Update your post");
        return "addPost";
    }

    @RequestMapping("/delete/{id}")
    public String deleteList(@PathVariable("id") long id, Model model) {

        for (Guestbook guestbook : allPosts) {
            if (guestbook.getId() == id) {
                allPosts.remove(guestbook);
                break;
            }
        }

        return "redirect:/showAllPosts";
    }


}