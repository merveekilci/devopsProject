package com.sau.myapp2.Controllers;

import com.sau.myapp2.Models.User;
import com.sau.myapp2.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private static String UPLOADED_FOLDER = "/Users/mervekilci/Downloads/myapp2/src/main/resources/static/images/";

    @GetMapping("/usr")
    public String getAllUsers(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/adduser")
    public String addUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "_addUser";
    }

    @PostMapping("/adduser")
    public String addUserSubmit(User user, @RequestParam("image") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (!file.isEmpty()){
            try{
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER +file.getOriginalFilename());
                Files.write(path,bytes);
                user.setImageName("/uploads/" + file.getOriginalFilename());
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        userRepository.save(user);
        return "redirect:/usr"; // Kullanıcıları listeleme sayfasına yönlendirme
    }

    @GetMapping("/updateuser")
    public String updateUser(@RequestParam("id") int id, Model model){
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()) {
            return "redirect:/error.html"; // Kullanıcı bulunamazsa hata sayfasına yönlendir
        }
        User user = optionalUser.get();
        model.addAttribute("user", user);
        return "_updateUser"; // Kullanıcıyı güncelleme formuna yönlendir
    }


    @PostMapping("/updateuser")
    public String updateUserSubmit( User user, BindingResult result) {
        if (result.hasErrors()) {
            return "_updateUser"; // Hata varsa kullanıcıyı aynı sayfada tut
        }
        userRepository.save(user);
        return "redirect:/usr"; // Kullanıcıları listeleme sayfasına yönlendir
    }

    @GetMapping("/deleteuser")
    public String deleteUser(@RequestParam("id") int id){
        userRepository.deleteById(id);
        return "redirect:/usr"; // Kullanıcıları listeleme sayfasına yönlendir
    }






}
