package com.example.producingwebservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.opentable.htng.otsquad.Country;
import com.opentable.htng.otsquad.Currency;
import com.opentable.htng.otsquad.OTMember;
import com.opentable.htng.otsquad.OTSquad;
import com.opentable.htng.otsquad.OTSquadResponse;

@Component
public class OTSquadRepository {

    private static final Map<String, OTSquad> otSquads = new HashMap<>();
    private static final ArrayList<OTMember> otMembers = new ArrayList<>();

    @PostConstruct
    public void initData() {

        OTMember jasson = new OTMember();
        jasson.setName("Jasson");
        jasson.setId(1337);

        OTMember yadnyesh = new OTMember();
        yadnyesh.setName("Yadnyesh");
        yadnyesh.setId(1);

        OTMember dmtry = new OTMember();
        dmtry.setName("Dmitry");
        dmtry.setId(2);

        OTMember david = new OTMember();
        david.setName("David");
        david.setId(3);

        OTMember tomas = new OTMember();
        tomas.setName("Tomas");
        tomas.setId(4);

        OTMember athulya = new OTMember();
        athulya.setName("Preethi");
        athulya.setId(4);

        ArrayList<OTMember> superAwesomeMembers = new ArrayList<>();
        superAwesomeMembers.add(jasson);

        ArrayList<OTMember> integrationMembers = new ArrayList<>();
        integrationMembers.add(yadnyesh);
        integrationMembers.add(dmtry);
        integrationMembers.add(athulya);
        integrationMembers.add(david);
        integrationMembers.add(tomas);
        integrationMembers.add(jasson);

        ArrayList<OTMember> pupsMembers = new ArrayList<>();
        pupsMembers.add(yadnyesh);
        pupsMembers.add(jasson);

        OTSquad pups = new OTSquad();
        pups.setId(1);
        pups.setSquadName("PUPS");
        pups.getMembers().addAll(pupsMembers);
        pups.setTotalMembers(pups.getMembers().size());

        OTSquad integrations = new OTSquad();
        integrations.setId(2);
        integrations.setSquadName("Integrations");
        integrations.getMembers().addAll(integrationMembers);
        integrations.setTotalMembers(integrations.getMembers().size());

        OTSquad superAwesome = new OTSquad();
        superAwesome.setId(1337);
        superAwesome.setSquadName("SUPER AWESOME SQUAD");
        superAwesome.getMembers().addAll(superAwesomeMembers);
        superAwesome.setTotalMembers(superAwesome.getMembers().size());

        otMembers.add(jasson);
        otMembers.add(yadnyesh);
        otMembers.add(dmtry);
        otMembers.add(david);
        otMembers.add(tomas);
        otMembers.add(athulya);

        otSquads.put("PUPS",pups);
        otSquads.put("INTEGRATIONS",integrations);
        otSquads.put("SUPER AWESOME SQUAD",superAwesome);

    }

    /*Getting information of a squad by name*/
    public OTSquad getSquad(String name) {
        return otSquads.get(name.toUpperCase());
    }

    /*Getting all the squads in which the member is in*/
    public ArrayList<String> getMemberSquads(String memberName){
        ArrayList<String> squads = new ArrayList<>();

        for(Map.Entry<String, OTSquad> entry : otSquads.entrySet()) {
            for(OTMember otMember: entry.getValue().getMembers()){
                System.out.println("name is:" + otMember.getName() + " and looking for " + memberName);
                if(otMember.getName().compareToIgnoreCase(memberName)==0) {
                    squads.add(entry.getKey());
                    continue;
                }
            }
        }
        return squads;
    }

    public String addMemberToSquad(String memberName, String squadName){
        OTMember addedMember = null;
        String addedSquad = null;
        String result = "";

        for(OTMember otMember:otMembers){
            if(memberName.compareToIgnoreCase(otMember.getName())==0){
                addedMember=otMember;
                continue;
            }
        }
        if(addedMember == null){
            return memberName + "was not found";
        }
        if(addedMember != null){
            for(Map.Entry<String, OTSquad> entry : otSquads.entrySet()) {
                if(squadName.compareToIgnoreCase(entry.getKey())==0){
                    addedSquad = squadName;
                    entry.getValue().getMembers().add(addedMember);
                    continue;
                }
            }
        }

        if(addedSquad == null){
            return squadName +" was not found";
        }

        return addedMember.getName() + " was added to SQUAD " + addedSquad;

    }
}
