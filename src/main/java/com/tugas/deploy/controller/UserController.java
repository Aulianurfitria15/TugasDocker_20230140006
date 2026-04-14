package com.tugas.deploy.controller;

import com.tugas.deploy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {
    private final String USERNAME = "admin";
    private final String PASSWORD = "20230140006";
    private List<User> daftarMahasiswa = new ArrayList<>();

    @GetMapping("/")
    public String loginpage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            return "home";
        } else {
            model.addAttribute("error",
                    "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/home")
    public String homepage(Model model) {
        // Mengirimkan list mahasiswa ke view home.html
        model.addAttribute("mahasiswaList", daftarMahasiswa);
        return "home";
    }

    @GetMapping("/form")
    public String formPage() {
        return "form";
    }

    @PostMapping("/tambah-mahasiswa")
    public String tambahMahasiswa(@RequestParam String nama,
                                  @RequestParam String nim,
                                  @RequestParam String jenisKelamin) {
        // Simpan data ke dalam list (menggunakan class model User)
        User mahasiswaBaru = new User();
        mahasiswaBaru.setUsername(nama); // Kita gunakan field username sebagai Nama
        mahasiswaBaru.setPassword(nim);  // Kita gunakan field password sebagai NIM
        mahasiswaBaru.setJenisKelamin(jenisKelamin); // Simpan jenis kelamin

        daftarMahasiswa.add(mahasiswaBaru);

        return "redirect:/home"; // Setelah submit, kembali ke halaman tabel
    }
}
