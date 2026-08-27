package com.epam.assessment.knowledge.persistence;

import com.epam.assessment.knowledge.DTO.KnowledgeElementDTO;
import com.epam.assessment.knowledge.model.KnowledgeBank;
import com.epam.assessment.knowledge.model.KnowledgeElement;
import com.epam.assessment.knowledge.modelmapper.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class JsonPersistence implements KnowledgeBankPersistence {

    ObjectMapper objectMapper = new ObjectMapper();
    String dataFilePath;
    KnowledgeBank knowledgeBank;

    public JsonPersistence(String dataFilePath) {
        this.dataFilePath = dataFilePath;
        this.knowledgeBank = new KnowledgeBank();
    }

    @Override
    public KnowledgeBank load() {
        objectMapper.registerModule(new JavaTimeModule());
        File JSONString;
        try {
            JSONString = new File(dataFilePath);
            ArrayList<KnowledgeElementDTO> knowledgeBankElementsDTO = objectMapper.readValue(
                    JSONString, new TypeReference<ArrayList<KnowledgeElementDTO>>() {
                    });
            ArrayList<KnowledgeElement> knowledgeElements = knowledgeBankElementsDTO
                    .stream()
                    .map((knowledgeBankElement) -> Mapper.toKnowledgeElement(knowledgeBankElement))
                    .collect(Collectors.toCollection(ArrayList::new));

            this.knowledgeBank.setElements(knowledgeElements);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (StreamReadException e) {
            System.out.println("StreamReadException");

            e.printStackTrace();
        } catch (DatabindException e) {
            System.out.println("DatabindException");

            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException");

            e.printStackTrace();
        }

        return knowledgeBank;

    }

    @Override
    public void save(KnowledgeBank knowledgeBank) {

        Charset charset = Charset.forName("US-ASCII");
        Path path = Paths.get("data/knowledge.json");
        List<KnowledgeElement> knowledgeElemets = knowledgeBank.getElements();

        List<KnowledgeElementDTO> knowledgeElementsDTO = knowledgeElemets
                .stream()
                .map((knowledgeElemet) -> Mapper.toKnowledgeElementDTO(knowledgeElemet))
                .collect(Collectors.toCollection(ArrayList::new));
        knowledgeElementsDTO.sort((a, b) -> a.getId() - b.getId());

        try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
            String serializedArray = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(knowledgeElementsDTO);
            System.out.println(serializedArray);
            writer.write(serializedArray, 0, serializedArray.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}
