package com.springboot.player_registration_system.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.springboot.player_registration_system.enums.GroupType;
import com.springboot.player_registration_system.exceptions.AvengersCodenameNotAvailable;
import com.springboot.player_registration_system.exceptions.JusticeLeagueCodenameNotAvailable;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CodenameHandler {

    @Getter
    private List<String> avengersJsonCodenames= new ArrayList<>();

    @Getter
    private List<String> justiceLeagueXmlCodenames= new ArrayList<>();

    @Value(value = "${json.codenames.link}")
    private String jsonCodenamesLink;

    @Value(value = "${xml.codenames.link}")
    private String xmlCodenamesLink;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private final XmlMapper xmlMapper;

    @Autowired
    public CodenameHandler(RestTemplate restTemplate, ObjectMapper objectMapper, XmlMapper xmlMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.xmlMapper = xmlMapper;
    }


    @PostConstruct
    private void loadAvengersJsonCodenames(){
        ResponseEntity<String> codenamesOriginalResponse= restTemplate.getForEntity(jsonCodenamesLink,String.class);

        String codenamesResponseAsString= codenamesOriginalResponse.getBody();
        try {
            JsonNode rootNode = objectMapper.readTree(codenamesResponseAsString);
            ArrayNode innerObjectsList= (ArrayNode)rootNode.get("vingadores");
            for (JsonNode object: innerObjectsList) {
                avengersJsonCodenames.add(object.get("codinome").asText());
            }

            //by using Map (readValue)
            /*
            Map codenamesResponse = objectMapper.readValue(codenamesResponseAsString,Map.class);

            List<Map> codenameMapsList = (List<Map>)codenamesResponse.get("vingadores");

            List<String> codenames = codenameMapsList.stream()
                    .map(m -> (String) m.get("codinome"))
                    .toList();
             */
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void loadJusticeLeagueXmlCodenames(){
        ResponseEntity<String> codenamesOriginalResponse= restTemplate.getForEntity(xmlCodenamesLink,String.class);

        String codenamesResponseAsString= codenamesOriginalResponse.getBody();
        try {
             JsonNode rootNode = xmlMapper.readTree(codenamesResponseAsString);

             JsonNode innerObject= rootNode.get("codinomes");

             ArrayNode codenamesAsNode= (ArrayNode)innerObject.get("codinome");
             for (JsonNode node: codenamesAsNode) {
                 justiceLeagueXmlCodenames.add(node.asText());
             }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCodenameFrom(GroupType groupType) {
        if(groupType==GroupType.AVENGERS)
            return getCodenameFromAvengers();
        else //if(groupType==GroupType.JUSTICE_LEAGUE)
            return getCodenameFromJusticeLeague();
    }

    private String getCodenameFromAvengers() {
        if (avengersJsonCodenames.isEmpty()) {
            throw new AvengersCodenameNotAvailable("There are not Avengers Codename available!");
        }
        return getRandomCodenameFrom(avengersJsonCodenames);
    }

    private String getCodenameFromJusticeLeague() {
        if(justiceLeagueXmlCodenames.isEmpty()){
            throw new JusticeLeagueCodenameNotAvailable("There are not Justice League Codename available!");
        }
        return getRandomCodenameFrom(justiceLeagueXmlCodenames);
    }

    private String getRandomCodenameFrom(List<String> codenames) {
        int randomIndex= new Random().nextInt(0,codenames.size());
        String codename= codenames.get(randomIndex);
        codenames.remove(randomIndex);
        return codename;
    }


    //METHOD NOT REQUIRED (called from removePlayer)
    /*
    public void resetCodename(String codename,GroupType groupType){
        if(groupType==GroupType.AVENGERS)
            avengersJsonCodenames.add(codename);
        else //if(groupType==GroupType.JUSTICE_LEAGUE)
            justiceLeagueXmlCodenames.add(codename);
    }*/

}


