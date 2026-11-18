package br.com.GillMap.ms.alagamentos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ms-regioes")
public interface RegiaoClient {

    @RequestMapping(method = RequestMethod.PUT,
            value = "/api/v1/regioes/{regiaoId}/situacao/limpo")
    void definirLimpo(@PathVariable Long regiaoId);

    @RequestMapping(method = RequestMethod.PUT,
            value = "/api/v1/regioes/{regiaoId}/situacao/leve")
    void definirLeve(@PathVariable Long regiaoId);

    @RequestMapping(method = RequestMethod.PUT,
            value = "/api/v1/regioes/{regiaoId}/situacao/medio")
    void definirMedio(@PathVariable Long regiaoId);

    @RequestMapping(method = RequestMethod.PUT,
            value = "/api/v1/regioes/{regiaoId}/situacao/grave")
    void definirGrave(@PathVariable Long regiaoId);
}