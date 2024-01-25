package org.bedu.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.bedu.dto.CreateInterviewerDTO;
import org.bedu.dto.InterviewerDTO;
import org.bedu.service.InterviewerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class InterviewerControllerTest {

    @MockBean
    private InterviewerService service;
    
    @Autowired
    private InterviewerController controller;

    @Test
    @DisplayName("Controller should be injected")
    public void smokeTest(){
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller should return a list of interviewers")
    public void findAllTest(){
        List<InterviewerDTO> fakeData = new LinkedList<>();
        InterviewerDTO fakeInterviewer = new InterviewerDTO();

        fakeInterviewer.setId(100);
        fakeInterviewer.setName("Joaquin Salazar");
        fakeInterviewer.setEmail("joaquin@test.com");

        when(service.findAll()).thenReturn(fakeData);

        //ACT
        List<InterviewerDTO> result = controller.findAll();

        assertEquals(fakeData, result);
    }

    @Test
    @DisplayName("Controller should save an interviewer")
    public void saveTest(){
        CreateInterviewerDTO dto = new CreateInterviewerDTO();
        dto.setName("Maria Rosales");
        dto.setEmail("maria@test.com");

        InterviewerDTO saved = new InterviewerDTO();
        saved.setId(200);
        saved.setName("Maria Rosales");
        saved.setEmail("maria@test.com");

        when(service.save(any(CreateInterviewerDTO.class))).thenReturn(saved);

        //ACT
        InterviewerDTO result = controller.save(dto);

        //Assert
        assertNotNull(result);
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getEmail(), result.getEmail());
    }
}