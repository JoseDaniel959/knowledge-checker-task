package com.epam.assessment.knowledge;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.epam.assessment.knowledge.UI.Interface;
import com.epam.assessment.knowledge.model.KnowledgeBank;
import com.epam.assessment.knowledge.persistence.JsonPersistence;


public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        JsonPersistence JSONPersistenceString = (JsonPersistence)context.getBean("jsonPersistence");
        KnowledgeBank knowledgeBank = (KnowledgeBank)context.getBean("knowledgeBank");
        
        Interface UI = (Interface)context.getBean("interface");
        JSONPersistenceString.load();
        UI.loadInterface();
        JSONPersistenceString.save(knowledgeBank);
    }
}