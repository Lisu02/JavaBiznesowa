package com.example.projektJava.scheduler;

import com.example.projektJava.dao.AdvertDAO;
import com.example.projektJava.model.Advert;
import com.example.projektJava.service.EmailService;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@EnableScheduling
public class ExpiredScheduler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    private AdvertDAO advertDAO;
    private EmailService emailService;
    @Autowired
    public ExpiredScheduler(AdvertDAO advertDAO,EmailService emailService){
        this.advertDAO = advertDAO;
        this.emailService = emailService;
    }

    //Sprawdzanie co 30 sekund WAZNA ADNOTACJA TRANSACTIONAL
    @Scheduled(fixedRate = 30000)
    @Transactional
    public void advertExpired(){
        List<Advert> advertList = advertDAO.findAll();

        for(Advert advert: advertList){
            if(advert.getExpirationDate().isBefore(LocalDate.now())){
                sendExpirationEmail(advert);
                advertDAO.delete(advert);
            }
        }

    }

    public void sendExpirationEmail(Advert advert){
        logger.info("Deleting an expired advert -> " + advert);
       // emailService.sendEmail("uzytkownik@gmail.com",advert.getTitle() + " EXPIRED","YOUR ADVERT HAS BEEN EXPIRED! (advert invormation below)\n" + advert.getInformation());
    }


}
