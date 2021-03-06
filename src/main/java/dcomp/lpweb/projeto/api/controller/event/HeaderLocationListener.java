package dcomp.lpweb.projeto.api.controller.event;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class HeaderLocationListener implements ApplicationListener<HeaderLocationEvento> {

    @Override
    public void onApplicationEvent(HeaderLocationEvento event) {
        adicionaHeaderLocationNaRespostaHTTP(event);

    }

    private void adicionaHeaderLocationNaRespostaHTTP(HeaderLocationEvento event) {
        HttpServletResponse response = event.getResponse();
        Integer id = event.getId();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id).toUri();

        response.setHeader("Location", uri.toString() );
    }
}