package co.cun.edu.customermicroservice.web.rest.errors;

import java.util.List;
<<<<<<< HEAD
=======

>>>>>>> 14b631ca7e762996af8a874ece38b74eea1b80bd
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ErrorMessage {
    private String code ;
    private List<Map<String, String >> messages ;
    
}