package cn.tinbat.stowaway.controller;

import cn.tinbat.stowaway.domain.Cargo;
import cn.tinbat.stowaway.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {
    @Value("${filepath}")
    private String filepath;

    @Autowired
    private CargoService cargoService;

    private static Map<String, Cargo> cargos = new HashMap<>();

    @RequestMapping("/hello")
    public String hello(Model model) {
        File file = new File(filepath);
        if(!file.isDirectory()){
            file.mkdir();
        }
        File[] files = file.listFiles();
        long space = 0l;
        for(int i=0;i<files.length;i++){
            space = space+files[i].length();
        }
        model.addAttribute("name", "world");
        model.addAttribute("space", space/1000);

        return "hello";
    }

    @RequestMapping("/stow")
    public void stow(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String uuid = httpServletRequest.getParameter("uuid");
        String str = httpServletRequest.getParameter("str");
        System.out.println("uuid = " + uuid);
        System.out.println("str =" + str);
        String output;
        if (cargos.containsKey(uuid)) {
            if ("$".equals(str)) {
                cargoService.saveFile(cargos.get(uuid));
                cargos.remove(uuid);
                output = "end";
            } else {
                Cargo cargo = cargos.get(uuid);
                cargo.load(str);
                cargos.put(uuid, cargo);
                output = "append";
            }
        } else {
            Cargo cargo = new Cargo(uuid, uuid);
            cargo.load(str);
            cargos.put(uuid, cargo);
            output = "append";
        }
        try {
            PrintWriter out = httpServletResponse.getWriter();
            out.print(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/unload")
    public void unload(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String uuid = httpServletRequest.getParameter("uuid");
        String output = cargoService.getGoods(uuid);
        try {
            PrintWriter out = httpServletResponse.getWriter();
            System.out.println(output);
            out.print(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}