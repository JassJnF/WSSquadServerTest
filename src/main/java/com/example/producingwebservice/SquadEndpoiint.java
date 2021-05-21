package com.example.producingwebservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.opentable.htng.otsquad.AddMemberToSquad;
import com.opentable.htng.otsquad.AddMemberToSquadResponse;
import com.opentable.htng.otsquad.GetOTSquad;
import com.opentable.htng.otsquad.MemberSquadsResponse;
import com.opentable.htng.otsquad.OTSquadResponse;
import com.opentable.htng.otsquad.GetMemberSquads;
import com.opentable.htng.otsquad.MemberSquadsResponse;

@Endpoint
public class SquadEndpoiint {
    private static final String NAMESPACE_URI = "http://opentable.com/htng/otsquad";
    private OTSquadRepository otSquadRepository;

    @Autowired
    public SquadEndpoiint(OTSquadRepository otSquadRepository) {
        this.otSquadRepository = otSquadRepository;
    }

    //Define operation, it should be the same of the xsd schema file.
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOTSquad")
    @ResponsePayload
    public OTSquadResponse getOTSquad(@RequestPayload GetOTSquad request) {
        System.out.println("Looking for: " + request.getSquadName());
        OTSquadResponse response = new OTSquadResponse();
        response.setOtsquad(otSquadRepository.getSquad(request.getSquadName()));
        return response;
    }

    //Define operation:
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMemberSquads")
    @ResponsePayload
    public MemberSquadsResponse getMemberSquads(@RequestPayload GetMemberSquads request) {
        System.out.println("Looking for squads for member: " + request.getMemberName());
        MemberSquadsResponse response = new MemberSquadsResponse();
        ArrayList<String> squads = otSquadRepository.getMemberSquads(request.getMemberName());
        response.getSquads().addAll(squads);
        System.out.println("squads found are:");
        for(String squad:response.getSquads()){
            System.out.println(squad);
        }
        return response;
    }

    //Define operation:
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addMemberToSquad")
    @ResponsePayload
    public AddMemberToSquadResponse getMemberSquads(@RequestPayload AddMemberToSquad request) {
        AddMemberToSquadResponse response = new AddMemberToSquadResponse();

        response.setResult(otSquadRepository.addMemberToSquad(request.getMemberName(), request.getSquadName()));
        return response;
    }

}
