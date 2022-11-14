/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.card.cemilan;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Wiratama
 */
@Controller
public class controller {
    
    @RequestMapping("/viewCard")
    public String getCard(@RequestParam("name") String nama,
                          @RequestParam("tanggal")@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                          @RequestParam("gambar") MultipartFile gambar,
                          Model model) throws IOException{
        
        // for image
        String blob = Base64.encodeBase64String(gambar.getBytes());
        String foto = "data:image/jpeg;base64,".concat(blob);
        
        // for date
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String tanggal = sdf.format(date);
        
        // for thymleaf
        model.addAttribute("nama", nama);
        model.addAttribute("img", foto);
        model.addAttribute("tanggal", tanggal);
        
        return "viewCard";
    }
    
}
