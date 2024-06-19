package com.example.projektJava;

import com.example.projektJava.dao.AdvertDAO;
import com.example.projektJava.dao.UsersDAO;
import com.example.projektJava.model.Advert;
import com.example.projektJava.model.Users;
import com.example.projektJava.service.AdvertService;
import com.example.projektJava.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AdvertServiceTest {

    @Mock
    private AdvertDAO advertDAO;

    @Mock
    private UsersDAO usersDAO;

    @Mock
    private EmailService emailService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AdvertService advertService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessAdvertForm() {
        Users user = new Users();
        user.setUsername("testUser");
        user.setAdverts(new ArrayList<>());

        Advert advert = new Advert();
        advert.setTitle("Test Advert");
        advert.setInformation("Test Information");

        when(authentication.getName()).thenReturn("testUser");
        when(usersDAO.findByUsername("testUser")).thenReturn(user);

        advertService.processAdvertForm(advert, authentication);

        assertEquals(LocalDate.now(), advert.getCreationDate());
        assertEquals(user, advert.getUser());
        assertEquals(false, advert.getAccepted());
        assertEquals(1, user.getAdverts().size());

        verify(advertDAO, times(1)).save(advert);
        //verify(emailService, times(1)).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    public void testUpdateAdvertForm() {
        Advert existingAdvert = new Advert();
        existingAdvert.setId(1L);
        existingAdvert.setCreationDate(LocalDate.now().minusDays(1));

        Advert newAdvert = new Advert();
        newAdvert.setId(1L);
        newAdvert.setTitle("Updated Advert");
        newAdvert.setInformation("Updated Information");

        when(advertDAO.findById(anyLong())).thenReturn(existingAdvert);

        advertService.updateAdvertForm(newAdvert);

        assertEquals(existingAdvert.getCreationDate(), newAdvert.getCreationDate());
        assertEquals(false, newAdvert.getAccepted());
        assertEquals(existingAdvert.getUser(), newAdvert.getUser());

        verify(advertDAO, times(1)).update(newAdvert);
    }

    @Test
    public void testAcceptAdvert() {
        Advert advert = new Advert();
        advert.setId(1L);
        advert.setAccepted(false);

        when(advertDAO.findById(anyLong())).thenReturn(advert);

        advertService.acceptAdvert(1L);

        assertEquals(true, advert.getAccepted());
        verify(advertDAO, times(1)).update(advert);
    }

    @Test
    public void testDeleteAdvert() {
        Advert advert = new Advert();
        advert.setId(1L);

        when(advertDAO.findById(anyLong())).thenReturn(advert);

        advertService.deleteAdvert(1L);

        verify(advertDAO, times(1)).delete(advert);
    }
}

