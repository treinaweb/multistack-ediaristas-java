package br.com.treinaweb.ediaristas.api.dtos.responses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;

import lombok.Data;

@Data
public class HateoasResponse {

    private List<LinkResponse> links;

    public HateoasResponse() {
        links = new ArrayList<>();
    }

    public void adcionarLinks(Link... links) {
        for (Link link : links) {
            var linkResponse = new LinkResponse();
            linkResponse.setUri(link.getHref());
            linkResponse.setType(link.getType());
            linkResponse.setRel(link.getRel().value());

            this.links.add(linkResponse);
        }
    }

}
